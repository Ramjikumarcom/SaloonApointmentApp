package com.SaloonProj.categoryservice.CategoryController;

import com.SaloonProj.categoryservice.CategoryDto.CategoryDto;
import com.SaloonProj.categoryservice.CategoryService.CategoryService;
import com.SaloonProj.categoryservice.SaloonServiceDto.SaloonServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryServiceController {

    private  final CategoryService categoryService;



    @GetMapping("/bysaloon/{saloonId}")

    public ResponseEntity<Set<CategoryDto>>findCategoryBySaloonId(
            @PathVariable Long saloonId
    ) throws Exception {

       return new ResponseEntity<>( categoryService.getAllCategoryBySaloon(saloonId),HttpStatus.OK);

    }


    @GetMapping("/byCategoryId/{categoryId}")
    public ResponseEntity<CategoryDto>findByCategoryId(
            @PathVariable Long categoryId
    ) throws Exception {
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatus.OK);
    }

     @GetMapping("/search")
    public ResponseEntity<List<CategoryDto>>searchCategoryByName(
            @RequestParam("keyword") String keyword
     ){
        // may be in future here i have to implement so permission facility so  that searched result only
         // come from one saloonId

        return new ResponseEntity<>(categoryService.searchCategoryModelByName(keyword),HttpStatus.OK);

     }
    @GetMapping("/saloon/{saloonId}/category/{categoryId}")
    public  ResponseEntity<CategoryDto>getCategoryByIdAndSaloonId(@PathVariable Long categoryId,
                                                             @PathVariable Long saloonId)
            throws Exception {

        CategoryDto categoryDto=categoryService.findByIdAndSaloonId(categoryId,saloonId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }


}
