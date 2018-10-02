package com.cfcp.incc.security;

import com.cfcp.incc.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {

    static Logger logger = LoggerFactory.getLogger(UserContext.class);

    public static User getCurrentUser() {
        User user;
        try {
            UserAdapter adapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = adapter.getUser();
            for (GrantedAuthority auth : adapter.getAuthorities()) {
                user.addRole(((RoleAdapter) auth).getRole());
            }
        } catch (Exception e) {
            user = null;
//			throw new RuntimeException("获取当前用户时发生错误。",e);
        }
        return user;
    }

    public static void refreshAuths(UserAdapter adapter){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), adapter.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
