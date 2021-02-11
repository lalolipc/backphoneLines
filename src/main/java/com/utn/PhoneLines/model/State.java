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
@Table(name="states")
public class State {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idState;

    @NotNull
    private String name;


    @OneToMany(fetch=FetchType.LAZY, mappedBy="state", cascade=CascadeType.ALL)
    @JsonBackReference(value="listCities")
    private List<City> listCities;

}
