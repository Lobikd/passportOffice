package com.evilcorp.passoffice.application.controller;

import com.evilcorp.passoffice.api.dto.PersonDto;
import com.evilcorp.passoffice.application.dto.PersonRequestDto;
import com.evilcorp.passoffice.application.dto.PersonResponseDto;
import com.evilcorp.passoffice.application.mapper.PersonControllerMapper;
import com.evilcorp.passoffice.application.service.PersonService;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jeasy.random.FieldPredicates.named;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonControllerMockitoTest {

    @InjectMocks
    private PersonController controller;
    @Mock
    private PersonService personService;
    @Spy
    private PersonControllerMapper personControllerMapper = Mappers.getMapper(PersonControllerMapper.class);

    private final EasyRandom easyRandom = new EasyRandom(
            new EasyRandomParameters()
            .stringLengthRange(5, 10)
            .excludeField(named("personId"))
    );

    @Test
    void addPerson() {
        UUID personId = UUID.randomUUID();
        var personRequestDto = easyRandom.nextObject(PersonRequestDto.class);

        doReturn(personId).when(personService).addPerson(personRequestDto);

        var result = controller.addPerson(personRequestDto);

        verify(personService).addPerson(personRequestDto);
        assertThat(personId).isEqualTo(result);
    }

    @Test
    void getPersonById() {
        UUID personId = UUID.randomUUID();
        var personResponseDto = easyRandom.nextObject(PersonResponseDto.class).toBuilder()
                .personId(personId)
                .build();

        var personDto = PersonDto.builder().build();

        doReturn(personResponseDto).when(personService).getPersonById(personId);
        doReturn(personDto).when(personControllerMapper).toPerson(personResponseDto);

        var result = controller.getPersonById(personId);

        verify(personService).getPersonById(personId);
        assertThat(personDto).isEqualTo(result);
    }
}