import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { BlueprintDetailComponent } from 'app/entities/blueprint/blueprint-detail.component';
import { Blueprint } from 'app/shared/model/blueprint.model';

describe('Component Tests', () => {
  describe('Blueprint Management Detail Component', () => {
    let comp: BlueprintDetailComponent;
    let fixture: ComponentFixture<BlueprintDetailComponent>;
    const route = ({ data: of({ blueprint: new Blueprint('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [BlueprintDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BlueprintDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlueprintDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load blueprint on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blueprint).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
