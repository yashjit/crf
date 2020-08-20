package com.nttdata.crf.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TeamMapperTest {

    private TeamMapper teamMapper;

    @BeforeEach
    public void setUp() {
        teamMapper = new TeamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(teamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(teamMapper.fromId(null)).isNull();
    }
}
