import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { FieldMasterDetailComponent } from 'app/entities/field-master/field-master-detail.component';
import { FieldMaster } from 'app/shared/model/field-master.model';

describe('Component Tests', () => {
  describe('FieldMaster Management Detail Component', () => {
    let comp: FieldMasterDetailComponent;
    let fixture: ComponentFixture<FieldMasterDetailComponent>;
    const route = ({ data: of({ fieldMaster: new FieldMaster('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [FieldMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FieldMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FieldMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fieldMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fieldMaster).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
