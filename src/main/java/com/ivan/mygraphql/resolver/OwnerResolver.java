package com.ivan.mygraphql.resolver;

import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.model.entity.Vehicle;
import com.ivan.mygraphql.service.VehicleService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OwnerResolver implements GraphQLResolver<Owner> {

    private final VehicleService vehicleService;

    public List<Vehicle> getVehicles(Owner owner) {
         return vehicleService.getByOwner(owner);
    }

}
