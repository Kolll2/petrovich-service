package com.kosh.petrovichservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PersonDto {
    @ApiModelProperty(name = "String for declination", value = "Склоняемая строка", example = "Максим", required = true)
    private String name;
    @ApiModelProperty(name = "Enum name type", value = "Тип строки", example = "FirstName", required = true,
            allowableValues = "FirstName,LastName,PatronymicName")
    private String nameType;
    @ApiModelProperty(name = "Пол", value = "Пол", example = "Male", required = true, allowableValues = "Male,Female,Both")
    private String gender;
    @ApiModelProperty(name = "Склонение", value = "Склонение", example = "Dative", required = true,
            allowableValues = "Genitive,Dative,Accusative,Instrumental,Prepositional")
    private String caseType;
}
