package com.ivan.mygraphql.resolver;

import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.model.entity.Vehicle;
import com.ivan.mygraphql.service.VehicleService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class OwnerResolver implements GraphQLResolver<Owner> {

    private final VehicleService vehicleService;

    public OwnerResolver(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Vehicle> getVehicles(Owner owner) {
        log.info("JUMP");
        return vehicleService.getByOwner(owner);
    }

}
