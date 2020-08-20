import { element, by, ElementFinder } from 'protractor';

export class ApplicationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-application div table .btn-danger'));
  title = element.all(by.css('jhi-application div h2#page-heading span')).first();
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

export class ApplicationUpdatePage {
  pageTitle = element(by.id('jhi-application-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  appNameInput = element(by.id('field_appName'));
  appTypeInput = element(by.id('field_appType'));
  customInput = element(by.id('field_custom'));

  workloadSelect = element(by.id('field_workload'));
  waveSelect = element(by.id('field_wave'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAppNameInput(appName: string): Promise<void> {
    await this.appNameInput.sendKeys(appName);
  }

  async getAppNameInput(): Promise<string> {
    return await this.appNameInput.getAttribute('value');
  }

  async setAppTypeInput(appType: string): Promise<void> {
    await this.appTypeInput.sendKeys(appType);
  }

  async getAppTypeInput(): Promise<string> {
    return await this.appTypeInput.getAttribute('value');
  }

  async setCustomInput(custom: string): Promise<void> {
    await this.customInput.sendKeys(custom);
  }

  async getCustomInput(): Promise<string> {
    return await this.customInput.getAttribute('value');
  }

  async workloadSelectLastOption(): Promise<void> {
    await this.workloadSelect.all(by.tagName('option')).last().click();
  }

  async workloadSelectOption(option: string): Promise<void> {
    await this.workloadSelect.sendKeys(option);
  }

  getWorkloadSelect(): ElementFinder {
    return this.workloadSelect;
  }

  async getWorkloadSelectedOption(): Promise<string> {
    return await this.workloadSelect.element(by.css('option:checked')).getText();
  }

  async waveSelectLastOption(): Promise<void> {
    await this.waveSelect.all(by.tagName('option')).last().click();
  }

  async waveSelectOption(option: string): Promise<void> {
    await this.waveSelect.sendKeys(option);
  }

  getWaveSelect(): ElementFinder {
    return this.waveSelect;
  }

  async getWaveSelectedOption(): Promise<string> {
    return await this.waveSelect.element(by.css('option:checked')).getText();
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

export class ApplicationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-application-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-application'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
