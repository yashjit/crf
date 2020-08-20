import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CrfTestModule } from '../../../test.module';
import { BlueprintComponent } from 'app/entities/blueprint/blueprint.component';
import { BlueprintService } from 'app/entities/blueprint/blueprint.service';
import { Blueprint } from 'app/shared/model/blueprint.model';

describe('Component Tests', () => {
  describe('Blueprint Management Component', () => {
    let comp: BlueprintComponent;
    let fixture: ComponentFixture<BlueprintComponent>;
    let service: BlueprintService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [BlueprintComponent],
      })
        .overrideTemplate(BlueprintComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlueprintComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlueprintService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Blueprint('123')],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.blueprints && comp.blueprints[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
