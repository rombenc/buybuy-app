package com.ecommerce.buybuy.controller;

import com.ecommerce.buybuy.dto.request.CategoryRequest;
import com.ecommerce.buybuy.dto.request.ProductRequest;
import com.ecommerce.buybuy.dto.response.ProductResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.service.Impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @PostMapping("/products")
    public ResponseEntity<WebResponse<ProductResponse>> createProduct(
            @RequestParam("image") MultipartFile image,
            @RequestBody ProductRequest productRequest) throws IOException {
        WebResponse<ProductResponse> response = productService.createProduct(image, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/products/{productId}")
    public ResponseEntity<WebResponse<ProductResponse>> updateProduct(
            @PathVariable String productId,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestBody ProductRequest productRequest,
            @RequestBody CategoryRequest categoryRequest) throws IOException {
        WebResponse<ProductResponse> response = productService.updateProduct(productId, image, productRequest, categoryRequest);
        return ResponseEntity.ok(response);
    }

}
