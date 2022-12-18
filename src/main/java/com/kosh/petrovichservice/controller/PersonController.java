package com.kosh.petrovichservice.controller;

import com.kosh.petrovichservice.dto.PersonDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {

    @GetMapping
    public String getDeclination(@RequestParam String name,
                                 @RequestParam String nameType,
                                 @RequestParam String gender,
                                 @RequestParam String caseName) {
        return "personService";
    }

    @PostMapping
    public String getDeclination(@RequestBody PersonDto personDto) {
        return "personService";
    }
}
