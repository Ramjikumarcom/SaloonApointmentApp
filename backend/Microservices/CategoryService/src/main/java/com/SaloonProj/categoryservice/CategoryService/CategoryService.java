package com.SaloonProj.categoryservice.CategoryService;

import com.SaloonProj.categoryservice.CategoryDto.CategoryDto;
import com.SaloonProj.categoryservice.SaloonServiceDto.SaloonServiceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto, SaloonServiceDto saloonServiceDto);

    Set<CategoryDto> getAllCategoryBySaloon(Long saloonId) throws Exception;


    CategoryDto getCategoryById(Long categoryId) throws Exception;


    String  deleteCategoryById(Long categoryId,Long saloonId) throws Exception;

    List<CategoryDto>searchCategoryModelByName(String keyword);

    CategoryDto findByIdAndSaloonId(Long categoryId,Long saloonId) throws Exception;
}
