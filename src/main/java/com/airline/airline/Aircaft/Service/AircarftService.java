package com.airline.airline.Aircaft.Service;

import com.airline.airline.Aircaft.Model.Aircraft;
import com.airline.airline.Aircaft.Repository.AircarftRepository;
import com.airline.airline.Airport.Model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircarftService {
    @Autowired
    private AircarftRepository aircarftRepository;

    public List<Aircraft> findAll(){
        return aircarftRepository.findAll();
    }
    public Page<Aircraft> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, 3);
        return aircarftRepository.findAll(pageable);
    }
    public Page<Aircraft> findAllWithSort( String field, String direction , int pageNumber){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1,5, sort);

        return aircarftRepository.findAll(pageable);
    }
    public void save(Aircraft aircraft){
        aircarftRepository.save(aircraft);
    }
    public void delete(Long id){
        aircarftRepository.deleteById(id);
    }

    public Aircraft getById(Long id) {
        return aircarftRepository.findById(id).orElse(null);
    }

    public List<Aircraft> findByKeyword(String keyword){
        return aircarftRepository.findByKeyword(keyword);
    }


}
