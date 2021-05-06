package com.ivan.mygraphql.repository;

import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByType(String type);

    List<Vehicle> findByOwner(Owner owner);

}
