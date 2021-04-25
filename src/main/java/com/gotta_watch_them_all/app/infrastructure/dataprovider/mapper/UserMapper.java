package com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper;

import com.gotta_watch_them_all.app.core.entity.User;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserEntity domainToEntity(User domain) {
        return new UserEntity()
                .setId(domain.getId())
                .setEmail(domain.getEmail())
                .setUsername(domain.getName())
                .setPassword(domain.getPassword())
                .setRoles(domain.getRoles()
                        .stream()
                        .map(RoleMapper::domainToEntity)
                        .collect(Collectors.toSet()))
                .setVulgar(domain.isVulgar());
    }
}
