package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Welfare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WelfareRepository extends JpaRepository<Welfare, Integer>, JpaSpecificationExecutor<Welfare> {

    @Modifying
    @Query(value = "update welfare set express_number=(:expressNumber), state=3 where oder_id=(:orderId) and open_id=(:openId)", nativeQuery = true)
    void updateExpressNumberByKey(@Param("orderId") String orderId, @Param("openId") String openId, @Param("expressNumber") String expressNumber);
}
