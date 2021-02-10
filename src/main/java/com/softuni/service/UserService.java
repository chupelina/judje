package com.softuni.service;

import com.softuni.model.entity.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String> getAllUsersByName();

    void changeUserRole(String userName, String roleStr);
}
