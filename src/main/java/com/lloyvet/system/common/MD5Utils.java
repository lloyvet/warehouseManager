package com.lloyvet.system.common;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

public class MD5Utils {
    //生成uuid
    public static String creatUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    //对密码进行加密
    public static String md5(String source,String salt,Integer has){
        Md5Hash hash = new Md5Hash(source,salt,has);
        return hash.toString();
    }
}
