package com.evilcorp.passoffice.application.integration.generator.repository;

import com.evilcorp.passoffice.application.entity.PersonEntity;
import com.evilcorp.passoffice.application.integration.generator.PersonEntityGenerator;
import com.evilcorp.passoffice.application.repository.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonJpaRepositoryTestData {

    private final PersonJpaRepository personJpaRepository;
    private final PersonEntityGenerator personEntityGenerator = new PersonEntityGenerator();

    public PersonEntity generateAndSave() {
        return savePerson();
    }

    private PersonEntity savePerson() {
        PersonEntity personEntity = personEntityGenerator.nextPersonEntity();
        personEntity = personJpaRepository.save(personEntity);
        return personEntity;
    }
}
