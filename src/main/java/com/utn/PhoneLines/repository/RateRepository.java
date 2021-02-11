package com.utn.PhoneLines.repository;

import com.utn.PhoneLines.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate,Integer> {
    @Query(value = "select t.* from rates r where r.id_city_from = ?1 and r.id_city_to = ?2 ", nativeQuery = true)
    Rate getRatesbyCity(Integer idCityFrom, Integer idCityTo);
}
