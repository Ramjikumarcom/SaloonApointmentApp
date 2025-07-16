package com.SaloonProj.categoryservice.CategoryController;

import com.SaloonProj.categoryservice.CategoryDto.CategoryDto;
import com.SaloonProj.categoryservice.CategoryService.CategoryService;
import com.SaloonProj.categoryservice.CategoryServiceImpl.client.SaloonServiceFeignClient;
import com.SaloonProj.categoryservice.SaloonServiceDto.SaloonServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category/salonOwner")
public class SaloonCategoryController {
  private  final CategoryService categoryService;

  private final SaloonServiceFeignClient saloonServiceFeignClient;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto,
                                                      @RequestHeader("Authorization")String token) throws Exception {
        SaloonServiceDto saloonServiceDto=saloonServiceFeignClient.getSaloonbyOwner(token).getBody();
        CategoryDto categoryDto1= categoryService.createCategory(categoryDto, saloonServiceDto);
        return new ResponseEntity<>(categoryDto1,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{categoryId}")
    public  ResponseEntity<String>deleteCategory(@PathVariable Long categoryId
    ,@RequestHeader("Authorization")String token) throws Exception {
        SaloonServiceDto saloonServiceDto=saloonServiceFeignClient.getSaloonbyOwner(token).getBody();
        categoryService.deleteCategoryById(categoryId,saloonServiceDto.getSaloonId());
        return new ResponseEntity<>("Category Deleted Succesfully",HttpStatus.OK);
    }
}
