package com.kosh.petrovichservice.util;

import com.github.petrovich4j.Gender;

public final class GenderHelper {
    public static Gender extractGender(String gender) {
        if ("androgynous".equals(gender))
            return Gender.Both;
        if ("male".equals(gender))
            return Gender.Male;
        return Gender.Female;
    }
}
