package com.ivan.mygraphql.resolver.mutation;

import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.service.OwnerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OwnerMutation implements GraphQLMutationResolver {

    private final OwnerService ownerService;

    public Owner createOwner(Owner owner) {
        return ownerService.save(owner);
    }

    public List<Owner> getAll(int count) {
        return ownerService.getAll(count);
    }

}
