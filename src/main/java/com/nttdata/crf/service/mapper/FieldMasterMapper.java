package com.nttdata.crf.service.mapper;


import com.nttdata.crf.domain.*;
import com.nttdata.crf.service.dto.FieldMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FieldMaster} and its DTO {@link FieldMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FieldMasterMapper extends EntityMapper<FieldMasterDTO, FieldMaster> {



    default FieldMaster fromId(String id) {
        if (id == null) {
            return null;
        }
        FieldMaster fieldMaster = new FieldMaster();
        fieldMaster.setId(id);
        return fieldMaster;
    }
}
