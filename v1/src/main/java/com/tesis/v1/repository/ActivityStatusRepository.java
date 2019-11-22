package com.tesis.v1.repository;

import com.tesis.v1.entity.ActivityStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityStatusRepository extends JpaRepository<ActivityStatusEntity, Long> {

}
