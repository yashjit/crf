import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ConfigMasterComponentsPage, ConfigMasterDeleteDialog, ConfigMasterUpdatePage } from './config-master.page-object';

const expect = chai.expect;

describe('ConfigMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let configMasterComponentsPage: ConfigMasterComponentsPage;
  let configMasterUpdatePage: ConfigMasterUpdatePage;
  let configMasterDeleteDialog: ConfigMasterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ConfigMasters', async () => {
    await navBarPage.goToEntity('config-master');
    configMasterComponentsPage = new ConfigMasterComponentsPage();
    await browser.wait(ec.visibilityOf(configMasterComponentsPage.title), 5000);
    expect(await configMasterComponentsPage.getTitle()).to.eq('crfApp.configMaster.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(configMasterComponentsPage.entities), ec.visibilityOf(configMasterComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ConfigMaster page', async () => {
    await configMasterComponentsPage.clickOnCreateButton();
    configMasterUpdatePage = new ConfigMasterUpdatePage();
    expect(await configMasterUpdatePage.getPageTitle()).to.eq('crfApp.configMaster.home.createOrEditLabel');
    await configMasterUpdatePage.cancel();
  });

  it('should create and save ConfigMasters', async () => {
    const nbButtonsBeforeCreate = await configMasterComponentsPage.countDeleteButtons();

    await configMasterComponentsPage.clickOnCreateButton();

    await promise.all([
      configMasterUpdatePage.setNameInput('name'),
      configMasterUpdatePage.setValueInput('value'),
      configMasterUpdatePage.setTypeInput('type'),
      configMasterUpdatePage.setDescriptionInput('description'),
      configMasterUpdatePage.setCustomInput('custom'),
      configMasterUpdatePage.organizationSelectLastOption(),
    ]);

    expect(await configMasterUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await configMasterUpdatePage.getValueInput()).to.eq('value', 'Expected Value value to be equals to value');
    expect(await configMasterUpdatePage.getTypeInput()).to.eq('type', 'Expected Type value to be equals to type');
    expect(await configMasterUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    const selectedIsRequired = configMasterUpdatePage.getIsRequiredInput();
    if (await selectedIsRequired.isSelected()) {
      await configMasterUpdatePage.getIsRequiredInput().click();
      expect(await configMasterUpdatePage.getIsRequiredInput().isSelected(), 'Expected isRequired not to be selected').to.be.false;
    } else {
      await configMasterUpdatePage.getIsRequiredInput().click();
      expect(await configMasterUpdatePage.getIsRequiredInput().isSelected(), 'Expected isRequired to be selected').to.be.true;
    }
    expect(await configMasterUpdatePage.getCustomInput()).to.eq('custom', 'Expected Custom value to be equals to custom');

    await configMasterUpdatePage.save();
    expect(await configMasterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await configMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ConfigMaster', async () => {
    const nbButtonsBeforeDelete = await configMasterComponentsPage.countDeleteButtons();
    await configMasterComponentsPage.clickOnLastDeleteButton();

    configMasterDeleteDialog = new ConfigMasterDeleteDialog();
    expect(await configMasterDeleteDialog.getDialogTitle()).to.eq('crfApp.configMaster.delete.question');
    await configMasterDeleteDialog.clickOnConfirmButton();

    expect(await configMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
