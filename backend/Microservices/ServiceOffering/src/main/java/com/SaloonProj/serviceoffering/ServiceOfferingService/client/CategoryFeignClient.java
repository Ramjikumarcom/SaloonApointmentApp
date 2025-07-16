package com.SaloonProj.serviceoffering.ServiceOfferingService.client;

import com.SaloonProj.serviceoffering.OtherDTO.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATEGORYSERVICE")
public interface CategoryFeignClient {
    @GetMapping("/api/category/saloon/{saloonId}/category/{categoryId}")
    public  ResponseEntity<CategoryDto>getCategoryByIdAndSaloonId(@PathVariable Long categoryId,
                                                                  @PathVariable Long saloonId)
            throws Exception;
}
