package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.CategoryRequest;
import com.ecommerce.buybuy.dto.request.ProductRequest;
import com.ecommerce.buybuy.dto.response.ProductResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.Category;
import com.ecommerce.buybuy.entity.Product;
import com.ecommerce.buybuy.entity.Seller;
import com.ecommerce.buybuy.repository.CategoryRepository;
import com.ecommerce.buybuy.repository.ProductRepository;
import com.ecommerce.buybuy.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    public WebResponse<ProductResponse> createProduct(MultipartFile image, ProductRequest productRequest) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(image);

        // Ambil informasi pengguna yang sedang login
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Seller seller = (Seller) sellerRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setProductCode(productRequest.getProductCode());
        product.setProductName(productRequest.getProductName());
        product.setImageUrl(imageUrl);
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setBrand(productRequest.getBrand());
        product.setCategory(category);  // Set kategori dari database

        productRepository.save(product);

        return WebResponse.<ProductResponse>builder()
                .statusCode(200)
                .data(new ProductResponse())
                .message("Successfully added product")
                .build();
    }

    public WebResponse<ProductResponse> updateProduct(String productId, MultipartFile image, ProductRequest productRequest, CategoryRequest categoryRequest) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(image);
            product.setImageUrl(imageUrl);
        }

        product.setProductCode(productRequest.getProductCode());
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setBrand(productRequest.getBrand());
        product.setCategory(categoryRepository.findById(categoryRequest.getId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        productRepository.save(product);

        return WebResponse.<ProductResponse>builder()
                .statusCode(200)
                .data(new ProductResponse())
                .message("Successfully updated product")
                .build();
    }
}
