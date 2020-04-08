package com.lloyvet.system.controller;

import com.lloyvet.system.common.ResultObj;
import com.lloyvet.system.domain.Loginfo;
import com.lloyvet.system.service.LoginfoService;
import com.lloyvet.system.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;

    @RequestMapping("loadAllLoginfo")
    public Object loadAllLoginfo(LoginfoVo loginfoVo){
        return loginfoService.queryAllLoginfo(loginfoVo);
    }
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Loginfo loginfo){
        try{
            loginfoService.removeById(loginfo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try{
            loginfoService.removeByIds(Arrays.asList(ids));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
}
