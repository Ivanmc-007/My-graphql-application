package com.ivan.mygraphql.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private boolean inBlacklist;

    @NonNull
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="client")
    private Set<Payment> payments = new HashSet<>();

}
