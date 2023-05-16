package com.airline.airline.Passenger.Model;




import com.airline.airline.Security.models.Auditable;
import com.airline.airline.tickets.Model.Tickets;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "passengers")
public class Passenger extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "passenger")
    private List<Tickets> tickets;

// constructors, getters, and setters omitted for brevity

    // constructors, getters, and setters omitted for brevity
}

