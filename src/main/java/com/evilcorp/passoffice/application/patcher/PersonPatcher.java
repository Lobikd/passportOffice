package com.evilcorp.passoffice.application.patcher;

import com.evilcorp.passoffice.application.entity.PersonEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PersonPatcher {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchUpdate(@MappingTarget PersonEntity personEntity, PersonEntity person);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void patchOverwriting(@MappingTarget PersonEntity personEntity, PersonEntity person);
}
