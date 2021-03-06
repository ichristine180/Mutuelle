package com.mutuelle.demo.model.security;

import java.util.Collection;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mutuelle.demo.model.HealthFacility;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "userName"),
		@UniqueConstraint(columnNames = "email") })
public class Users implements UserDetails {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	@NotEmpty(message = " name is Required")
	@Column(name = "fName")
	private String fName;
	@NotEmpty(message = " name is Required")
	@Column(name = "lName")
	private String lName;

	@NotEmpty(message = " email is Required")
	@Email(message = "Valid email is Required")
	@Column(name = "email")
	private String email;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "healthFacilityId")
	private HealthFacility healthFacility;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	private boolean enabled = true;

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(final Long user_id) {
		this.user_id = user_id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(final String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(final String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public HealthFacility getHealthFacility() {
		return healthFacility;
	}

	public void setHealthFacility(HealthFacility healthFacility) {
		this.healthFacility = healthFacility;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", healthFacility=" + healthFacility + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
		return authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
