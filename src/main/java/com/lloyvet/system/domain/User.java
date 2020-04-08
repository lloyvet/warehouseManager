package com.lloyvet.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 登陆名
     */
    @TableField(value = "loginname")
    private String loginname;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 密码
     */
    @TableField(value = "pwd")
    private String pwd;

    /**
     * 部门ID
     */
    @TableField(value = "deptid")
    private Integer deptid;

    /**
     * 入职时间
     */
    @TableField(value = "hiredate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date hiredate;

    @TableField(value = "ordernum")
    private Integer ordernum;

    /**
     * 用户类型[0超级管理员1普通用户]
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 头像地址
     */
    @TableField(value = "imgpath")
    private String imgpath;

    /**
     * 盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     *  是否可用
     */
    @TableField(value = "available")
    private Integer available;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_LOGINNAME = "loginname";

    public static final String COL_ADDRESS = "address";

    public static final String COL_SEX = "sex";

    public static final String COL_REMARK = "remark";

    public static final String COL_PWD = "pwd";

    public static final String COL_DEPTID = "deptid";

    public static final String COL_HIREDATE = "hiredate";

    public static final String COL_ORDERNUM = "ordernum";

    public static final String COL_TYPE = "type";

    public static final String COL_IMGPATH = "imgpath";

    public static final String COL_SALT = "salt";

    public static final String COL_AVAILABLE = "available";
}