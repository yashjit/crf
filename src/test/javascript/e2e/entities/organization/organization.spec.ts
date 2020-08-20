import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrganizationComponentsPage, OrganizationDeleteDialog, OrganizationUpdatePage } from './organization.page-object';

const expect = chai.expect;

describe('Organization e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let organizationComponentsPage: OrganizationComponentsPage;
  let organizationUpdatePage: OrganizationUpdatePage;
  let organizationDeleteDialog: OrganizationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Organizations', async () => {
    await navBarPage.goToEntity('organization');
    organizationComponentsPage = new OrganizationComponentsPage();
    await browser.wait(ec.visibilityOf(organizationComponentsPage.title), 5000);
    expect(await organizationComponentsPage.getTitle()).to.eq('crfApp.organization.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(organizationComponentsPage.entities), ec.visibilityOf(organizationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Organization page', async () => {
    await organizationComponentsPage.clickOnCreateButton();
    organizationUpdatePage = new OrganizationUpdatePage();
    expect(await organizationUpdatePage.getPageTitle()).to.eq('crfApp.organization.home.createOrEditLabel');
    await organizationUpdatePage.cancel();
  });

  it('should create and save Organizations', async () => {
    const nbButtonsBeforeCreate = await organizationComponentsPage.countDeleteButtons();

    await organizationComponentsPage.clickOnCreateButton();

    await promise.all([
      organizationUpdatePage.setOrgNameInput('orgName'),
      organizationUpdatePage.setFirstNameInput('firstName'),
      organizationUpdatePage.setLastNameInput('lastName'),
      organizationUpdatePage.setEmailInput('email'),
      organizationUpdatePage.setPhoneNumberInput('phoneNumber'),
      organizationUpdatePage.setCreateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      organizationUpdatePage.locationSelectLastOption(),
    ]);

    expect(await organizationUpdatePage.getOrgNameInput()).to.eq('orgName', 'Expected OrgName value to be equals to orgName');
    expect(await organizationUpdatePage.getFirstNameInput()).to.eq('firstName', 'Expected FirstName value to be equals to firstName');
    expect(await organizationUpdatePage.getLastNameInput()).to.eq('lastName', 'Expected LastName value to be equals to lastName');
    expect(await organizationUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await organizationUpdatePage.getPhoneNumberInput()).to.eq(
      'phoneNumber',
      'Expected PhoneNumber value to be equals to phoneNumber'
    );
    expect(await organizationUpdatePage.getCreateDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createDate value to be equals to 2000-12-31'
    );

    await organizationUpdatePage.save();
    expect(await organizationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await organizationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Organization', async () => {
    const nbButtonsBeforeDelete = await organizationComponentsPage.countDeleteButtons();
    await organizationComponentsPage.clickOnLastDeleteButton();

    organizationDeleteDialog = new OrganizationDeleteDialog();
    expect(await organizationDeleteDialog.getDialogTitle()).to.eq('crfApp.organization.delete.question');
    await organizationDeleteDialog.clickOnConfirmButton();

    expect(await organizationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
