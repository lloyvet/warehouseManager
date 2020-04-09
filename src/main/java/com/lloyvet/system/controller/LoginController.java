package com.lloyvet.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lloyvet.system.common.ActiveUser;
import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.MenuTreeNode;
import com.lloyvet.system.common.ResultObj;
import com.lloyvet.system.domain.Loginfo;
import com.lloyvet.system.domain.Menu;
import com.lloyvet.system.domain.User;
import com.lloyvet.system.service.LoginfoService;
import com.lloyvet.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("login")
public class LoginController{
    
    @Autowired
    private MenuService menuService;

    @Autowired
    private LoginfoService loginfoService;
    /**
     * 用户登录
     */
    @RequestMapping("doLogin")
    @ResponseBody
    public ResultObj doLogin(String loginname, String password, HttpServletRequest request){
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(loginname,password);
            subject.login(loginToken);
            ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
            String token = subject.getSession().getId().toString();
            //写入登录日志
            User user = activeUser.getUser();
            request.setAttribute("user",user);
            Loginfo loginfo = new Loginfo();
            loginfo.setLoginname(user.getName()+"-"+user.getLoginname());
            loginfo.setLoginip(request.getRemoteAddr());
            loginfo.setLogintime(new Date());
            loginfoService.save(loginfo);
            List<String> permissions = activeUser.getPermissions();
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            map.put("permissions",permissions);

            return new ResultObj(200,"登录成功",map);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return ResultObj.UN_LOGIN;
        }
    }

    /**
     * 返回验证码
     */

    /**
     * 加载所有菜单
     */
    @RequestMapping("loadIndexMenu")
    @ResponseBody
    public Object loadIndexMenu(){
        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
        User user = activeUser.getUser();
        if(null==user){
            return null;
        }
        List<Menu> menus = null;
        if(user.getType().equals(Constant.USER_TYPE_SUPER)){
            //超级管理员
            menus = menuService.queryAllMenuForList();
        }else {
            //普通用户
            menus = menuService.queryMenuForListByUserId(user.getId());
        }
        List<MenuTreeNode> menuTreeNodes = new ArrayList<>();
        for (Menu menu : menus) {
            Boolean spread = menu.getSpread()==Constant.SPREAD_TRUE?true:false;
            menuTreeNodes.add(new MenuTreeNode(menu.getId(),menu.getPid(),menu.getTitle(),menu.getHref(),menu.getIcon(),spread, menu.getTarget(),menu.getTypecode()));
        }
        List<MenuTreeNode> nodes = MenuTreeNode.MenuTreeNodeBuilder.build(menuTreeNodes,0);
        Map<String,Object> res = new HashMap<>();
        for (MenuTreeNode node : nodes) {
            res.put(node.getTypecode(),node);
        }
        return res;
    }
    /**
     * 验证当前token是否登录
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public ResultObj checkLogin(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return ResultObj.IS_LOGIN;
        }else{
            return ResultObj.UN_LOGIN;
        }
    }


}
