package cn.yhs.learn.shiro;

import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.domain.Users;
import cn.yhs.learn.service.UsersService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.shiro.MyLoginRealm
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/29 9:29
 * @Description: 自定义的Realm
 **/
public class MyLoginRealm extends AuthorizingRealm {

    @Autowired
    private UsersService usersService;

    /**
     * 设置该Realm的名称
     *
     * @return
     */
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * 权限授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Users users = (Users) principals.getPrimaryPrincipal();
        // 创建授权对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 给用户添加角色
        List<Role> roleList = users.getRoleList();
        if (null != roleList && roleList.size() > 0) {
            ArrayList<String> roles = new ArrayList<>();
            for (Role role : roleList) {
                roles.add(role.getRoleName());
                List<Permission> permissionList = role.getPermissionList();
                if (null != permissionList && permissionList.size() > 0) {
                    for (Permission permission : permissionList) {
                        simpleAuthorizationInfo.addStringPermission(permission.getPermissionUrl());
                    }
                }
            }
            simpleAuthorizationInfo.addRoles(roles);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录验证用的
     *
     * @param token 前端获取的用户名和密码
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.从token中获取登录的用户名
        String username = token.getPrincipal().toString();
        // 2.根据用户名去数据库查询用户
        Users daoUsers = usersService.findUserByUsername(username);
        if (null != daoUsers) {
            // 加盐规则
            ByteSource byteSource = ByteSource.Util.bytes("喻汉生加的盐");
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(daoUsers, daoUsers.getPassword(), byteSource, this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }

}
