package com.utn.PhoneLines.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import com.utn.PhoneLines.model.enums.LineStatus;
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
@Table(name="phones")
public class Phone {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idPhone;

    @NotNull
    private String number;

   /* @NotNull
    private Integer id_phone_type;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_phone_type")
    private PhoneType phoneType;

    @OneToMany(mappedBy = "phone")
    private List<Invoice> listInvoices;


    /*@OneToMany(mappedBy = "phone")
    private List<Call> listCalls;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCity")
    private City city;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status" ,columnDefinition = "varchar(50) default 'enabled'")
    private LineStatus status = LineStatus.ENABLED;

    @NotNull
    private boolean active;
}
