import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { ConfigMasterUpdateComponent } from 'app/entities/config-master/config-master-update.component';
import { ConfigMasterService } from 'app/entities/config-master/config-master.service';
import { ConfigMaster } from 'app/shared/model/config-master.model';

describe('Component Tests', () => {
  describe('ConfigMaster Management Update Component', () => {
    let comp: ConfigMasterUpdateComponent;
    let fixture: ComponentFixture<ConfigMasterUpdateComponent>;
    let service: ConfigMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [ConfigMasterUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ConfigMasterUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConfigMasterUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConfigMasterService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConfigMaster('123');
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
        const entity = new ConfigMaster();
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
