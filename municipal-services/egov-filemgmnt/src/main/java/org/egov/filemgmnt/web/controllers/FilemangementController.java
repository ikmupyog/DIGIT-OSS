package org.egov.filemgmnt.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/v1")

public class FilemangementController {

    @GetMapping(value="/_search")
    public String getSearch() {
       return "hai";
    }
    
    
}
