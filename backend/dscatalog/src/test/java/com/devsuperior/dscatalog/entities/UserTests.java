package com.devsuperior.dscatalog.entities;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTests {

    private Set<Role> role = new HashSet<>();

    @Test
    void userShouldHaveCorrectStructure() {
        User entity = new User();
        entity.setId(1L);
        entity.setFirstName("first");
        entity.setLastName("last");
        entity.setEmail("teste@email.com");
        entity.setPassword("123456");

        assertNotNull(entity.getId());
        assertNotNull(entity.getFirstName());
        assertNotNull(entity.getLastName());
        assertNotNull(entity.getEmail());
        assertNotNull(entity.getPassword());
        assertNotNull(entity.getRoles());

    }

}
