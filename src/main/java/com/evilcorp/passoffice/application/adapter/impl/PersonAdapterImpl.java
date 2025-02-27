package com.evilcorp.passoffice.application.adapter.impl;

import com.evilcorp.passoffice.application.adapter.PersonAdapter;
import com.evilcorp.passoffice.application.dto.PersonRequestDto;
import com.evilcorp.passoffice.application.dto.PersonResponseDto;
import com.evilcorp.passoffice.application.entity.PersonEntity;
import com.evilcorp.passoffice.application.exception.PersonNotFoundWithPassportNumException;
import com.evilcorp.passoffice.application.exception.PersonNotFoundWithPersonIdException;
import com.evilcorp.passoffice.application.mapper.PersonAdapterMapper;
import com.evilcorp.passoffice.application.patcher.PersonPatcher;
import com.evilcorp.passoffice.application.repository.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonAdapterImpl implements PersonAdapter {

    private final PersonJpaRepository repository;
    private final PersonAdapterMapper mapper;
    private final PersonPatcher personPatcher;

    @Override
    public UUID addPerson(PersonRequestDto requestDto) {
        PersonEntity personEntity = mapper.personRequestDtoToPersonEntity(requestDto);
        PersonEntity personResponseDto = repository.save(personEntity);
        return personResponseDto.getPersonId();
    }

    @Override
    public PersonResponseDto getPersonById(UUID personId) {
        PersonEntity person = repository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundWithPersonIdException(personId));
        return mapper.personEntityToPersonResponseDto(person);
    }

    @Override
    public PersonResponseDto getPersonByPassportNum(String passportNum) {
        PersonEntity person = repository.findByPassportNum(passportNum)
                .orElseThrow(() -> new PersonNotFoundWithPassportNumException(passportNum));
        return mapper.personEntityToPersonResponseDto(person);
    }

    @Override
    public void deletePersonById(UUID personId) {
        PersonEntity deletePerson = repository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundWithPersonIdException(personId));
        repository.delete(deletePerson);
    }

    @Override
    public void deletePersonByPassportNum(String passportNum) {
        PersonEntity deletePerson = repository.findByPassportNum(passportNum)
                .orElseThrow(() -> new PersonNotFoundWithPassportNumException(passportNum));
        repository.delete(deletePerson);
    }

    @Override
    public UUID updatePerson(PersonRequestDto requestDto) {
        PersonEntity updatePersonEntity = mapper.personRequestDtoToPersonEntity(requestDto);
        PersonEntity personResponseEntity = repository.findById(updatePersonEntity.getPersonId())
                .orElseThrow(() -> new PersonNotFoundWithPersonIdException(updatePersonEntity.getPersonId()));
        personPatcher.patchUpdate(personResponseEntity, updatePersonEntity);
        return repository.save(personResponseEntity).getPersonId();
    }

    @Override
    public UUID overwritingPerson(PersonRequestDto overwritingPerson) {
        PersonEntity overwritingPersonEntity = mapper.personRequestDtoToPersonEntity(overwritingPerson);
        PersonEntity personResponseEntity = repository.findById(overwritingPersonEntity.getPersonId())
                .orElseThrow(() -> new PersonNotFoundWithPersonIdException(overwritingPersonEntity.getPersonId()));
        personPatcher.patchOverwriting(personResponseEntity, overwritingPersonEntity);
        return repository.save(personResponseEntity).getPersonId();
    }
}
