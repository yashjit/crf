import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { ConfigMasterDetailComponent } from 'app/entities/config-master/config-master-detail.component';
import { ConfigMaster } from 'app/shared/model/config-master.model';

describe('Component Tests', () => {
  describe('ConfigMaster Management Detail Component', () => {
    let comp: ConfigMasterDetailComponent;
    let fixture: ComponentFixture<ConfigMasterDetailComponent>;
    const route = ({ data: of({ configMaster: new ConfigMaster('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [ConfigMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ConfigMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConfigMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load configMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.configMaster).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
