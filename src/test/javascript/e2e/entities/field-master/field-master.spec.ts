import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FieldMasterComponentsPage, FieldMasterDeleteDialog, FieldMasterUpdatePage } from './field-master.page-object';

const expect = chai.expect;

describe('FieldMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let fieldMasterComponentsPage: FieldMasterComponentsPage;
  let fieldMasterUpdatePage: FieldMasterUpdatePage;
  let fieldMasterDeleteDialog: FieldMasterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load FieldMasters', async () => {
    await navBarPage.goToEntity('field-master');
    fieldMasterComponentsPage = new FieldMasterComponentsPage();
    await browser.wait(ec.visibilityOf(fieldMasterComponentsPage.title), 5000);
    expect(await fieldMasterComponentsPage.getTitle()).to.eq('crfApp.fieldMaster.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(fieldMasterComponentsPage.entities), ec.visibilityOf(fieldMasterComponentsPage.noResult)),
      1000
    );
  });

  it('should load create FieldMaster page', async () => {
    await fieldMasterComponentsPage.clickOnCreateButton();
    fieldMasterUpdatePage = new FieldMasterUpdatePage();
    expect(await fieldMasterUpdatePage.getPageTitle()).to.eq('crfApp.fieldMaster.home.createOrEditLabel');
    await fieldMasterUpdatePage.cancel();
  });

  it('should create and save FieldMasters', async () => {
    const nbButtonsBeforeCreate = await fieldMasterComponentsPage.countDeleteButtons();

    await fieldMasterComponentsPage.clickOnCreateButton();

    await promise.all([
      fieldMasterUpdatePage.setNameInput('name'),
      fieldMasterUpdatePage.setValueInput('value'),
      fieldMasterUpdatePage.setTypeInput('type'),
      fieldMasterUpdatePage.setDescriptionInput('description'),
      fieldMasterUpdatePage.setCustomInput('custom'),
    ]);

    expect(await fieldMasterUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await fieldMasterUpdatePage.getValueInput()).to.eq('value', 'Expected Value value to be equals to value');
    expect(await fieldMasterUpdatePage.getTypeInput()).to.eq('type', 'Expected Type value to be equals to type');
    expect(await fieldMasterUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    const selectedIsRequired = fieldMasterUpdatePage.getIsRequiredInput();
    if (await selectedIsRequired.isSelected()) {
      await fieldMasterUpdatePage.getIsRequiredInput().click();
      expect(await fieldMasterUpdatePage.getIsRequiredInput().isSelected(), 'Expected isRequired not to be selected').to.be.false;
    } else {
      await fieldMasterUpdatePage.getIsRequiredInput().click();
      expect(await fieldMasterUpdatePage.getIsRequiredInput().isSelected(), 'Expected isRequired to be selected').to.be.true;
    }
    expect(await fieldMasterUpdatePage.getCustomInput()).to.eq('custom', 'Expected Custom value to be equals to custom');

    await fieldMasterUpdatePage.save();
    expect(await fieldMasterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await fieldMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last FieldMaster', async () => {
    const nbButtonsBeforeDelete = await fieldMasterComponentsPage.countDeleteButtons();
    await fieldMasterComponentsPage.clickOnLastDeleteButton();

    fieldMasterDeleteDialog = new FieldMasterDeleteDialog();
    expect(await fieldMasterDeleteDialog.getDialogTitle()).to.eq('crfApp.fieldMaster.delete.question');
    await fieldMasterDeleteDialog.clickOnConfirmButton();

    expect(await fieldMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
