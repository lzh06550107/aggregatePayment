package cn.liaozhonghao.www.controller.user;

import cn.liaozhonghao.www.common.core.dwz.DWZ;
import cn.liaozhonghao.www.common.core.dwz.DwzAjax;
import cn.liaozhonghao.www.common.core.page.PageBean;
import cn.liaozhonghao.www.common.core.page.PageParam;
import cn.liaozhonghao.www.service.user.entity.UserInfo;
import cn.liaozhonghao.www.service.user.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户信息
 */
@Controller
@RequestMapping("/user/info")
public class UserInfoController {
	
	@Autowired
	private UserInfoService rpUserInfoService;

	/**
	 * 函数功能说明 ： 查询用户信息
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/list", method ={RequestMethod.POST, RequestMethod.GET})
	public String list(UserInfo rpUserInfo, PageParam pageParam, Model model) {
		PageBean pageBean = rpUserInfoService.listPage(pageParam, rpUserInfo);
		model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("rpUserInfo",rpUserInfo);
		return "user/info/list";
	}
	
	/**
	 * 函数功能说明 ：跳转添加
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("user:userInfo:add")
	@RequestMapping(value = "/addUI", method = RequestMethod.GET)
	public String addUI() {
		
		return "user/info/add";
	}
	
	/**
	 * 函数功能说明 ： 保存
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("user:userInfo:add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model, @RequestParam("userName") String userName, @RequestParam("mobile") String mobile, @RequestParam("password") String password, DwzAjax dwz) {
		rpUserInfoService.registerOffline(userName, mobile, password);
		dwz.setStatusCode(DWZ.SUCCESS);
		dwz.setMessage(DWZ.SUCCESS_MSG);
		model.addAttribute("dwz", dwz);
		return DWZ.AJAX_DONE;
	}
	
	/**
	 * 函数功能说明 ： 查询用户信息 查找带回
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/lookupList", method ={RequestMethod.POST, RequestMethod.GET})
	public String lookupList(UserInfo rpUserInfo, PageParam pageParam, Model model) {
		PageBean pageBean = rpUserInfoService.listPage(pageParam, rpUserInfo);
		model.addAttribute("pageBean", pageBean);
        model.addAttribute("pageParam", pageParam);
        model.addAttribute("rpUserInfo",rpUserInfo);
		return "user/info/lookupList";
	}

}
