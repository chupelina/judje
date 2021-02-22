package com.softuni.model.binding;

import java.util.List;

public class CurrentUserInfoViewModel {
    private Long id;
    private String username;
    private String allSentHomeworks;
    private String email;
    private String githubAddress;

    public Long getId() {
        return id;
    }

    public CurrentUserInfoViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUserInfoViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAllSentHomeworks() {
        return allSentHomeworks;
    }

    public CurrentUserInfoViewModel setAllSentHomeworks(String allSentHomeworks) {
        this.allSentHomeworks = allSentHomeworks;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CurrentUserInfoViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGithubAddress() {
        return githubAddress;
    }

    public CurrentUserInfoViewModel setGithubAddress(String githubAddress) {
        this.githubAddress = githubAddress;
        return this;
    }
}
