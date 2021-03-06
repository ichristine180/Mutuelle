package com.mutuelle.demo.model.security;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_roles")
public class UserRole
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    public UserRole(final User user, final Role role)
    {
        this.user = user;
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

    public UserRole()
    {
    }

    public long getUserRoleId()
    {
        return userRoleId;
    }

    public void setUserRoleId(final long userRoleId)
    {
        this.userRoleId = userRoleId;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(final Role role)
    {
        this.role = role;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(final User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("UserRole{");
        sb.append("userRoleId=").append(userRoleId);
        sb.append(", user=").append(user);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}