package com.tesis.v1.repository;

import com.tesis.v1.entity.ActivityPointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityPointsRepository extends JpaRepository<ActivityPointsEntity, Long> {
}
