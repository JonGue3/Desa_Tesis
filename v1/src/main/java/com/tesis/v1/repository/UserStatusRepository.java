    package com.tesis.v1.repository;

import com.tesis.v1.entity.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {

    public UserStatusEntity findByIdUserStatus (long idUserStatus);
}
