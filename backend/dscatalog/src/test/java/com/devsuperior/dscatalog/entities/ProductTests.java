package com.devsuperior.dscatalog.entities;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTests {

    private Set<Category> categories = new HashSet<>();

    @Test
    void productShouldHaveCorrectStructureWithoutCategories() {
        Product entity = new Product();
        entity.setId(1L);
        entity.setName("Product");
        entity.setDescription("An amazing product");
        entity.setPrice(1000.0);
        entity.setImgUrl("image.com.br");
        entity.setDate(Instant.now());

        assertNotNull(entity.getId());
        assertNotNull(entity.getName());
        assertNotNull(entity.getDescription());
        assertNotNull(entity.getPrice());
        assertNotNull(entity.getImgUrl());
        assertNotNull(entity.getDate());
    }

    @Test
    void productShouldHaveCorrectStructureWithCategories() {
        Product entity = new Product();
        entity.setId(1L);
        entity.setName("Product");
        entity.setDescription("An amazing product");
        entity.setPrice(1000.0);
        entity.setImgUrl("image.com.br");
        entity.setDate(Instant.now());
        entity.setCategories(categories);

        assertNotNull(entity.getId());
        assertNotNull(entity.getName());
        assertNotNull(entity.getDescription());
        assertNotNull(entity.getPrice());
        assertNotNull(entity.getImgUrl());
        assertNotNull(entity.getDate());
        assertNotNull(entity.categories);
    }

}
