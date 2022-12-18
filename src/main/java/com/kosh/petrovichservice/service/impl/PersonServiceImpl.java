package com.kosh.petrovichservice.service.impl;

import com.github.petrovich4j.Case;
import com.github.petrovich4j.Gender;
import com.github.petrovich4j.NameType;
import com.github.petrovich4j.Petrovich;
import com.kosh.petrovichservice.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PersonServiceImpl implements PersonService {

    private Petrovich petrovich;

    @PostConstruct
    private void init() {
        //todo переопределнить библиотеки словарей
        petrovich = new Petrovich();
    }

    public String getDeclination(String name, String nameType, String gender, String caseName) {
        return petrovich.say(name, NameType.valueOf(nameType), Gender.valueOf(gender), Case.valueOf(caseName));
    }
}
