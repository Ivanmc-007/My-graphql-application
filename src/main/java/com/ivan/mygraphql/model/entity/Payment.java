package com.ivan.mygraphql.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

}
