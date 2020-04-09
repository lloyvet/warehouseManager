package com.lloyvet.system.realm;


import com.lloyvet.system.common.ActiveUser;
import com.lloyvet.system.common.Constant;
import com.lloyvet.system.domain.User;
import com.lloyvet.system.service.MenuService;
import com.lloyvet.system.service.RoleService;
import com.lloyvet.system.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;



public class UserRealm extends AuthorizingRealm {


    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private MenuService menuService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        User user = this.userService.queryUserByLoginName(userName);
        if (null != user) {
            ActiveUser activeUser = new ActiveUser();
            activeUser.setUser(user);
            //根据用户id查询权限名称集合
            List<String> roles = roleService.queryRoleNamesByUserId(user.getId());
            //根据用户id查询权限编码的集合
            List<String> prermissions = menuService.queryPermissionCodeByUserId(user.getId());
            activeUser.setRoles(roles);
            activeUser.setPermissions(prermissions);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPwd(), ByteSource.Util.bytes(user.getSalt()), getName());
            return info;
        }
        return null;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo  info =new SimpleAuthorizationInfo();
        ActiveUser activeUser= (ActiveUser) principalCollection.getPrimaryPrincipal();
        List<String> permissions = activeUser.getPermissions();
        List<String> roles = activeUser.getRoles();
        User user = activeUser.getUser();
        if(user.getType().equals(Constant.USER_TYPE_SUPER)){
            info.addStringPermission("*:*");
        }else{
            if(null!=roles&&roles.size()>0){
                info.addRoles(roles);
            }
            if(null!=permissions&&permissions.size()>0){
                info.addStringPermissions(permissions);
            }
        }
        return info;
    }
}
