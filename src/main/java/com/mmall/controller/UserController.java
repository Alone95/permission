package com.mmall.controller;

import com.google.code.kaptcha.Constants;
import com.mmall.model.SysUser;
import com.mmall.service.SysUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author alone95
 * @date 2017/10/30
 */
@Controller
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String path ="signin.jsp";
        response.sendRedirect(path);
    }

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //从session中取出servlet生成的验证码
        String kaptchExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //获取用户页面输入的验证码
        String kaptchReceived =request.getParameter("kaptcha");
        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMsg = "";
        String ret = request.getParameter("ret");

        if(StringUtils.isBlank(username)){
            errorMsg = "用户名不能为空";
        }else if(StringUtils.isBlank(password)){
            errorMsg = "密码不能为空";
        }else if(StringUtils.isBlank(kaptchReceived)){
            errorMsg = "验证码不能为空";
        }else if(sysUser==null){
            errorMsg = "查询不到指定用户";
        }else if(!sysUser.getPassword().equals(MD5Util.encrypt(password))){
            errorMsg = "用户名或者密码错误";
        }else if (!kaptchReceived.equalsIgnoreCase(kaptchExpected)){
            errorMsg = "验证码错误,请重新输入";
        }else if(sysUser.getStatus()!=1){
            errorMsg = "用户已被冻结,请联系管理员";
        }else{
            //login success
            request.getSession().setAttribute("user",sysUser);
            if (StringUtils.isNotBlank(ret)){
                response.sendRedirect(ret);
            }else{
                response.sendRedirect("/admin/index.page");//TODO;
            }

        }
        request.setAttribute("error",errorMsg);
        request.setAttribute("username",username);
        if (StringUtils.isNotBlank(ret)){
            request.setAttribute("ret",ret);
        }
        String path ="signin.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}
