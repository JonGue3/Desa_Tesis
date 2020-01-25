package com.tesis.v1.repository;

import com.tesis.v1.entity.MenuEntity;
import com.tesis.v1.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

    List<MenuEntity> getMenuEntitiesByTransactionEntity_ProfileEntitySet(ProfileEntity profileEntity);
}
