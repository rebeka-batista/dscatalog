package com.devsuperior.dscatalog.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleTests {

    @Test
    void roleShouldHaveCorrectStructure() {
        Role entity = new Role();
        entity.setId(1L);
        entity.setAuthority("admin");

        assertNotNull(entity.getId());
        assertNotNull(entity.getAuthority());
    }

}
