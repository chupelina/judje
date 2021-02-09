package com.softuni.service.impl;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.enums.RoleEnum;
import com.softuni.repository.RoleRepository;
import com.softuni.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void initRoles() {
        if(roleRepository.count()==0){
            RoleEntity admin = new RoleEntity();
            admin.setName(RoleEnum.ADMIN);
            RoleEntity user = new RoleEntity();
            user.setName(RoleEnum.USER);
            roleRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public RoleEntity findRole(String name) {
        return roleRepository.findFirstByName(RoleEnum.valueOf(name)).get();
    }
}
