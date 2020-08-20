import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WorkloadService } from 'app/entities/workload/workload.service';
import { IWorkload, Workload } from 'app/shared/model/workload.model';

describe('Service Tests', () => {
  describe('Workload Service', () => {
    let injector: TestBed;
    let service: WorkloadService;
    let httpMock: HttpTestingController;
    let elemDefault: IWorkload;
    let expectedResult: IWorkload | IWorkload[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WorkloadService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Workload(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createDate: currentDate.format(DATE_TIME_FORMAT),
            modifyDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Workload', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            createDate: currentDate.format(DATE_TIME_FORMAT),
            modifyDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
            modifyDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Workload()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Workload', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            type: 'BBBBBB',
            movegroup: 1,
            serverName: 'BBBBBB',
            serverTier: 'BBBBBB',
            os: 'BBBBBB',
            cloudInfo: 'BBBBBB',
            description: 'BBBBBB',
            custom: 'BBBBBB',
            status: 'BBBBBB',
            createDate: currentDate.format(DATE_TIME_FORMAT),
            modifyDate: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
            modifyDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Workload', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            type: 'BBBBBB',
            movegroup: 1,
            serverName: 'BBBBBB',
            serverTier: 'BBBBBB',
            os: 'BBBBBB',
            cloudInfo: 'BBBBBB',
            description: 'BBBBBB',
            custom: 'BBBBBB',
            status: 'BBBBBB',
            createDate: currentDate.format(DATE_TIME_FORMAT),
            modifyDate: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
            modifyDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Workload', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
