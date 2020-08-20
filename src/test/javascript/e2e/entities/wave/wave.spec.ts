import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { WaveComponentsPage, WaveDeleteDialog, WaveUpdatePage } from './wave.page-object';

const expect = chai.expect;

describe('Wave e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let waveComponentsPage: WaveComponentsPage;
  let waveUpdatePage: WaveUpdatePage;
  let waveDeleteDialog: WaveDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Waves', async () => {
    await navBarPage.goToEntity('wave');
    waveComponentsPage = new WaveComponentsPage();
    await browser.wait(ec.visibilityOf(waveComponentsPage.title), 5000);
    expect(await waveComponentsPage.getTitle()).to.eq('crfApp.wave.home.title');
    await browser.wait(ec.or(ec.visibilityOf(waveComponentsPage.entities), ec.visibilityOf(waveComponentsPage.noResult)), 1000);
  });

  it('should load create Wave page', async () => {
    await waveComponentsPage.clickOnCreateButton();
    waveUpdatePage = new WaveUpdatePage();
    expect(await waveUpdatePage.getPageTitle()).to.eq('crfApp.wave.home.createOrEditLabel');
    await waveUpdatePage.cancel();
  });

  it('should create and save Waves', async () => {
    const nbButtonsBeforeCreate = await waveComponentsPage.countDeleteButtons();

    await waveComponentsPage.clickOnCreateButton();

    await promise.all([
      waveUpdatePage.setWaveNameInput('waveName'),
      waveUpdatePage.setFileNameInput('fileName'),
      waveUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      waveUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      waveUpdatePage.setStatusInput('status'),
      waveUpdatePage.setCustomInput('custom'),
      waveUpdatePage.setCreatedByInput('createdBy'),
      waveUpdatePage.setCreateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      waveUpdatePage.setModifyDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      waveUpdatePage.projectSelectLastOption(),
    ]);

    expect(await waveUpdatePage.getWaveNameInput()).to.eq('waveName', 'Expected WaveName value to be equals to waveName');
    expect(await waveUpdatePage.getFileNameInput()).to.eq('fileName', 'Expected FileName value to be equals to fileName');
    expect(await waveUpdatePage.getStartDateInput()).to.contain('2001-01-01T02:30', 'Expected startDate value to be equals to 2000-12-31');
    expect(await waveUpdatePage.getEndDateInput()).to.contain('2001-01-01T02:30', 'Expected endDate value to be equals to 2000-12-31');
    expect(await waveUpdatePage.getStatusInput()).to.eq('status', 'Expected Status value to be equals to status');
    expect(await waveUpdatePage.getCustomInput()).to.eq('custom', 'Expected Custom value to be equals to custom');
    expect(await waveUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await waveUpdatePage.getCreateDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createDate value to be equals to 2000-12-31'
    );
    expect(await waveUpdatePage.getModifyDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected modifyDate value to be equals to 2000-12-31'
    );

    await waveUpdatePage.save();
    expect(await waveUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await waveComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Wave', async () => {
    const nbButtonsBeforeDelete = await waveComponentsPage.countDeleteButtons();
    await waveComponentsPage.clickOnLastDeleteButton();

    waveDeleteDialog = new WaveDeleteDialog();
    expect(await waveDeleteDialog.getDialogTitle()).to.eq('crfApp.wave.delete.question');
    await waveDeleteDialog.clickOnConfirmButton();

    expect(await waveComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
