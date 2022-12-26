package com.kosh.petrovichservice.controller;

import com.kosh.petrovichservice.dto.RuleDto;
import com.kosh.petrovichservice.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/lib")
@Api("Контроллер обновления правил склонения")
@AllArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;


    @PostMapping
    @ApiOperation(value = "Обновляет правило склонения согласно параметрам",
            notes = "Обновляет правило склонения согласно параметрам")
    public String updateDictionaries(@RequestBody @ApiParam(value = "Person dto", required = true) RuleDto ruleDto) {
        libraryService.updateDictionaries(ruleDto.getNameType(), ruleDto.getGender(), ruleDto.getTest(), ruleDto.getMod());
        return "";
    }
}
