package com.indielist.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jsingh on 15-01-10.
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findUserByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User extends AuditedBaseEntity {

    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "email")
    private String email;

    @Column(name = "last_name")
    private String lastName;
    
    @OneToMany(mappedBy = "user")
    private Set<Ad> ads = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_username", referencedColumnName = "username")}, inverseJoinColumns =
            {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<Role> authorities = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public static User findUserByUsername(String username) {
        return (User) getSingleResultOrNull(em().createNamedQuery("User.findUserByUsername").setParameter("username", username));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
