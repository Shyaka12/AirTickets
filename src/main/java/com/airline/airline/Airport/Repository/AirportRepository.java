package com.airline.airline.Airport.Repository;

import com.airline.airline.Airport.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query(value = "select a from Airport a where " +
            "concat(a.name, a.city, a.country) like %?1%")
    List<Airport> findByKeyword(String keyword);
}
