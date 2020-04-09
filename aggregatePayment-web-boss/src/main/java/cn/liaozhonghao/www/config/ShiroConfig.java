package cn.liaozhonghao.www.config;

import cn.liaozhonghao.www.permission.shiro.credentials.RetryLimitHashedCredentialsMatcher;
import cn.liaozhonghao.www.permission.shiro.filter.RcCaptchaValidateFilter;
import cn.liaozhonghao.www.permission.shiro.filter.RcFormAuthenticationFilter;
import cn.liaozhonghao.www.permission.shiro.realm.OperatorRealm;
import cn.liaozhonghao.www.permission.shiro.spring.SpringCacheManagerWrapper;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 生成ehcache管理器的工厂
    // https://blog.csdn.net/jialijuan521_zhang/article/details/85913613
    @Bean
    public EhCacheManagerFactoryBean ehcacheManagerFactoryBean () {
        EhCacheManagerFactoryBean ehcacheManagerFactoryBean = new EhCacheManagerFactoryBean();  // 该工厂bean产生并管理缓存管理器
        if (isNullOfCacheManagerObject(ehcacheManagerFactoryBean)) {  // 如果还没有产生缓存管理器，那么就配置该ehcacheManagerFactoryBean 用于创建缓存管理器。
            configEhcacheManagerFactoryBean(ehcacheManagerFactoryBean);
        }
        return ehcacheManagerFactoryBean;  // 返回配置好的缓存管理器工厂bean
    }

    private boolean isNullOfCacheManagerObject (EhCacheManagerFactoryBean ehcacheManagerFactoryBean ) {
        return ehcacheManagerFactoryBean.getObject() == null;
    }

    private void configEhcacheManagerFactoryBean (EhCacheManagerFactoryBean ehcacheManagerFactoryBean) {
        ehcacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehcacheManagerFactoryBean.setShared(true);  // 关键，让该工厂创建的管理器能够被共享， spring本身能使用创建的缓存管理器，shrio也能使用该缓存管理器。
    }

    // 为spring提供ehcache缓存管理器
    @Bean
    public EhCacheCacheManager springCacheManager (EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
        return ehCacheCacheManager;
    }

    // 为shiro提供ehcache缓存管理器
    @Bean
    public EhCacheManager shiroCacheManager (EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean.getObject());
        return ehCacheManager;
    }

    /**
     * 自定义的user Realm实现
     *
     * @param retryLimitHashedCredentialsMatcher 凭证匹配器
     * @return 自定义Realm
     */
    @Bean(name = "userRealm")
    public OperatorRealm operatorRealm(@Qualifier("credentialsMatcher") RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher) {
        OperatorRealm operatorRealm = new OperatorRealm();
        operatorRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher);
        operatorRealm.setCachingEnabled(false);
        return operatorRealm;
    }

    /**
     * 凭证匹配器，做登录次数验证，和密码匹配验证
     *
     * @param ehCacheManager
     * @return 凭证匹配器
     */
    @Bean(name = "credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(EhCacheManager ehCacheManager) {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager);
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");
        retryLimitHashedCredentialsMatcher.setHashIterations(2);
        retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

        return retryLimitHashedCredentialsMatcher;
    }

    /**
     * 安全管理器
     *
     * @param operatorRealm 自定义Realm
     * @return 安全管理器
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") OperatorRealm operatorRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(operatorRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 基于Form表单的身份验证过滤器，为了控制验证码
     * 注意：该验证器不能注册为bean，否则会导致该验证其注册两遍，在访问的时候会抛异常报错
     *
     * @return 表单的身份验证过滤器
     */
    public RcFormAuthenticationFilter rcFormAuthenticationFilter() {
        RcFormAuthenticationFilter rcFormAuthenticationFilter = new RcFormAuthenticationFilter();
        rcFormAuthenticationFilter.setUsernameParam("loginName");
        rcFormAuthenticationFilter.setPasswordParam("roncooPwd");
        rcFormAuthenticationFilter.setRememberMeParam("rememberMe");
        rcFormAuthenticationFilter.setLoginUrl("/login");
        rcFormAuthenticationFilter.setFailureKeyAttribute("shiroLoginFailure");
        return rcFormAuthenticationFilter;
    }

    /**
     * 验证码验证过滤器
     *
     * @return 验证码验证过滤器
     */
    @Bean(name = "rcCaptchaValidateFilter")
    public RcCaptchaValidateFilter rcCaptchaValidateFilter() {
        RcCaptchaValidateFilter rcCaptchaValidateFilter = new RcCaptchaValidateFilter();
        rcCaptchaValidateFilter.setCaptchaEbabled(true);
        rcCaptchaValidateFilter.setCaptchaParam("captchaCode");
        rcCaptchaValidateFilter.setFailureKeyAttribute("shiroLoginFailure");
        return rcCaptchaValidateFilter;
    }

    /**
     * Shiro主过滤器本身功能十分强大,其强大之处就在于它支持任何基于URL路径表达式的、自定义的过滤器的执行
     * Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截,Shiro对基于Spring的Web应用提供了完美的支持
     *
     * @param defaultWebSecurityManager 安全管理器
     * @param rcCaptchaValidateFilter   验证码验证过滤器
     * @return Shiro主过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager, @Qualifier("rcCaptchaValidateFilter") RcCaptchaValidateFilter rcCaptchaValidateFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/system/unauthorized.jsp");

        Map<String, Filter> filters = new LinkedMap();
        filters.put("authc", rcFormAuthenticationFilter());
        filters.put("rcCaptchaValidate", rcCaptchaValidateFilter);
        shiroFilterFactoryBean.setFilters(filters);
        // 拦截器
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/rcCaptcha*", "anon");
        filterChainDefinitionMap.put("/system/unauthorized.jsp", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/dwz/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/login", "rcCaptchaValidate,authc");
        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
