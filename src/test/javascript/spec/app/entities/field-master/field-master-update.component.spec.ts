import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { FieldMasterUpdateComponent } from 'app/entities/field-master/field-master-update.component';
import { FieldMasterService } from 'app/entities/field-master/field-master.service';
import { FieldMaster } from 'app/shared/model/field-master.model';

describe('Component Tests', () => {
  describe('FieldMaster Management Update Component', () => {
    let comp: FieldMasterUpdateComponent;
    let fixture: ComponentFixture<FieldMasterUpdateComponent>;
    let service: FieldMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [FieldMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FieldMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FieldMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FieldMaster('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new FieldMaster();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
