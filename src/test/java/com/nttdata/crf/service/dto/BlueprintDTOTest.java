package com.nttdata.crf.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class BlueprintDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlueprintDTO.class);
        BlueprintDTO blueprintDTO1 = new BlueprintDTO();
        blueprintDTO1.setId("id1");
        BlueprintDTO blueprintDTO2 = new BlueprintDTO();
        assertThat(blueprintDTO1).isNotEqualTo(blueprintDTO2);
        blueprintDTO2.setId(blueprintDTO1.getId());
        assertThat(blueprintDTO1).isEqualTo(blueprintDTO2);
        blueprintDTO2.setId("id2");
        assertThat(blueprintDTO1).isNotEqualTo(blueprintDTO2);
        blueprintDTO1.setId(null);
        assertThat(blueprintDTO1).isNotEqualTo(blueprintDTO2);
    }
}
