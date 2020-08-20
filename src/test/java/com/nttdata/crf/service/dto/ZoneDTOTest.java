package com.nttdata.crf.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class ZoneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZoneDTO.class);
        ZoneDTO zoneDTO1 = new ZoneDTO();
        zoneDTO1.setId("id1");
        ZoneDTO zoneDTO2 = new ZoneDTO();
        assertThat(zoneDTO1).isNotEqualTo(zoneDTO2);
        zoneDTO2.setId(zoneDTO1.getId());
        assertThat(zoneDTO1).isEqualTo(zoneDTO2);
        zoneDTO2.setId("id2");
        assertThat(zoneDTO1).isNotEqualTo(zoneDTO2);
        zoneDTO1.setId(null);
        assertThat(zoneDTO1).isNotEqualTo(zoneDTO2);
    }
}
