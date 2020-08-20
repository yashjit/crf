package com.nttdata.crf.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class WaveTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wave.class);
        Wave wave1 = new Wave();
        wave1.setId("id1");
        Wave wave2 = new Wave();
        wave2.setId(wave1.getId());
        assertThat(wave1).isEqualTo(wave2);
        wave2.setId("id2");
        assertThat(wave1).isNotEqualTo(wave2);
        wave1.setId(null);
        assertThat(wave1).isNotEqualTo(wave2);
    }
}
