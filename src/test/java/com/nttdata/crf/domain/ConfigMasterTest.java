package com.nttdata.crf.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class ConfigMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigMaster.class);
        ConfigMaster configMaster1 = new ConfigMaster();
        configMaster1.setId("id1");
        ConfigMaster configMaster2 = new ConfigMaster();
        configMaster2.setId(configMaster1.getId());
        assertThat(configMaster1).isEqualTo(configMaster2);
        configMaster2.setId("id2");
        assertThat(configMaster1).isNotEqualTo(configMaster2);
        configMaster1.setId(null);
        assertThat(configMaster1).isNotEqualTo(configMaster2);
    }
}
