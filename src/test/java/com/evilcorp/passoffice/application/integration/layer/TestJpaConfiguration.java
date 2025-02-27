package com.evilcorp.passoffice.application.integration.layer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan({
        "com.evilcorp.passoffice.application.integration.generator"
})
public class TestJpaConfiguration {
}
