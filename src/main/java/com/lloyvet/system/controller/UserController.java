package com.lloyvet.system.controller;

import com.lloyvet.system.common.Constant;
import com.lloyvet.system.common.MD5Utils;
import com.lloyvet.system.common.ResultObj;
import com.lloyvet.system.domain.User;
import com.lloyvet.system.service.UserService;
import com.lloyvet.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("loadAllUser")
    public Object loadAllUser(UserVo userVo){
        return userService.queryAllUser(userVo);
    }
    @RequestMapping("updateUser")
    public ResultObj updateUser(User user){
        try{
            userService.updateUser(user);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 添加用户
     */
    @PostMapping("addUser")
    public ResultObj addUser(User user){
        try{
            user.setSalt(MD5Utils.creatUUID());
            user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD,user.getSalt(),2));
            user.setHiredate(new Date());
            user.setImgpath(Constant.DEFAULT_TITLE_IMAGE);
            user.setAvailable(Constant.AVAILABLE_TRUE);
            userService.saveUser(user);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除用户
     */
    @PostMapping("deleteUser")
    public ResultObj deleteUser(Integer id){
        try{
            userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

}
