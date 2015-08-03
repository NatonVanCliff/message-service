package com.springapp.mvc.user.model;

import com.springapp.mvc.contact.model.Contact;
import com.springapp.mvc.message.model.Message;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ms_users")
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Integer roleId = new Integer(Role.ROLE_USER.getId());
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, Integer roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username, String password, String firstName, String lastName, Role role) {
        this(username, password, firstName, lastName, role.getId());
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "roleId", nullable = false)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
