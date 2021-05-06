package com.ivan.mygraphql.resolver.mutation;

import com.ivan.mygraphql.model.entity.Vehicle;
import com.ivan.mygraphql.service.VehicleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    private final VehicleService vehicleService;

    public VehicleMutation(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Vehicle createVehicle(final String type, final String modelCode, final String brandName) {
        return this.vehicleService.createVehicle(type, modelCode, brandName);
    }

}