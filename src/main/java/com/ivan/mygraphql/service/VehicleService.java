package com.ivan.mygraphql.service;

import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.model.entity.Vehicle;
import com.ivan.mygraphql.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository ;
    }

    @Transactional(rollbackFor = Exception.class)
    public Vehicle createVehicle(final String type,final String modelCode, final String brandName) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        return this.vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicleById(final Long id) {
        return vehicleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getVehicleByType(String type) {
        return vehicleRepository.findByType(type);
    }

    public Set<Vehicle> saveAll(Set<Vehicle> vehicles) {
        return new HashSet<>(vehicleRepository.saveAll(vehicles));
    }

    public List<Vehicle> getByOwner(Owner owner) {
        return vehicleRepository.findByOwner(owner);
    }

}
