import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BlueprintComponentsPage, BlueprintDeleteDialog, BlueprintUpdatePage } from './blueprint.page-object';

const expect = chai.expect;

describe('Blueprint e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let blueprintComponentsPage: BlueprintComponentsPage;
  let blueprintUpdatePage: BlueprintUpdatePage;
  let blueprintDeleteDialog: BlueprintDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Blueprints', async () => {
    await navBarPage.goToEntity('blueprint');
    blueprintComponentsPage = new BlueprintComponentsPage();
    await browser.wait(ec.visibilityOf(blueprintComponentsPage.title), 5000);
    expect(await blueprintComponentsPage.getTitle()).to.eq('crfApp.blueprint.home.title');
    await browser.wait(ec.or(ec.visibilityOf(blueprintComponentsPage.entities), ec.visibilityOf(blueprintComponentsPage.noResult)), 1000);
  });

  it('should load create Blueprint page', async () => {
    await blueprintComponentsPage.clickOnCreateButton();
    blueprintUpdatePage = new BlueprintUpdatePage();
    expect(await blueprintUpdatePage.getPageTitle()).to.eq('crfApp.blueprint.home.createOrEditLabel');
    await blueprintUpdatePage.cancel();
  });

  it('should create and save Blueprints', async () => {
    const nbButtonsBeforeCreate = await blueprintComponentsPage.countDeleteButtons();

    await blueprintComponentsPage.clickOnCreateButton();

    await promise.all([
      blueprintUpdatePage.setTitleInput('title'),
      blueprintUpdatePage.setTypeInput('type'),
      blueprintUpdatePage.setDescriptionInput('description'),
      blueprintUpdatePage.setTemplateInput('template'),
      blueprintUpdatePage.setCustomInput('custom'),
      blueprintUpdatePage.workloadSelectLastOption(),
    ]);

    expect(await blueprintUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await blueprintUpdatePage.getTypeInput()).to.eq('type', 'Expected Type value to be equals to type');
    expect(await blueprintUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await blueprintUpdatePage.getTemplateInput()).to.eq('template', 'Expected Template value to be equals to template');
    expect(await blueprintUpdatePage.getCustomInput()).to.eq('custom', 'Expected Custom value to be equals to custom');

    await blueprintUpdatePage.save();
    expect(await blueprintUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await blueprintComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Blueprint', async () => {
    const nbButtonsBeforeDelete = await blueprintComponentsPage.countDeleteButtons();
    await blueprintComponentsPage.clickOnLastDeleteButton();

    blueprintDeleteDialog = new BlueprintDeleteDialog();
    expect(await blueprintDeleteDialog.getDialogTitle()).to.eq('crfApp.blueprint.delete.question');
    await blueprintDeleteDialog.clickOnConfirmButton();

    expect(await blueprintComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
