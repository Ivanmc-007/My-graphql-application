package com.ivan.mygraphql.service;

import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Owner> getAll(int count) {
        return ownerRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = { Exception.class })
    public Owner save(Owner owner) {
        owner.getVehicles().forEach(el -> el.setOwner(owner));
        return ownerRepository.save(owner);
    }

    @Transactional(readOnly = true)
    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

}
