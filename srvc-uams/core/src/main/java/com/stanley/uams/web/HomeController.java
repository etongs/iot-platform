package com.stanley.uams.web;

import com.stanley.common.spring.BaseController;
import com.stanley.uams.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录页、首页
 * @author 13346450@qq.com 童晟
 * @version 1.0
 * @create 2017/8/22
 **/
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {
    @Resource
    private HomeService homeService;

    /**
     * 登录页
     * @return
     */
    @RequestMapping(value = {"","login"}, method = RequestMethod.GET)
    public String login() {
        return "mainframe/login";
    }

    /**
     * 登录校验
     * @param model
     * @param username
     * @param password
     * @param rememberMe
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Model model,HttpServletRequest request, String username, String password, Boolean rememberMe) {
        return homeService.checkLogin(model, request, username, password, rememberMe);
    }

    /**
     * 登录后的主页面
     * @return
     */
    @RequestMapping("home")
    public String home(Model model){
        return homeService.home(model);
    }

    /**
     * 登出
     * @param model
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(Model model){
        return homeService.logout(model);
    }

    /**
     * 被踢出后跳转的页面
     * @param model
     * @return
     */
    @RequestMapping(value = "kickout")
    public String kickout(Model model) {
        return homeService.kickout(model);
    }

    /**
     * 未登录的跳转页面
     * @param model
     * @return
     */
    @RequestMapping(value = "401", method = RequestMethod.GET)
    public String notLogin(Model model){
        return "401";
    }

    /**
     * 无权限的跳转页面
     * @param model
     * @return
     */
    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String forbidden(Model model){
        return "403";
    }

    /**
     * 获取验证码
     * @param response
     */
    @RequestMapping(value="getGifCode",method=RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpSession session){
        homeService.getGifCode(response,session);
    }

    @RequestMapping(value = "checkValidateCode/{validateCode}",method=RequestMethod.GET)
    @ResponseBody
    public String checkValidateCode(HttpServletRequest request, @PathVariable String validateCode){
        return homeService.checkValidateCode(request,validateCode);
    }
}
