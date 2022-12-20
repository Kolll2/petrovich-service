package com.kosh.petrovichservice.service.impl;

import com.github.petrovich4j.Gender;
import com.github.petrovich4j.Rule;
import com.github.petrovich4j.RuleSet;
import com.kosh.petrovichservice.service.LibraryService;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@Getter
public class LibraryServiceImpl implements LibraryService {

    private RuleSet firstName;
    private RuleSet lastName;
    private RuleSet middleName;

    @SneakyThrows
    @PostConstruct
    @SuppressWarnings("rawtypes")
    public void init() {
        Constructor constructor = new Constructor(LinkedHashMap.class);
        Yaml yaml = new Yaml(constructor);
        LinkedHashMap<String, LinkedHashMap> load = yaml.load(new FileInputStream("src/rules/rules.yml"));
        firstName = extractRuleSet(load.get("firstname"));
        lastName = extractRuleSet(load.get("lastname"));
        middleName = extractRuleSet(load.get("middlename"));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public RuleSet extractRuleSet(LinkedHashMap list) {
        ArrayList<LinkedHashMap<String, Object>> exceptions = (ArrayList<LinkedHashMap<String, Object>>) list.get("exceptions");
        ArrayList<LinkedHashMap<String, Object>> suffixes = (ArrayList<LinkedHashMap<String, Object>>) list.get("suffixes");
        return new RuleSet(extractedRules(exceptions), extractedRules(suffixes));
    }

    @SuppressWarnings("unchecked")
    private Rule[] extractedRules(ArrayList<LinkedHashMap<String, Object>> rulesMap) {
        Rule[] resultExceptions = new Rule[rulesMap.size()];
        for (int i = 0; i < rulesMap.size(); i++) {
            LinkedHashMap<String, Object> m = rulesMap.get(i);

            String libGender = (String) m.get("gender");
            ArrayList<String> libTests = (ArrayList<String>) m.get("test");
            ArrayList<String> libMods = (ArrayList<String>) m.get("mods");

            Gender gender = extractGender(libGender);
            String[] tests = libTests.toArray(new String[0]);
            String[] mods = libMods.toArray(new String[0]);

            resultExceptions[i] = new Rule(gender, tests, mods);
        }
        return resultExceptions;
    }

    private Gender extractGender(String gender) {
        if ("androgynous".equals(gender))
            return Gender.Both;
        if ("male".equals(gender))
            return Gender.Male;
        return Gender.Female;
    }

}
