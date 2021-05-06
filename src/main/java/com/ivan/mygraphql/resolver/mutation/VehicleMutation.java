package com.ivan.mygraphql.resolver.mutation;

import com.ivan.mygraphql.model.entity.Vehicle;
import com.ivan.mygraphql.service.VehicleService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleMutation implements GraphQLMutationResolver {

    private final VehicleService vehicleService;

    public Vehicle createVehicle(final String type, final String modelCode, final String brandName) {
        return this.vehicleService.createVehicle(type, modelCode, brandName);
    }

}