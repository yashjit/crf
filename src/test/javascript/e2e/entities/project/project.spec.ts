import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProjectComponentsPage, ProjectDeleteDialog, ProjectUpdatePage } from './project.page-object';

const expect = chai.expect;

describe('Project e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let projectComponentsPage: ProjectComponentsPage;
  let projectUpdatePage: ProjectUpdatePage;
  let projectDeleteDialog: ProjectDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Projects', async () => {
    await navBarPage.goToEntity('project');
    projectComponentsPage = new ProjectComponentsPage();
    await browser.wait(ec.visibilityOf(projectComponentsPage.title), 5000);
    expect(await projectComponentsPage.getTitle()).to.eq('crfApp.project.home.title');
    await browser.wait(ec.or(ec.visibilityOf(projectComponentsPage.entities), ec.visibilityOf(projectComponentsPage.noResult)), 1000);
  });

  it('should load create Project page', async () => {
    await projectComponentsPage.clickOnCreateButton();
    projectUpdatePage = new ProjectUpdatePage();
    expect(await projectUpdatePage.getPageTitle()).to.eq('crfApp.project.home.createOrEditLabel');
    await projectUpdatePage.cancel();
  });

  it('should create and save Projects', async () => {
    const nbButtonsBeforeCreate = await projectComponentsPage.countDeleteButtons();

    await projectComponentsPage.clickOnCreateButton();

    await promise.all([
      projectUpdatePage.setProjectNameInput('projectName'),
      projectUpdatePage.setProjectTypeInput('projectType'),
      projectUpdatePage.setTargetCloudInput('targetCloud'),
      projectUpdatePage.setStartDateInput('2000-12-31'),
      projectUpdatePage.setEndDateInput('2000-12-31'),
      projectUpdatePage.setCustomInput('custom'),
      projectUpdatePage.setStatusInput('status'),
      projectUpdatePage.setCreatedByInput('createdBy'),
      projectUpdatePage.setCreateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      projectUpdatePage.setModifyDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      projectUpdatePage.organizationSelectLastOption(),
    ]);

    expect(await projectUpdatePage.getProjectNameInput()).to.eq('projectName', 'Expected ProjectName value to be equals to projectName');
    expect(await projectUpdatePage.getProjectTypeInput()).to.eq('projectType', 'Expected ProjectType value to be equals to projectType');
    expect(await projectUpdatePage.getTargetCloudInput()).to.eq('targetCloud', 'Expected TargetCloud value to be equals to targetCloud');
    expect(await projectUpdatePage.getStartDateInput()).to.eq('2000-12-31', 'Expected startDate value to be equals to 2000-12-31');
    expect(await projectUpdatePage.getEndDateInput()).to.eq('2000-12-31', 'Expected endDate value to be equals to 2000-12-31');
    expect(await projectUpdatePage.getCustomInput()).to.eq('custom', 'Expected Custom value to be equals to custom');
    expect(await projectUpdatePage.getStatusInput()).to.eq('status', 'Expected Status value to be equals to status');
    expect(await projectUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await projectUpdatePage.getCreateDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createDate value to be equals to 2000-12-31'
    );
    expect(await projectUpdatePage.getModifyDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected modifyDate value to be equals to 2000-12-31'
    );

    await projectUpdatePage.save();
    expect(await projectUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await projectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Project', async () => {
    const nbButtonsBeforeDelete = await projectComponentsPage.countDeleteButtons();
    await projectComponentsPage.clickOnLastDeleteButton();

    projectDeleteDialog = new ProjectDeleteDialog();
    expect(await projectDeleteDialog.getDialogTitle()).to.eq('crfApp.project.delete.question');
    await projectDeleteDialog.clickOnConfirmButton();

    expect(await projectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
