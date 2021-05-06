package com.ivan.mygraphql.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String modelCode;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    private String brandName;

    private transient String formattedDate;

}
