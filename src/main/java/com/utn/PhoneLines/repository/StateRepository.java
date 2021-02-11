package com.utn.PhoneLines.repository;

import com.utn.PhoneLines.model.City;
import com.utn.PhoneLines.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {
}
