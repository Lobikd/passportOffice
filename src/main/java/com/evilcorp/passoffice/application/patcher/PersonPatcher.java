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

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchUpdate(@MappingTarget PersonEntity actionEntity, PersonEntity action);

    @BeanMapping(ignoreByDefault = true)
    void patchOverwriting(@MappingTarget PersonEntity actionEntity, PersonEntity action);
}
