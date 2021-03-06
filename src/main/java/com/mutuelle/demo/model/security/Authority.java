package com.mutuelle.demo.model.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String authority;

	public Authority(String erole) {
		this.authority = erole;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return "Authority [authority=" + authority + "]";
	}

}
