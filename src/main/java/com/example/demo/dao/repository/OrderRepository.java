package com.example.demo.dao.repository;


import com.example.demo.dao.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Oder, Integer>, JpaSpecificationExecutor<Oder> {

}
