package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Microboss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MicrobossRepository extends JpaRepository<Microboss, Integer>, JpaSpecificationExecutor<Microboss> {


    @Modifying
    @Query(value = "update microboss set state=(:state), state_desc=(:stateDesc) where id=(:id)", nativeQuery = true)
    void updateValidationStateById(@Param("id") int id, @Param("state") int state, @Param("stateDesc") String stateDesc);
}
