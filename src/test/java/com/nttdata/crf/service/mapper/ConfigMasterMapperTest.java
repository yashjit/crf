package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigMasterMapperTest {

    private ConfigMasterMapper configMasterMapper;

    @BeforeEach
    public void setUp() {
        configMasterMapper = new ConfigMasterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(configMasterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(configMasterMapper.fromId(null)).isNull();
    }
}
