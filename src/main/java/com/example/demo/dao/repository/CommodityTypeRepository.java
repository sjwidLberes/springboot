package com.example.demo.dao.repository;


import com.example.demo.dao.entity.CommodityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommodityTypeRepository extends JpaRepository<CommodityType, Integer>, JpaSpecificationExecutor<CommodityType> {


}
