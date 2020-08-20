package com.nttdata.crf.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class WorkloadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Workload.class);
        Workload workload1 = new Workload();
        workload1.setId("id1");
        Workload workload2 = new Workload();
        workload2.setId(workload1.getId());
        assertThat(workload1).isEqualTo(workload2);
        workload2.setId("id2");
        assertThat(workload1).isNotEqualTo(workload2);
        workload1.setId(null);
        assertThat(workload1).isNotEqualTo(workload2);
    }
}
