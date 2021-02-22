package com.softuni.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "users")
public class UserEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String git;
    @ManyToOne
    private RoleEntity role;
    @OneToMany(mappedBy = "author")
    private List<HomeworkEntity> homeworks;

    public List<HomeworkEntity> getHomeworks() {
        return homeworks;
    }

    public UserEntity setHomeworks(List<HomeworkEntity> homeworks) {
        this.homeworks = homeworks;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGit() {
        return git;
    }

    public UserEntity setGit(String git) {
        this.git = git;
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(RoleEntity role) {
        this.role = role;
        return this;
    }
}
