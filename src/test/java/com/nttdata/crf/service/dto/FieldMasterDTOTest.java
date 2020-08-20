package com.nttdata.crf.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class FieldMasterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldMasterDTO.class);
        FieldMasterDTO fieldMasterDTO1 = new FieldMasterDTO();
        fieldMasterDTO1.setId("id1");
        FieldMasterDTO fieldMasterDTO2 = new FieldMasterDTO();
        assertThat(fieldMasterDTO1).isNotEqualTo(fieldMasterDTO2);
        fieldMasterDTO2.setId(fieldMasterDTO1.getId());
        assertThat(fieldMasterDTO1).isEqualTo(fieldMasterDTO2);
        fieldMasterDTO2.setId("id2");
        assertThat(fieldMasterDTO1).isNotEqualTo(fieldMasterDTO2);
        fieldMasterDTO1.setId(null);
        assertThat(fieldMasterDTO1).isNotEqualTo(fieldMasterDTO2);
    }
}
