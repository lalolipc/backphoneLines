package com.utn.PhoneLines.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name="calls")
public class Call {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_call")
    private Integer idCall;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_origin_phone")
    private Phone originPhone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_destination_phone")
    private Phone destinationPhone;
    @Column(name="date_call")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCall;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rate")
    private Rate rate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;


    private Integer duration;

    private double totalPrice;

    private double costPrice;

    private double salePrice;

    private String numberOrigin;

    private String numberDestination;

    private String cityOrigin;

    private String cityDestination;


}