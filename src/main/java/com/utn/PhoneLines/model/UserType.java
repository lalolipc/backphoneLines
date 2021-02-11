package com.utn.PhoneLines.model;

import com.sun.istack.NotNull;
import com.utn.PhoneLines.model.enums.UserTypeEnum;
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
@Table(name="usertypes")
public class UserType {

    public enum EUSERTYPE{
        CLIENT(1), EMPLOYEER(2);;

        private int value;

        public int getValue() {
            return value;
        }

        private EUSERTYPE(int _val){
            this.value= _val;
        }
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idUserType;

    @Enumerated(EnumType.STRING)
    @Column(name="name")
    private UserTypeEnum name;


}
