package com.utn.PhoneLines.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="rates")
public class Rate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idRate;

    @NotNull
    private float salePrice;
    @NotNull
    private float costPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCityFrom")
    private City cityFrom;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idCityTo")
    private City cityTo;

    @OneToMany(mappedBy = "rate")
    private List<Call> listCalls;

}

