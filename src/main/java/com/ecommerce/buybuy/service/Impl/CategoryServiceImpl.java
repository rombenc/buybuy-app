package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.CategoryRequest;
import com.ecommerce.buybuy.dto.response.CategoryResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.Category;
import com.ecommerce.buybuy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;

    //todo: create category
    public WebResponse<Object> createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return WebResponse.builder()
                .statusCode(200)
                .message("Category created successfully")
                .build();
    }

    //todo: update category
    public WebResponse<CategoryResponse> updateCategory (Long id, CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("No category can be found"));
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return WebResponse.<CategoryResponse>builder()
                .statusCode(200)
                .message("Successfully updating category")
                .data(null)
                .build();
    }
    //todo: get category by name
    public WebResponse<CategoryResponse> getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            return WebResponse.<CategoryResponse>builder()
                    .statusCode(404)
                    .message("Category not found")
                    .data(null)
                    .build();
        }
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(category.getName());
        // Tambahkan properti lain dari categoryResponse dari category jika diperlukan
        return WebResponse.<CategoryResponse>builder()
                .statusCode(200)
                .message("Category found")
                .data(categoryResponse)
                .build();
    }
    //todo: get all category
    public List<WebResponse<CategoryResponse>> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> {
                    CategoryResponse categoryResponse = new CategoryResponse();
                    categoryResponse.setId(category.getId());
                    categoryResponse.setName(category.getName());
                    // Tambahkan properti lain dari categoryResponse dari category jika diperlukan

                    return WebResponse.<CategoryResponse>builder()
                            .statusCode(200)
                            .message("Category found")
                            .data(categoryResponse)
                            .build();
                })
                .collect(Collectors.toList());
    }

    //todo: delete category
    public WebResponse<Object> deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return WebResponse.builder()
                .statusCode(200)
                .message("category has been deleted")
                .data(null)
                .build();
    }
}
