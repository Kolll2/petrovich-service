package com.kosh.petrovichservice.controller;

import com.kosh.petrovichservice.dto.PersonDto;
import com.kosh.petrovichservice.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/person")
@Api("Контроллер склонения Имени/Фамилии/Отчества")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    @ApiOperation(value = "Склоняет переданный переданную строку согласно параметрам",
            notes = "Возвращает склоненную строку согласно параметрам")
    public String getDeclination(@RequestParam @ApiParam(name = "name", value = "Склоняемая строка", example = "Максим") String name,
                                 @RequestParam @ApiParam(name = "nameType", value = "Тип строки", example = "FirstName",
                                         allowableValues = "FirstName,LastName,PatronymicName") String nameType,
                                 @RequestParam @ApiParam(name = "gender", value = "Пол", example = "Male",
                                         allowableValues = "Male,Female,Both") String gender,
                                 @RequestParam @ApiParam(name = "caseName", value = "Склонение", example = "Dative",
                                         allowableValues = "Genitive,Dative,Accusative,Instrumental,Prepositional") String caseName) {
        return personService.getDeclination(name, nameType, gender, caseName);
    }

    @PostMapping
    @ApiOperation(value = "Склоняет переданный переданную строку согласно параметрам (PersonDto)",
            notes = "Возвращает склоненную строку согласно параметрам")
    public String getDeclination(@RequestBody @ApiParam(value = "Person dto", required = true) PersonDto personDto) {
        return personService.getDeclination(
                personDto.getName(),
                personDto.getNameType(),
                personDto.getGender(),
                personDto.getCaseType()
        );
    }
}
