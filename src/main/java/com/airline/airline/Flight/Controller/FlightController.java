package com.airline.airline.Flight.Controller;

import com.airline.airline.Aircaft.Service.AircarftService;
import com.airline.airline.Flight.Model.Flight;
import com.airline.airline.Flight.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private AircarftService aircarftService;


    public Model addModelAttribute(Model model){
        model.addAttribute("tickets", flightService.findAll());
        model.addAttribute("aircrafts", aircarftService.findAll());
        return  model;
    }
    @GetMapping("/flights")
    public String getAll(Model model){
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights",flights);
            return "/flight/airList";
        }
        @GetMapping("/flightsAdd")
    public String addAirport(Model model){
            model.addAttribute("tickets", flightService.findAll());
            model.addAttribute("aircrafts", aircarftService.findAll());
        model.addAttribute(model);
        return "flight/airAdd";
        }
      @GetMapping("/flightEdit/{id}")
      public String edit(@PathVariable Long id, Model model){
        Flight flights = flightService.getById(id);
        model.addAttribute("flights", flights);
        return "/flight/airEdit";
      }
    @GetMapping("/flightDetail/{id}")
    public String Details(@PathVariable Long id, Model model){
        Flight flights = flightService.getById(id);
        model.addAttribute("flights", flights);
        return "flight/airDetail";
    }

        @PostMapping("/flights")
    public String save(Flight flights){
            flightService.save(flights);
        return "redirect:/flights";
        }
        @RequestMapping(value = "/flight/delete/{id}",method = {RequestMethod.GET,RequestMethod.DELETE})
        public String Delete(@PathVariable Long id){
            flightService.delete(id);
        return "redirect:/flights";
        }
    @RequestMapping(value = "/flight/update/{id}",method = {RequestMethod.GET,RequestMethod.PUT})
    public String Update(Flight flights){
        flightService.save( flights);
        return "redirect:/flights";
    }
    }


