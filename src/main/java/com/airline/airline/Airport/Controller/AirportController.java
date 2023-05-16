package com.airline.airline.Airport.Controller;

import com.airline.airline.Aircaft.Model.Aircraft;
import com.airline.airline.Aircaft.Service.AircarftService;
import com.airline.airline.Airport.Model.Airport;
import com.airline.airline.Airport.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class AirportController {

    @Autowired
    private AirportService airportService;

//    @GetMapping("/airports")
//    public String getAll(Model model, String keyword){
//        List<Airport> airports;
//        airports = keyword == null? airports =airportService.findAll():airportService.findByKeyword(keyword);
//
//        model.addAttribute("airports",airports);
//            return "/airport/airList";
//        }

    @GetMapping("/airports")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/airports/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Airport> page= airportService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Airport> airports = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("airports",airports);
        return "/airport/airList";
    }
    @GetMapping("/airports/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){

        Page<Airport> page = airportService.findAllWithSort(field, sortDir, currentPage);
        List<Airport> countries = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("countries", countries);
        return "/airport/airList";
    }
        @GetMapping("/airportAdd")
    public String addAirport(){
        return "airport/airAdd";
        }
      @GetMapping("/airportEdit/{id}")
      public String edit(@PathVariable Long id, Model model){
        Airport airports = airportService.getById(id);
        model.addAttribute("airports", airports);
        return "/airport/airEdit";
      }
    @GetMapping("/airportDetail/{id}")
    public String Details(@PathVariable Long id, Model model){
        Airport airports = airportService.getById(id);
        model.addAttribute("airports", airports);
        return "airport/airDetail";
    }

        @PostMapping("/airports")
    public String save(Airport airport){
            airportService.save(airport);
        return "redirect:/airports";
        }
        @RequestMapping(value = "/airports/delete/{id}",method = {RequestMethod.GET,RequestMethod.DELETE})
        public String Delete(@PathVariable Long id){
            airportService.delete(id);
        return "redirect:/airports";
        }
    @RequestMapping(value = "/airports/update/{id}",method = {RequestMethod.GET,RequestMethod.PUT})
    public String Update(Airport airport){
        airportService.save( airport);
        return "redirect:/airports";
    }
    }


