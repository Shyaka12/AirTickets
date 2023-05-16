package com.airline.airline.Flight.Model;



import com.airline.airline.Aircaft.Model.Aircraft;
import com.airline.airline.Aircaft.Service.AircarftService;
import com.airline.airline.Airport.Model.Airport;
import com.airline.airline.tickets.Model.Tickets;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aircraft_id" ,insertable=false, updatable=false)
    private Aircraft aircraft;
    private Integer aircraft_id;
    private String departureTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureOut;

    private String arrivalTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalOut;

    private String status;

    private Integer price;

    @OneToMany(mappedBy = "flight")
    private List<Tickets> tickets;


    // constructors, getters and setters omitted for brevity
}


