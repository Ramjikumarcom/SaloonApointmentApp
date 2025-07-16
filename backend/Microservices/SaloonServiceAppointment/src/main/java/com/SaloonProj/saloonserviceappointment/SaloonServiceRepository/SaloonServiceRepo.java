package com.SaloonProj.saloonserviceappointment.SaloonServiceRepository;

import com.SaloonProj.saloonserviceappointment.Model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SaloonServiceRepo extends JpaRepository<Model,Long> {

    Model findByOwnerId(Long ownerId);


    // this custom query is used for search on the basis of city,name,and address which is case-insensitive

    @Query(
            "select s from Model s where  " +
                    " (lower(s.city) like lower(concat('%',:keyword,'%')) OR " +

                    "lower(s.name) like lower(concat ('%',:keyword,'%')) OR " +

                    "lower(s.state) like lower(concat ('%',:keyword,'%')) OR " +

                    "lower(s.address) like lower(concat ('%',:keyword,'%')) )"
    )

    //@Param("keyword")	Binds the method parameter to the named query parameter
    List<Model> searchSaloons(@Param("keyword") String keyword);


}
