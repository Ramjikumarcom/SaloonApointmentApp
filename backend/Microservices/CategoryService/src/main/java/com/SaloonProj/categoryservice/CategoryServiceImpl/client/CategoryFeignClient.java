package com.SaloonProj.categoryservice.CategoryServiceImpl.client;

import com.SaloonProj.categoryservice.CategoryDto.CategoryDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATEGORYSERVICE")
public interface CategoryFeignClient {
    @GetMapping("/api/category/salonOwner/saloon/{saloonId}/category/{categoryId}")
    public  ResponseEntity<CategoryDto>getCategoryByIdAndSaloonId(@PathVariable Long categoryId,
                                                                  @PathVariable Long saloonId)
            throws Exception;
}
