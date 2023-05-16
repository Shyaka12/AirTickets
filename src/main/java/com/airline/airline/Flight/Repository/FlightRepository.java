package com.airline.airline.Flight.Repository;

import com.airline.airline.Flight.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository <Flight ,Long> {
}
