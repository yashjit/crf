import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { ApplicationUpdateComponent } from 'app/entities/application/application-update.component';
import { ApplicationService } from 'app/entities/application/application.service';
import { Application } from 'app/shared/model/application.model';

describe('Component Tests', () => {
  describe('Application Management Update Component', () => {
    let comp: ApplicationUpdateComponent;
    let fixture: ComponentFixture<ApplicationUpdateComponent>;
    let service: ApplicationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [ApplicationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ApplicationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApplicationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApplicationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Application('123');
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
        const entity = new Application();
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
