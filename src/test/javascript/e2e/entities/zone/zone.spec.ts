import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ZoneComponentsPage, ZoneDeleteDialog, ZoneUpdatePage } from './zone.page-object';

const expect = chai.expect;

describe('Zone e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let zoneComponentsPage: ZoneComponentsPage;
  let zoneUpdatePage: ZoneUpdatePage;
  let zoneDeleteDialog: ZoneDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Zones', async () => {
    await navBarPage.goToEntity('zone');
    zoneComponentsPage = new ZoneComponentsPage();
    await browser.wait(ec.visibilityOf(zoneComponentsPage.title), 5000);
    expect(await zoneComponentsPage.getTitle()).to.eq('crfApp.zone.home.title');
    await browser.wait(ec.or(ec.visibilityOf(zoneComponentsPage.entities), ec.visibilityOf(zoneComponentsPage.noResult)), 1000);
  });

  it('should load create Zone page', async () => {
    await zoneComponentsPage.clickOnCreateButton();
    zoneUpdatePage = new ZoneUpdatePage();
    expect(await zoneUpdatePage.getPageTitle()).to.eq('crfApp.zone.home.createOrEditLabel');
    await zoneUpdatePage.cancel();
  });

  it('should create and save Zones', async () => {
    const nbButtonsBeforeCreate = await zoneComponentsPage.countDeleteButtons();

    await zoneComponentsPage.clickOnCreateButton();

    await promise.all([zoneUpdatePage.setZoneNameInput('zoneName')]);

    expect(await zoneUpdatePage.getZoneNameInput()).to.eq('zoneName', 'Expected ZoneName value to be equals to zoneName');

    await zoneUpdatePage.save();
    expect(await zoneUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await zoneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Zone', async () => {
    const nbButtonsBeforeDelete = await zoneComponentsPage.countDeleteButtons();
    await zoneComponentsPage.clickOnLastDeleteButton();

    zoneDeleteDialog = new ZoneDeleteDialog();
    expect(await zoneDeleteDialog.getDialogTitle()).to.eq('crfApp.zone.delete.question');
    await zoneDeleteDialog.clickOnConfirmButton();

    expect(await zoneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
