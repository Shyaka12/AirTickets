package com.airline.airline.Aircaft.Controller;

import com.airline.airline.Aircaft.Model.Aircraft;
import com.airline.airline.Aircaft.Service.AircarftService;
import com.airline.airline.Airport.Model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class AircarftController {

    @Autowired
    private AircarftService aircarftService;
//    @GetMapping("/aircrafts")
//    public String getAll(Model model, String keyword){
//        List<Aircraft> aircrafts;
//       if(keyword == null) {
//           aircrafts=aircarftService.findAll();
//       }else {
//           aircrafts= aircarftService.findByKeyword(keyword);
//       }
//        model.addAttribute("aircrafts",aircrafts);
//            return "/air/airList";
//        }

    @GetMapping("/aircrafts")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/aircrafts/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Aircraft> page= aircarftService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Aircraft> aircrafts = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("aircrafts",aircrafts);
        return "/air/airList";
    }
    @GetMapping("/aircrafts/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir){

        Page<Aircraft> page = aircarftService.findAllWithSort(field, sortDir, currentPage);
        List<Aircraft> countries = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("countries", countries);
        return "/air/airList";
    }

        @GetMapping("/aircarfAdd")
    public String addAircarft(Model model){
        model.addAttribute(model);
        return "air/airAdd";
        }
      @GetMapping("/aircarfEdit/{id}")
      public String edit(@PathVariable Long id, Model model){
        Aircraft aircraft = aircarftService.getById(id);
        model.addAttribute("aircraft", aircraft);
        return "/air/airEdit";
      }
    @GetMapping("/aircarfDetail/{id}")
    public String Details(@PathVariable Long id, Model model){
        Aircraft aircraft = aircarftService.getById(id);
        model.addAttribute("aircraft", aircraft);
        return "/air/airDetail";
    }

        @PostMapping("/aircrafts")
    public String save(Aircraft aircraft){
        aircarftService.save(aircraft);
        return "redirect:/aircrafts";
        }
        @RequestMapping(value = "/aircrafts/delete/{id}",method = {RequestMethod.GET,RequestMethod.DELETE})
        public String Delete(@PathVariable Long id){
        aircarftService.delete(id);
        return "redirect:/aircrafts";
        }
    @RequestMapping(value = "/aircrafts/update/{id}",method = {RequestMethod.GET,RequestMethod.PUT})
    public String Update(Aircraft aircraft){
        aircarftService.save(aircraft);
        return "redirect:/aircrafts";
    }
    }


