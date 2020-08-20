package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ZoneMapperTest {

    private ZoneMapper zoneMapper;

    @BeforeEach
    public void setUp() {
        zoneMapper = new ZoneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(zoneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(zoneMapper.fromId(null)).isNull();
    }
}
