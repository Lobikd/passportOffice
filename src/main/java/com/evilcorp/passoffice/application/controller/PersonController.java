package com.evilcorp.passoffice.application.controller;

import com.evilcorp.passoffice.api.dto.PersonDto;
import com.evilcorp.passoffice.application.dto.PersonRequestDto;
import com.evilcorp.passoffice.application.dto.PersonResponseDto;
import com.evilcorp.passoffice.application.mapper.PersonControllerMapper;
import com.evilcorp.passoffice.application.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonControllerMapper mapper;
    private final PersonService personService;

    @PostMapping("/add-person")
    public UUID addPerson(@RequestBody @Valid PersonRequestDto requestDto) {
        return personService.addPerson(requestDto);
    }

    @GetMapping("/get/id/{personId}")
    public PersonDto getPersonById(@PathVariable UUID personId) {
        PersonResponseDto person = personService.getPersonById(personId);
        return mapper.toPerson(person);
    }

    @GetMapping("/get/passport/{passportNum}")
    public PersonDto getPersonByPassportNum(@PathVariable String passportNum) {
        PersonResponseDto person = personService.getPersonByPassportNum(passportNum);
        return mapper.toPerson(person);
    }

    @DeleteMapping("/delete/id/{personId}")
    public void deletePersonById(@PathVariable UUID personId) {
        personService.deletePersonById(personId);
    }

    @DeleteMapping("/delete/passport/{passportNum}")
    public void deletePersonByPassportNum(@PathVariable String passportNum) {
        personService.deletePersonByPassportNum(passportNum);
    }

    @PatchMapping(value = "/update/")
    public UUID updatePerson(@RequestBody @Valid PersonRequestDto updateRequestDto) {
        return personService.updatePerson(updateRequestDto);
    }

    @PatchMapping(value = "/overwriting/")
    public UUID overwritingPerson(@RequestBody @Valid PersonRequestDto overwritingRequestDto) {
        return personService.overwritingPerson(overwritingRequestDto);
    }
}