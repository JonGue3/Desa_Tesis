package com.tesis.v1.repository;

import com.tesis.v1.entity.ProfileEntity;
import com.tesis.v1.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> getTransactionEntitiesByProfileEntitySet(ProfileEntity profileEntity);
}
