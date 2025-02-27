package com.evilcorp.passoffice.application.integration.generator.repository.test;

import com.evilcorp.passoffice.application.entity.PersonEntity;
import com.evilcorp.passoffice.application.integration.generator.repository.PersonJpaRepositoryTestData;
import com.evilcorp.passoffice.application.integration.layer.ApplicationJpaTest;
import com.evilcorp.passoffice.application.repository.PersonJpaRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

class PersonJpaRepositoryTest extends ApplicationJpaTest {

    @Autowired
    private PersonJpaRepository personJpaRepository;
    @Autowired
    private PersonJpaRepositoryTestData personJpaRepositoryTestData;

    private PersonEntity person;

    @PostConstruct
    void init() {
        person = personJpaRepositoryTestData.generateAndSave();
        assertThat(person).isNotNull();
    }

    @Test
    @Transactional
    void generateAndSavePerson() {
        PersonEntity found = personJpaRepository
                .findById(person.getPersonId()).orElseThrow();
        assertThat(found).isNotSameAs(person);

        personJpaRepository.deleteById(found.getPersonId());
    }
}