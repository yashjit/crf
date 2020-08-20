import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CrfTestModule } from '../../../test.module';
import { FieldMasterComponent } from 'app/entities/field-master/field-master.component';
import { FieldMasterService } from 'app/entities/field-master/field-master.service';
import { FieldMaster } from 'app/shared/model/field-master.model';

describe('Component Tests', () => {
  describe('FieldMaster Management Component', () => {
    let comp: FieldMasterComponent;
    let fixture: ComponentFixture<FieldMasterComponent>;
    let service: FieldMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [FieldMasterComponent],
      })
        .overrideTemplate(FieldMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FieldMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FieldMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FieldMaster('123')],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fieldMasters && comp.fieldMasters[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
