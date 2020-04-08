package com.lloyvet.system.controller;

import com.lloyvet.system.common.ActiveUser;
import com.lloyvet.system.common.ResultObj;
import com.lloyvet.system.domain.Notice;
import com.lloyvet.system.domain.User;
import com.lloyvet.system.service.NoticeService;
import com.lloyvet.system.vo.NoticeVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 查询
     */
    @RequestMapping("loadAllNotice")
    public Object loadAllNotice(NoticeVo noticeVo){
        return noticeService.queryAllNotice(noticeVo);
    }
    /**
     * 添加
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(Notice notice){

        try{
            Subject subject = SecurityUtils.getSubject();
            ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
            notice.setCreatetime(new Date());
            notice.setOpername(activeUser.getUser().getName());
            noticeService.save(notice);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(Notice notice){
        try{
            notice.setCreatetime(new Date());
            noticeService.updateById(notice);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(Notice notice){
        try {
            noticeService.removeById(notice);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 批量删除
     */
    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteNotice(Integer[] ids){
        try {
            noticeService.removeByIds(Arrays.asList(ids));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
