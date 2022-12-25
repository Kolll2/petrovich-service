package com.kosh.petrovichservice.service;

public interface PersonService {

    String getDeclination(String name, String nameType, String gender, String caseName);

    void updateDictionaries();
}
