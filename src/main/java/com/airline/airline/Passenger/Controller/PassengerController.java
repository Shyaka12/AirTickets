package com.airline.airline.Passenger.Controller;

import com.airline.airline.Passenger.Model.Passenger;
import com.airline.airline.Passenger.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    @GetMapping("/passenger")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/passenger/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Passenger> page= passengerService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Passenger> passengers = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("passengers ",passengers );
        return "/passenger/airList";
    }
    @GetMapping("/passenger/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){

        Page<Passenger> page = passengerService.findAllWithSort(field, sortDir, currentPage);
        List<Passenger> passengers = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("passengers ",passengers);
        return "/passenger/airList";
    }
        @GetMapping("/passengerAdd")
    public String addAirport(){
        return "passenger/airAdd";
        }
      @GetMapping("/passengerEdit/{id}")
      public String edit(@PathVariable Long id, Model model){
        Passenger passenger = passengerService.getById(id);
        model.addAttribute("passengers", passenger);
        return "/passenger/airEdit";
      }
    @GetMapping("/passengerDetail/{id}")
    public String Details(@PathVariable Long id, Model model){
       Passenger passenger = passengerService.getById(id);
        model.addAttribute("passengers", passenger);
        return "passenger/airDetail";
    }

        @PostMapping("/passenger")
    public String save(Passenger passenger){
            passengerService.save(passenger);
        return "redirect:/passenger";
        }
        @RequestMapping(value = "/passenger/delete/{id}",method = {RequestMethod.GET,RequestMethod.DELETE})
        public String Delete(@PathVariable Long id){
            passengerService.delete(id);
        return "redirect:/passenger";
        }
    @RequestMapping(value = "/passenger/update/{id}",method = {RequestMethod.GET,RequestMethod.PUT})
    public String Update(Passenger passenger){
        passengerService.save( passenger);
        return "redirect:/passenger";
    }
    }


