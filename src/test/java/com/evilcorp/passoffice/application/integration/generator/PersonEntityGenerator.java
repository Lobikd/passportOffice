package com.evilcorp.passoffice.application.integration.generator;

import com.evilcorp.passoffice.application.entity.PersonEntity;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class PersonEntityGenerator {

    private final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .stringLengthRange(10, 15)
                    .randomize(BigDecimal.class, () -> BigDecimal.valueOf(ThreadLocalRandom.current().nextLong(9999999999999L))
                            .divide(BigDecimal.valueOf(Math.pow(10, 5)), 5, RoundingMode.HALF_UP))
    );

    public PersonEntity nextPersonEntity() {
        return random.nextObject(PersonEntity.class);
    }
}