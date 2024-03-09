package com.vedha.repository;

import com.vedha.entity.ApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ApiRepository extends JpaRepository<ApiEntity, Number> {

    Optional<ApiEntity> findByapiName(String apiName);

    @Modifying
    @Transactional
    @Query(value = "update ApiEntity a set a.apiResTime = :apiResTime")
    int updateAllApiResTime(@Param("apiResTime") String apiResTime);
}
