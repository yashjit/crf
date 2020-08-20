import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { BlueprintUpdateComponent } from 'app/entities/blueprint/blueprint-update.component';
import { BlueprintService } from 'app/entities/blueprint/blueprint.service';
import { Blueprint } from 'app/shared/model/blueprint.model';

describe('Component Tests', () => {
  describe('Blueprint Management Update Component', () => {
    let comp: BlueprintUpdateComponent;
    let fixture: ComponentFixture<BlueprintUpdateComponent>;
    let service: BlueprintService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [BlueprintUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BlueprintUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlueprintUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlueprintService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Blueprint('123');
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
        const entity = new Blueprint();
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
