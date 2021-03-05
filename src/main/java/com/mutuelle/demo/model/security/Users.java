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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mutuelle.demo.model.HealthFacility;


@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "userName"),
    @UniqueConstraint(columnNames = "email")
})
public class Users implements UserDetails
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    @Column(name = "fName")
    private String fName;
    @Column(name = "lName")
    private String lName;
    @Column(name = "email")
    private String email;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "health_facility")
    private HealthFacility health_facility;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	private boolean enabled = true;

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}


    public Long getUser_id()
    {
        return user_id;
    }

    public void setUser_id(final Long user_id)
    {
        this.user_id = user_id;
    }

    public String getfName()
    {
        return fName;
    }

    public void setfName(final String fName)
    {
        this.fName = fName;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(final String lName)
    {
        this.lName = lName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(final String userName)
    {
        this.userName = userName;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public HealthFacility getHealth_facility()
    {
        return health_facility;
    }

    public void setHealth_facility(final HealthFacility health_facility)
    {
        this.health_facility = health_facility;
    }

    @Override
    public String toString()
    {
        return "Users [user_id=" + user_id + ", fName=" + fName + ", lName=" + lName + ", email=" + email
            + ", userName=" + userName + ", password=" + password + ", health_facility=" + health_facility
            + "]";
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
		return authorities;
	}

    @Override
    public String getUsername()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
