package com.gotta_watch_them_all.app.unit.infrastructure.dao;

import com.gotta_watch_them_all.app.core.entity.Role;
import com.gotta_watch_them_all.app.core.entity.RoleName;
import com.gotta_watch_them_all.app.infrastructure.dao.UserDaoImpl;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.UserEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper.RoleMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private PasswordEncoder mockEncoder;

    private UserDaoImpl sut;

    @BeforeEach
    void setup() {
        sut = new UserDaoImpl(mockUserRepository, mockEncoder);
    }

    @Test
    void createUser_return_id_of_saved_user() {
        var roles = Set.of(new Role().setName(RoleName.ROLE_USER));
        var userEntity = new UserEntity()
                .setUsername("user name")
                .setEmail("user@email.com")
                .setPassword("passwordEncoded")
                .setRoles(roles.stream().map(RoleMapper::domainToEntity).collect(Collectors.toSet()));
        when(mockEncoder.encode("password")).thenReturn("passwordEncoded");
        when(mockUserRepository.save(userEntity)).thenReturn(new UserEntity().setId(5L));

        var result = sut.createUser("user name", "user@email.com", "password", roles);

        assertThat(result).isEqualTo(5L);
    }
}