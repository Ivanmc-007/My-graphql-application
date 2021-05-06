package com.ivan.mygraphql.repository;

import com.ivan.mygraphql.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
