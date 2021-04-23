package com.gotta_watch_them_all.app.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean vulgar;
}
