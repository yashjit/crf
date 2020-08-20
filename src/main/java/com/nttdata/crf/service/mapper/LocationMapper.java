package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.LocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.countryName", target = "countryCountryName")
    LocationDTO toDto(Location location);

    @Mapping(source = "countryId", target = "country")
    Location toEntity(LocationDTO locationDTO);

    default Location fromId(String id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
