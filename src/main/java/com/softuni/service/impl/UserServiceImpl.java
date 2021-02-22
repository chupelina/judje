package com.softuni.service.impl;

import com.softuni.model.binding.CurrentUserInfoViewModel;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.enums.RoleEnum;
import com.softuni.model.entity.service.UserServiceModel;
import com.softuni.repository.UserRepository;
import com.softuni.security.CurrentUser;
import com.softuni.service.HomeworkService;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        } else {
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

    @Override
    public List<String> getAllUsersByName() {
        return userRepository.findAllUsernames();
    }

    @Override
    public void changeUserRole(String userName, String roleStr) {
        UserEntity userEntity = userRepository.findByUsername(userName).get();
        if (userEntity.getRole().getName() != RoleEnum.valueOf(roleStr.toUpperCase())) {
            userEntity.setRole(roleService.findRole((roleStr).toUpperCase()));
            userRepository.save(userEntity);
        }

    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public CurrentUserInfoViewModel findCurrentUserInfo(Long id) {
        UserEntity entity = findById(id);
        String sentHomeworks = userRepository.findAllHomeworks(id).stream().map(h->
                h.getExercise().getName()).collect(Collectors.joining(", "));
        CurrentUserInfoViewModel curr = new CurrentUserInfoViewModel();
        curr.setId(id).setEmail(entity.getEmail())
                .setGithubAddress(entity.getGit())
                .setUsername(entity.getUsername())
                .setAllSentHomeworks(sentHomeworks);
        return curr;
    }
}
