package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>, JpaSpecificationExecutor<Commodity> {


    @Modifying
    @Query(value = "update commodity set on_shelf=(:onShelf) where id=(:id)", nativeQuery = true)
    void updateOnShelfById(@Param("id") int id, @Param("onShelf") boolean onShelf);
}
