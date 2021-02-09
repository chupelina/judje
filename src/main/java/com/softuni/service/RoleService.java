package com.softuni.service;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.enums.RoleEnum;

public interface RoleService {
    void initRoles();
    RoleEntity findRole(String name);
}
