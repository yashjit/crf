package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LocationMapperTest {

    private LocationMapper locationMapper;

    @BeforeEach
    public void setUp() {
        locationMapper = new LocationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(locationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(locationMapper.fromId(null)).isNull();
    }
}
