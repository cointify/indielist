package com.indielist.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author jsingh on 15-01-11.
 */
@Entity
@Table(name="roles")
@NamedQueries({
        @NamedQuery(name = "Role.findRoleByRoleName", query = "SELECT r FROM Role r WHERE r.name = :roleName")
})
public class Role extends BaseEntity implements GrantedAuthority {

    @Id
    @Column(name = "name")
    private String name;

    // Available Roles
    public static final String ADMINISTRATOR = "ROLE_ADMINISTRATOR";
    public static final String USER = "ROLE_USER";

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public static Role findRoleByRoleName(String roleName) {
        return (Role) getSingleResultOrNull(em().createNamedQuery("Role.findRoleByRoleName").setParameter("roleName", roleName));
    }
}

