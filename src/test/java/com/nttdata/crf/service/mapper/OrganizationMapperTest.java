package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrganizationMapperTest {

    private OrganizationMapper organizationMapper;

    @BeforeEach
    public void setUp() {
        organizationMapper = new OrganizationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(organizationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(organizationMapper.fromId(null)).isNull();
    }
}
