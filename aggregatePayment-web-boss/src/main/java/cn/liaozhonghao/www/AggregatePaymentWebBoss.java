package cn.liaozhonghao.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@ServletComponentScan
public class AggregatePaymentWebBoss
{
    public static void main(String[] args) {
        SpringApplication.run(AggregatePaymentWebBoss.class, args);
    }
}
