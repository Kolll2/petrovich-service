package com.kosh.petrovichservice.controller;

import com.kosh.petrovichservice.dto.PersonDto;
import com.kosh.petrovichservice.service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getDeclination(@RequestParam String name,
                                 @RequestParam String nameType,
                                 @RequestParam String gender,
                                 @RequestParam String caseName) {
        return personService.getDeclination(name, nameType, gender, caseName);
    }

    @PostMapping
    public String getDeclination(@RequestBody PersonDto personDto) {
        return personService.getDeclination(
                personDto.getName(),
                personDto.getNameType(),
                personDto.getGender(),
                personDto.getCaseType()
        );
    }
}
