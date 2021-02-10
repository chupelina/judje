package com.softuni.service.impl;

import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.service.UserServiceModel;
import com.softuni.repository.UserRepository;
import com.softuni.security.CurrentUser;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CurrentUser currentUser;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.currentUser = currentUser;
    }


    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userServiceModel.getUsername());
        userEntity.setEmail(userServiceModel.getEmail());
        userEntity.setGit(userServiceModel.getGit());
        userEntity.setPassword(userServiceModel.getPassword());
        if (userRepository.count() == 0) {
            userEntity.setRole(roleService.findRole("ADMIN"));
        }else{
            userEntity.setRole(roleService.findRole("USER"));
        }
        userRepository.save(userEntity);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(username, password);
        return user.map(u -> modelMapper.map(u, UserServiceModel.class)).orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser.setId(userServiceModel.getId())
                .setUsername(userServiceModel.getUsername())
                .setRole(userServiceModel.getRole().getName());
    }

    @Override
    public void logout() {
        currentUser.setUsername(null).setRole(null).setId(null);
    }
}
