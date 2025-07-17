package com.example.PriceWatch.services;

import com.example.PriceWatch.entities.CategoryEntity;
import com.example.PriceWatch.entities.ProductEntity;
import com.example.PriceWatch.repositories.CategoryRepository;
import com.example.PriceWatch.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // crear un producto
    public ProductEntity createProduct(ProductEntity productEntity) {
        CategoryEntity category = categoryRepository.findById(productEntity.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + productEntity.getCategory().getId()));
        productEntity.setCategory(category);

        return productRepository.save(productEntity);
    }

    // obtener un producto por id
    public ProductEntity getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
    }

    // obtener todos los productos disponible por nombre
    public List<ProductEntity> getAllByName(String name) {
        return productRepository.findAllByNameProduct(name);
    }

    // obtener todos los productos disponibles
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // obtener productos por categoría
    public List<ProductEntity> getProductsByCategory(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }

    // obtener productos por tienda
    public List<ProductEntity> getProductsByStore(Long storeId) {
        return productRepository.findDistinctProductsByStoreId(storeId);
    }

    // actualizar un producto
    public ProductEntity updateProduct(ProductEntity productEntity) {
        ProductEntity existingProduct = productRepository.findById(productEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productEntity.getId()));

        existingProduct.setNameProduct(productEntity.getNameProduct());
        existingProduct.setDescription(productEntity.getDescription());
        existingProduct.setCategory(productEntity.getCategory());

        return productRepository.save(existingProduct);
    }

    // eliminar un producto
    public void deleteProduct(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        productRepository.delete(product);
    }
}
