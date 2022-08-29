package com.devsuperior.dscatalog.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryTests {

    @Test
    void categoryShouldHaveCorrectStructure() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("Category");

        assertNotNull(entity.getId());
        assertNotNull(entity.getName());
        assertNotNull(entity.getProducts());
        assertEquals(0, entity.getProducts().size());
    }

    @Test
    void categoryShouldHaveCorrectTimeWhenCategoryIsCreated() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("Category");
        entity.prePersist();
        assertNotNull(entity.getCreatedAt());
    }

    @Test
    void categoryShouldHaveCorrectTimeWhenCategoryIsUpdated() {
        Category entity = new Category();
        entity.setId(1L);
        entity.setName("Category");
        entity.preUpdate();
        assertNotNull(entity.getUpdatedAt());
    }

}
