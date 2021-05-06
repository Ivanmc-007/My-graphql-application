package com.ivan.mygraphql.resolver.query;

import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.service.OwnerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OwnerQuery implements GraphQLQueryResolver {

    private final OwnerService ownerService;

    public OwnerQuery(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public List<Owner> getOwners(int count) {
        return ownerService.getAll(count);
    }

    public Owner createOwner(Owner owner) {
        return ownerService.save(owner);
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerService.getOwnerById(id);
    }

}
