package com.tesis.v1.repository;

import com.tesis.v1.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

}
