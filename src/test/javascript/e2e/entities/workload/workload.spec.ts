import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { WorkloadComponentsPage, WorkloadDeleteDialog, WorkloadUpdatePage } from './workload.page-object';

const expect = chai.expect;

describe('Workload e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let workloadComponentsPage: WorkloadComponentsPage;
  let workloadUpdatePage: WorkloadUpdatePage;
  let workloadDeleteDialog: WorkloadDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Workloads', async () => {
    await navBarPage.goToEntity('workload');
    workloadComponentsPage = new WorkloadComponentsPage();
    await browser.wait(ec.visibilityOf(workloadComponentsPage.title), 5000);
    expect(await workloadComponentsPage.getTitle()).to.eq('crfApp.workload.home.title');
    await browser.wait(ec.or(ec.visibilityOf(workloadComponentsPage.entities), ec.visibilityOf(workloadComponentsPage.noResult)), 1000);
  });

  it('should load create Workload page', async () => {
    await workloadComponentsPage.clickOnCreateButton();
    workloadUpdatePage = new WorkloadUpdatePage();
    expect(await workloadUpdatePage.getPageTitle()).to.eq('crfApp.workload.home.createOrEditLabel');
    await workloadUpdatePage.cancel();
  });

  it('should create and save Workloads', async () => {
    const nbButtonsBeforeCreate = await workloadComponentsPage.countDeleteButtons();

    await workloadComponentsPage.clickOnCreateButton();

    await promise.all([
      workloadUpdatePage.setTitleInput('title'),
      workloadUpdatePage.setTypeInput('type'),
      workloadUpdatePage.setMovegroupInput('5'),
      workloadUpdatePage.setServerNameInput('serverName'),
      workloadUpdatePage.setServerTierInput('serverTier'),
      workloadUpdatePage.setOsInput('os'),
      workloadUpdatePage.setCloudInfoInput('cloudInfo'),
      workloadUpdatePage.setDescriptionInput('description'),
      workloadUpdatePage.setCustomInput('custom'),
      workloadUpdatePage.setStatusInput('status'),
      workloadUpdatePage.setCreateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      workloadUpdatePage.setModifyDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      workloadUpdatePage.setCreatedByInput('createdBy'),
    ]);

    expect(await workloadUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await workloadUpdatePage.getTypeInput()).to.eq('type', 'Expected Type value to be equals to type');
    expect(await workloadUpdatePage.getMovegroupInput()).to.eq('5', 'Expected movegroup value to be equals to 5');
    expect(await workloadUpdatePage.getServerNameInput()).to.eq('serverName', 'Expected ServerName value to be equals to serverName');
    expect(await workloadUpdatePage.getServerTierInput()).to.eq('serverTier', 'Expected ServerTier value to be equals to serverTier');
    expect(await workloadUpdatePage.getOsInput()).to.eq('os', 'Expected Os value to be equals to os');
    expect(await workloadUpdatePage.getCloudInfoInput()).to.eq('cloudInfo', 'Expected CloudInfo value to be equals to cloudInfo');
    expect(await workloadUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await workloadUpdatePage.getCustomInput()).to.eq('custom', 'Expected Custom value to be equals to custom');
    expect(await workloadUpdatePage.getStatusInput()).to.eq('status', 'Expected Status value to be equals to status');
    expect(await workloadUpdatePage.getCreateDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createDate value to be equals to 2000-12-31'
    );
    expect(await workloadUpdatePage.getModifyDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected modifyDate value to be equals to 2000-12-31'
    );
    expect(await workloadUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');

    await workloadUpdatePage.save();
    expect(await workloadUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await workloadComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Workload', async () => {
    const nbButtonsBeforeDelete = await workloadComponentsPage.countDeleteButtons();
    await workloadComponentsPage.clickOnLastDeleteButton();

    workloadDeleteDialog = new WorkloadDeleteDialog();
    expect(await workloadDeleteDialog.getDialogTitle()).to.eq('crfApp.workload.delete.question');
    await workloadDeleteDialog.clickOnConfirmButton();

    expect(await workloadComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
