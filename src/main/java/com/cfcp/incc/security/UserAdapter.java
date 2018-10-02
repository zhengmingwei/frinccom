package com.cfcp.incc.security;

import com.cfcp.incc.entity.Role;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.service.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;


public class UserAdapter implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;

    private Collection<GrantedAuthority> athu;

    public UserAdapter(UserService userService, String userName) {

        this.user = userService.findUserByUserName(userName);
        if (this.user == null) {
            throw new UsernameNotFoundException("没有找到该用户：" + userName);
        }

        athu = new HashSet<GrantedAuthority>();
        for (Role role : userService.listRolesByUser(user)) {
            athu.add(new RoleAdapter(role));
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return athu;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getStatus() == 1;
    }

    public User getUser() {
        return user;
    }

    public void refresh(UserService userService){
        String userName = this.getUser().getName();
        this.user = userService.findUserByUserName(userName);
        if (this.user == null) {
            throw new UsernameNotFoundException("没有找到该用户：" + userName);
        }

        athu = new HashSet<GrantedAuthority>();
        for (Role role : userService.listRolesByUser(user)) {
            athu.add(new RoleAdapter(role));
        }
    }

}
