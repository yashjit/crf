package com.nttdata.crf.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class WaveDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaveDTO.class);
        WaveDTO waveDTO1 = new WaveDTO();
        waveDTO1.setId("id1");
        WaveDTO waveDTO2 = new WaveDTO();
        assertThat(waveDTO1).isNotEqualTo(waveDTO2);
        waveDTO2.setId(waveDTO1.getId());
        assertThat(waveDTO1).isEqualTo(waveDTO2);
        waveDTO2.setId("id2");
        assertThat(waveDTO1).isNotEqualTo(waveDTO2);
        waveDTO1.setId(null);
        assertThat(waveDTO1).isNotEqualTo(waveDTO2);
    }
}
