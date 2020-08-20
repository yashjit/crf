import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { WaveUpdateComponent } from 'app/entities/wave/wave-update.component';
import { WaveService } from 'app/entities/wave/wave.service';
import { Wave } from 'app/shared/model/wave.model';

describe('Component Tests', () => {
  describe('Wave Management Update Component', () => {
    let comp: WaveUpdateComponent;
    let fixture: ComponentFixture<WaveUpdateComponent>;
    let service: WaveService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [WaveUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WaveUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaveUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaveService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Wave('123');
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
        const entity = new Wave();
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
