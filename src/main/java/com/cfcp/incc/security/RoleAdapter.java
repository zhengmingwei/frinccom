package com.cfcp.incc.security;

import com.cfcp.incc.entity.Role;
import org.springframework.security.core.GrantedAuthority;


public class RoleAdapter implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private Role role;

	public RoleAdapter(Role role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role.getId();
	}

	public Role getRole() {
		return role;
	}
}
