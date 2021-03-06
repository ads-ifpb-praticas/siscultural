/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural;

import io.github.siscultural.entities.Functionary;
import io.github.siscultural.enums.UserType;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class FunctionaryUser extends User {

    private Long id;
    private String name;
    private String email;
    private UserType userType;

    public FunctionaryUser(Functionary functionary, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

        super(functionary.getName(), functionary.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = functionary.getId();
        this.name = functionary.getName();
        this.email = functionary.getEmail();
        this.userType = functionary.getUserType();

    }

    public FunctionaryUser(Long id, String name, String password, String email, UserType userType, String username, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.name = name;
        this.email = email;
        this.userType = userType;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "FunctionaryUser{" + "id=" + id + ", name=" + name + ", email=" + email + ", userType=" + userType + '}';
    }

}
