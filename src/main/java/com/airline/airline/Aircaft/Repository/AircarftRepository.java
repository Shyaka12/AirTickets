package com.airline.airline.Aircaft.Repository;

import com.airline.airline.Aircaft.Model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircarftRepository  extends JpaRepository<Aircraft, Long> {
    @Query(value = "select r from Aircraft r  where" +" r.name LIKE %?1% or r.model like %?1% or r.manufacturer like %?1% ")
    List<Aircraft> findByKeyword(String keyword);
}
