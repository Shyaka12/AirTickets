package com.airline.airline.Passenger.Service;

import com.airline.airline.Airport.Model.Airport;
import com.airline.airline.Airport.Repository.AirportRepository;
import com.airline.airline.Passenger.Model.Passenger;
import com.airline.airline.Passenger.Repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> findAll(){
        return passengerRepository.findAll();
    }
    public Page<Passenger> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, 3);
        return passengerRepository.findAll(pageable);
    }
    public Page<Passenger> findAllWithSort( String field, String direction , int pageNumber){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1,5, sort);

        return passengerRepository.findAll(pageable);
    }
    public void save(Passenger passenger){
        passengerRepository.save(passenger);
    }
    public void delete(Long id){

        passengerRepository.deleteById(id);
    }

    public Passenger getById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }
//    public List<Passenger> findByKeyword(String keyword){
//        return passengerRepository.findByKeyword(keyword);
//    }
}
