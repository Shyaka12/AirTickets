package com.airline.airline.Passenger.Repository;

import com.airline.airline.Passenger.Model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
