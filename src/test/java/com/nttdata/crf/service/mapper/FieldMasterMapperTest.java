package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FieldMasterMapperTest {

    private FieldMasterMapper fieldMasterMapper;

    @BeforeEach
    public void setUp() {
        fieldMasterMapper = new FieldMasterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(fieldMasterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fieldMasterMapper.fromId(null)).isNull();
    }
}
