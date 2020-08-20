package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BlueprintMapperTest {

    private BlueprintMapper blueprintMapper;

    @BeforeEach
    public void setUp() {
        blueprintMapper = new BlueprintMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(blueprintMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(blueprintMapper.fromId(null)).isNull();
    }
}
