package com.devsuperior.dscatalog.service;

import com.devsuperior.dscatalog.dto.ProductDto;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.service.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductServiceTestes {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private PageImpl<Product> page;
    private Product product;
    ProductDto productDto;
    private Category category;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        product = Factory.createProduct();
        category = Factory.createCategory();
        productDto = Factory.createProductDto();
        page = new PageImpl<>(List.of(product));

        when(repository.save(any())).thenReturn(product);
    }

    @Test
    public void findAllPagedShouldReturnPage() {
        when(repository.findAll((Pageable) any())).thenReturn(page);
        when(categoryRepository.getOne(existingId)).thenReturn(category);

        Mockito.when(repository.find(any(), any(), any())).thenReturn(page);
        when(repository.findById(existingId)).thenReturn(Optional.of(product));
        when(categoryRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Pageable pageable = PageRequest.of(0, 12);
        Page<ProductDto> result = service.findAllPaged(0L, "", pageable);
        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldReturnProductDtoWhenIdExists() {
        Mockito.when(repository.find(any(), any(), any())).thenReturn(page);
        when(repository.findById(existingId)).thenReturn(Optional.of(product));
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        ProductDto result = service.findById(existingId);
        assertNotNull(result);
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
        Mockito.when(repository.find(any(), any(), any())).thenReturn(page);
        when(repository.findById(existingId)).thenReturn(Optional.of(product));
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(nonExistingId));
        verify(repository).findById(nonExistingId);
    }

    @Test
    public void updateShouldReturnProductDtoWhenIdExists() {
        when(repository.getOne(existingId)).thenReturn(product);
        ProductDto result = service.update(existingId, productDto);
        assertNotNull(result);
    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
        when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> service.update(nonExistingId, productDto));
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        doNothing().when(repository).deleteById(existingId);
        assertDoesNotThrow(() -> service.delete(existingId));
        verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingId));
        verify(repository, times(1)).deleteById(nonExistingId);
    }

    @Test
    public void deleteShouldThrowDataIntegrityViolationExceptionWhenDependentIt() {
        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
        assertThrows(DataIntegrityViolationException.class, () -> service.delete(dependentId));
        verify(repository, times(1)).deleteById(dependentId);
    }

}
