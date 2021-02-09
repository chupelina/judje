package com.softuni.service.impl;

import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.enums.RoleEnum;
import com.softuni.model.entity.service.UserServiceModel;
import com.softuni.repository.UserRepository;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userServiceModel.getUsername());
        userEntity.setEmail(userServiceModel.getEmail());
        userEntity.setGit(userServiceModel.getGit());
        userEntity.setPassword(userServiceModel.getPassword());
        userEntity.setRole(roleService.findRole("USER"));
        userRepository.save(userEntity);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        Optional<UserEntity> user = userRepository.findByUsernameAndPassword(username,password);
        return user.map(u-> modelMapper.map(u, UserServiceModel.class)).orElse(null);
    }
}
