package com.nttdata.crf.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class BlueprintTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Blueprint.class);
        Blueprint blueprint1 = new Blueprint();
        blueprint1.setId("id1");
        Blueprint blueprint2 = new Blueprint();
        blueprint2.setId(blueprint1.getId());
        assertThat(blueprint1).isEqualTo(blueprint2);
        blueprint2.setId("id2");
        assertThat(blueprint1).isNotEqualTo(blueprint2);
        blueprint1.setId(null);
        assertThat(blueprint1).isNotEqualTo(blueprint2);
    }
}
