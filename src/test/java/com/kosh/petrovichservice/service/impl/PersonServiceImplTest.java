package com.kosh.petrovichservice.service.impl;

import com.kosh.petrovichservice.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonServiceImplTest {

    @Autowired
    protected PersonService personService;

    @Test
    void serviceIsNotNull() {
        assertNotNull(personService);
    }

    @Test
    void getDeclination() {
        //first simple test
        assertEquals(personService.getDeclination("Максим", "FirstName", "Male", "Dative"), "Максиму");
    }

    @Test
    public void testFemaleFirstNames() throws Exception {
        List<String[]> testCases = load("first_names_female.txt");
        for (String[] test : testCases) {
            check(test, "FirstName", "Female");
        }
    }

    @Test
    public void testMaleFirstNames() throws Exception {
        for (String[] test : load("first_names_male.txt")) {
            check(test, "FirstName", "Male");
        }
    }

    @Test
    public void testFemaleLastNames() throws Exception {
        for (String[] test : load("last_names_female.txt")) {
            check(test, "LastName", "Female");
        }
    }

    @Test
    public void testMaleLastNames() throws Exception {
        for (String[] test : load("last_names_male.txt")) {
            check(test, "LastName", "Male");
        }
    }

    @Test
    public void testFemalePatronymicNames() throws Exception {
        for (String[] test : load("patronymic_names_female.txt")) {
            check(test, "PatronymicName", "Female");
        }
    }

    @Test
    public void testMalePatronymicNames() throws Exception {
        for (String[] test : load("patronymic_names_male.txt")) {
            check(test, "PatronymicName", "Male");
        }
    }

    @Test
    public void checkLatinName() {
        check(new String[]{"John", "John", "John", "John", "John", "John"}, "FirstName", "Male");
    }

    private void check(String[] test, String type, String gender) {
        assertEquals(personService.getDeclination(test[0], type, gender, "Genitive"), test[1], "Genitive case, " + gender + ":");
        assertEquals(personService.getDeclination(test[0], type, gender, "Dative"), test[2], "Dative case, " + gender + ":");
        assertEquals(personService.getDeclination(test[0], type, gender, "Accusative"), test[3], "Accusative case, " + gender + ":");
        assertEquals(personService.getDeclination(test[0], type, gender, "Instrumental"), test[4], "Instrumental case, " + gender + ":");
        assertEquals(personService.getDeclination(test[0], type, gender, "Prepositional"), test[5], "Prepositional case, " + gender + ":");
    }

    private static List<String[]> load(String fileName) throws IOException {
        try (InputStream is = PersonServiceImplTest.class.getResourceAsStream("/" + fileName)) {
            List<String[]> res = new ArrayList<>();
            for (Scanner scanner = new Scanner(Objects.requireNonNull(is), StandardCharsets.UTF_8); scanner.hasNext(); ) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    res.add(line.split(","));
                }
            }
            return res;
        }
    }
}