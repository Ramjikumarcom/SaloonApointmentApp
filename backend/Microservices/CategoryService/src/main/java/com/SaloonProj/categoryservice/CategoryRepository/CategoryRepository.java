package com.SaloonProj.categoryservice.CategoryRepository;

import com.SaloonProj.categoryservice.CategoryDto.CategoryDto;
import com.SaloonProj.categoryservice.CategoryModel.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoryModel,Long> {

    Set<CategoryModel> findBySaloonId(Long saloonId);


    @Query(
            "select c from CategoryModel c where "+
                    "(lower(c.name) like lower(concat('%',:keyword,'%')))"
    )
    List<CategoryModel>searchCategoryModelByName(@Param("keyword") String keyword);



    CategoryModel findByCategoryIdAndSaloonId(Long categoryId,Long saloonId);
}
