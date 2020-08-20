import { element, by, ElementFinder } from 'protractor';

export class ZoneComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-zone div table .btn-danger'));
  title = element.all(by.css('jhi-zone div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ZoneUpdatePage {
  pageTitle = element(by.id('jhi-zone-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  zoneNameInput = element(by.id('field_zoneName'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setZoneNameInput(zoneName: string): Promise<void> {
    await this.zoneNameInput.sendKeys(zoneName);
  }

  async getZoneNameInput(): Promise<string> {
    return await this.zoneNameInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ZoneDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-zone-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-zone'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
