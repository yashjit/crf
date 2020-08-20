package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WaveMapperTest {

    private WaveMapper waveMapper;

    @BeforeEach
    public void setUp() {
        waveMapper = new WaveMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(waveMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(waveMapper.fromId(null)).isNull();
    }
}
