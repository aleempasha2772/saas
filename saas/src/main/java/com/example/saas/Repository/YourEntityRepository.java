package com.example.saas.Repository;

import com.example.saas.Model.YourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YourEntityRepository extends JpaRepository<YourEntity, Long> {
}
