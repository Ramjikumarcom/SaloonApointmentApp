package com.SaloonProj.categoryservice.CategoryServiceImpl;

import com.SaloonProj.categoryservice.CategoryDto.CategoryDto;
import com.SaloonProj.categoryservice.CategoryModel.CategoryModel;
import com.SaloonProj.categoryservice.CategoryRepository.CategoryRepository;
import com.SaloonProj.categoryservice.CategoryService.CategoryService;
import com.SaloonProj.categoryservice.CategoryServiceImpl.client.SaloonServiceFeignClient;
import com.SaloonProj.categoryservice.SaloonServiceDto.SaloonServiceDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public final CategoryRepository categoryRepository;


    public final SaloonServiceFeignClient saloonServiceFeignClient;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto, SaloonServiceDto saloonServiceDto) {
     categoryDto.setSaloonId(saloonServiceDto.getSaloonId());
        System.out.println( "this is SaloonserviceDto" +saloonServiceDto);
     CategoryModel categoryModel=modelMapper.map(categoryDto,CategoryModel.class);

      CategoryModel categoryModel1=  categoryRepository.save(categoryModel);

      return modelMapper.map(categoryModel1,CategoryDto.class);
    }

    @Override
    public Set<CategoryDto> getAllCategoryBySaloon(Long saloonId) throws Exception {

       SaloonServiceDto saloonServiceDto= saloonServiceFeignClient.findSaloonById(saloonId).getBody();
         if (saloonServiceDto==null||saloonServiceDto.getSaloonId()==null) throw
                 new Exception("saloon Not found with this saloonId= "+saloonId);

        Set<CategoryModel>categoryModelupdated=categoryRepository.findBySaloonId(saloonId);

        Set<CategoryDto> categoryDtos=categoryModelupdated.stream().
                map(category->modelMapper.map(category,
                CategoryDto.class)).collect(Collectors.toSet());
       return categoryDtos;

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) throws Exception {
        CategoryModel categoryModel=categoryRepository.findById(categoryId).orElseThrow(()->
                new Exception("category not found with CategoryId="+categoryId));


        return modelMapper.map(categoryModel,CategoryDto.class);
    }

    @Override
    public String deleteCategoryById(Long categoryId,Long saloonId) throws Exception {
        CategoryModel categoryModel=categoryRepository.findById(categoryId).orElseThrow(()->
                new Exception("category not found with CategoryId="+categoryId));

         if(!(categoryModel.getSaloonId().equals(saloonId)))
             throw new Exception("you don't have permission to Delete this category");

         categoryRepository.delete(categoryModel);



        return "Category deleted Succefully";
    }

    @Override
    public List<CategoryDto> searchCategoryModelByName(String keyword) {
        List<CategoryModel> categoryModels=categoryRepository.searchCategoryModelByName(keyword);

        // may be in future here i have to implement so permission facility so  that searched result only
        // come from one saloonId

        List<CategoryDto>categoryDtos=categoryModels.stream().map(categoryModel ->
                modelMapper.map(categoryModel,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto findByIdAndSaloonId(Long categoryId, Long saloonId) throws Exception {
        CategoryModel categoryModel=categoryRepository.findByCategoryIdAndSaloonId(categoryId,saloonId);
        if(categoryModel==null)throw new Exception("Category Not Found");

        return modelMapper.map(categoryModel,CategoryDto.class);
    }


}
