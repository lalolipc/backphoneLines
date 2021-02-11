package com.utn.PhoneLines.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idUser;

    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String dni;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    //cuando consultamos uno tenga estado del otro

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_type")
    private UserType userType;

    @Column(name = "active", columnDefinition = "bool default true")
    private Boolean active;




}
