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
@Table(name="cities")

public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idCity;

    @NotNull
    private String name;

    @NotNull
    private String prefix;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_state")
    private State state;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="city", cascade=CascadeType.ALL)
    @JsonBackReference(value="listUsers")
    private List<User> listUsers;

    @OneToMany(mappedBy = "cityFrom")
    @JsonBackReference(value="listRatesFrom")
    private List<Rate> listRatesFrom;

    @OneToMany(mappedBy = "cityTo")
    @JsonBackReference(value="listRatesTo")
    private List<Rate> listRatesTo;

}
