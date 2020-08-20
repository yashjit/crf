package com.nttdata.crf.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class WorkloadDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkloadDTO.class);
        WorkloadDTO workloadDTO1 = new WorkloadDTO();
        workloadDTO1.setId("id1");
        WorkloadDTO workloadDTO2 = new WorkloadDTO();
        assertThat(workloadDTO1).isNotEqualTo(workloadDTO2);
        workloadDTO2.setId(workloadDTO1.getId());
        assertThat(workloadDTO1).isEqualTo(workloadDTO2);
        workloadDTO2.setId("id2");
        assertThat(workloadDTO1).isNotEqualTo(workloadDTO2);
        workloadDTO1.setId(null);
        assertThat(workloadDTO1).isNotEqualTo(workloadDTO2);
    }
}
