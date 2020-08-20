package com.nttdata.crf.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nttdata.crf.web.rest.TestUtil;

public class FieldMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FieldMaster.class);
        FieldMaster fieldMaster1 = new FieldMaster();
        fieldMaster1.setId("id1");
        FieldMaster fieldMaster2 = new FieldMaster();
        fieldMaster2.setId(fieldMaster1.getId());
        assertThat(fieldMaster1).isEqualTo(fieldMaster2);
        fieldMaster2.setId("id2");
        assertThat(fieldMaster1).isNotEqualTo(fieldMaster2);
        fieldMaster1.setId(null);
        assertThat(fieldMaster1).isNotEqualTo(fieldMaster2);
    }
}
