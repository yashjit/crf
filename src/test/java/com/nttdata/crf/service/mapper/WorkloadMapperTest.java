package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WorkloadMapperTest {

    private WorkloadMapper workloadMapper;

    @BeforeEach
    public void setUp() {
        workloadMapper = new WorkloadMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(workloadMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(workloadMapper.fromId(null)).isNull();
    }
}
