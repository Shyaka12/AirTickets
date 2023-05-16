package com.airline.airline.Flight.Service;

import com.airline.airline.Flight.Model.Flight;
import com.airline.airline.Flight.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findAll(){
        return flightRepository.findAll();
    }
    public void save(Flight flight){
        flightRepository.save(flight);
    }
    public void delete(Long id){

        flightRepository.deleteById(id);
    }

    public Flight getById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }
}
