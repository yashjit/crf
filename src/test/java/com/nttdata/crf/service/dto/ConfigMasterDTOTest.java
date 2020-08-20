package com.nttdata.crf.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class ConfigMasterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigMasterDTO.class);
        ConfigMasterDTO configMasterDTO1 = new ConfigMasterDTO();
        configMasterDTO1.setId("id1");
        ConfigMasterDTO configMasterDTO2 = new ConfigMasterDTO();
        assertThat(configMasterDTO1).isNotEqualTo(configMasterDTO2);
        configMasterDTO2.setId(configMasterDTO1.getId());
        assertThat(configMasterDTO1).isEqualTo(configMasterDTO2);
        configMasterDTO2.setId("id2");
        assertThat(configMasterDTO1).isNotEqualTo(configMasterDTO2);
        configMasterDTO1.setId(null);
        assertThat(configMasterDTO1).isNotEqualTo(configMasterDTO2);
    }
}
