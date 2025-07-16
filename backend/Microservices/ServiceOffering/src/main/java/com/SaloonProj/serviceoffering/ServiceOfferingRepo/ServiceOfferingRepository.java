package com.SaloonProj.serviceoffering.ServiceOfferingRepo;

import com.SaloonProj.serviceoffering.ServiceOfferingModel.ServiceOfferingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ServiceOfferingRepository extends JpaRepository<ServiceOfferingModel,Long> {

    Set<ServiceOfferingModel> findBySaloonId(Long saloonId);

}
