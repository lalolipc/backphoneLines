package com.utn.PhoneLines.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.json.JsonParser;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CallInfraestructure {


    String numberOrigin;
    String numberDestination;
    Integer duration;
    @JsonProperty
    String callDate;
    //LocalTime timeDate;

}

