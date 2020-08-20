import { element, by, ElementFinder } from 'protractor';

export class CountryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-country div table .btn-danger'));
  title = element.all(by.css('jhi-country div h2#page-heading span')).first();
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

export class CountryUpdatePage {
  pageTitle = element(by.id('jhi-country-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  countryNameInput = element(by.id('field_countryName'));
  countryCodeInput = element(by.id('field_countryCode'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCountryNameInput(countryName: string): Promise<void> {
    await this.countryNameInput.sendKeys(countryName);
  }

  async getCountryNameInput(): Promise<string> {
    return await this.countryNameInput.getAttribute('value');
  }

  async setCountryCodeInput(countryCode: string): Promise<void> {
    await this.countryCodeInput.sendKeys(countryCode);
  }

  async getCountryCodeInput(): Promise<string> {
    return await this.countryCodeInput.getAttribute('value');
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

export class CountryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-country-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-country'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
