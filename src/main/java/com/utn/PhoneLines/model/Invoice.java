package com.utn.PhoneLines.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idInvoice;

    @Column(name="date_invoice")
    private LocalDateTime dateInvoice;

    @NotNull
    private float costPrice;
    @NotNull
    private float totalPrice;


    @Column(name="due_date")
    private LocalDateTime dueDate;
    @NotNull
    private Integer callsAmount;

    @NotNull
    private boolean paid;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPhone")
    private Phone phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;
}