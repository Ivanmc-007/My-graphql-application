package com.ivan.mygraphql.resolver.query;

import com.ivan.mygraphql.model.entity.Vehicle;
import com.ivan.mygraphql.service.VehicleService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

    private final VehicleService vehicleService;

    public VehicleQuery(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Vehicle> getAllVehicles() {
        return this.vehicleService.getAllVehicles();
    }

    public Optional<Vehicle> getVehicleById(final Long id) {
        return vehicleService.getVehicleById(id);
    }

    public List<Vehicle> getVehiclesByType(final String type) {
        return vehicleService.getVehicleByType(type);
    }

}
