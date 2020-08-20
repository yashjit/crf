import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CrfTestModule } from '../../../test.module';
import { ConfigMasterComponent } from 'app/entities/config-master/config-master.component';
import { ConfigMasterService } from 'app/entities/config-master/config-master.service';
import { ConfigMaster } from 'app/shared/model/config-master.model';

describe('Component Tests', () => {
  describe('ConfigMaster Management Component', () => {
    let comp: ConfigMasterComponent;
    let fixture: ComponentFixture<ConfigMasterComponent>;
    let service: ConfigMasterService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [ConfigMasterComponent],
      })
        .overrideTemplate(ConfigMasterComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConfigMasterComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConfigMasterService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConfigMaster('123')],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.configMasters && comp.configMasters[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
