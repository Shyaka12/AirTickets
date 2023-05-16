package com.airline.airline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @GetMapping("/index")
    public String home() {
        return "/index";
    }

    @GetMapping("/_layout")
    public String layout() {
        return "/_layout";
    }

    @GetMapping("/index2")
    public String index() {
        return "/index2";
    }



    @GetMapping("/Air")
    public  String air (){
        return "/air/index";
    }

    @GetMapping("security")
    public String security(){
        return "/security/index";
    }

}

