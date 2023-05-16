package com.airline.airline.Airport.Service;

import com.airline.airline.Aircaft.Model.Aircraft;
import com.airline.airline.Aircaft.Repository.AircarftRepository;
import com.airline.airline.Airport.Model.Airport;
import com.airline.airline.Airport.Repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> findAll(){
        return airportRepository.findAll();
    }
    public Page<Airport> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, 3);
        return airportRepository.findAll(pageable);
    }
    public Page<Airport> findAllWithSort( String field, String direction , int pageNumber){
       Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
               Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1,5, sort);

        return airportRepository.findAll(pageable);
    }
    public void save(Airport airport){
        airportRepository.save(airport);
    }
    public void delete(Long id){

        airportRepository.deleteById(id);
    }

    public Airport getById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public List<Airport> findByKeyword(String keyword){
        return airportRepository.findByKeyword(keyword);
    }
}
