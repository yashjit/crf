import { element, by, ElementFinder } from 'protractor';

export class WaveComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-wave div table .btn-danger'));
  title = element.all(by.css('jhi-wave div h2#page-heading span')).first();
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

export class WaveUpdatePage {
  pageTitle = element(by.id('jhi-wave-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  waveNameInput = element(by.id('field_waveName'));
  fileNameInput = element(by.id('field_fileName'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  statusInput = element(by.id('field_status'));
  customInput = element(by.id('field_custom'));
  createdByInput = element(by.id('field_createdBy'));
  createDateInput = element(by.id('field_createDate'));
  modifyDateInput = element(by.id('field_modifyDate'));

  projectSelect = element(by.id('field_project'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setWaveNameInput(waveName: string): Promise<void> {
    await this.waveNameInput.sendKeys(waveName);
  }

  async getWaveNameInput(): Promise<string> {
    return await this.waveNameInput.getAttribute('value');
  }

  async setFileNameInput(fileName: string): Promise<void> {
    await this.fileNameInput.sendKeys(fileName);
  }

  async getFileNameInput(): Promise<string> {
    return await this.fileNameInput.getAttribute('value');
  }

  async setStartDateInput(startDate: string): Promise<void> {
    await this.startDateInput.sendKeys(startDate);
  }

  async getStartDateInput(): Promise<string> {
    return await this.startDateInput.getAttribute('value');
  }

  async setEndDateInput(endDate: string): Promise<void> {
    await this.endDateInput.sendKeys(endDate);
  }

  async getEndDateInput(): Promise<string> {
    return await this.endDateInput.getAttribute('value');
  }

  async setStatusInput(status: string): Promise<void> {
    await this.statusInput.sendKeys(status);
  }

  async getStatusInput(): Promise<string> {
    return await this.statusInput.getAttribute('value');
  }

  async setCustomInput(custom: string): Promise<void> {
    await this.customInput.sendKeys(custom);
  }

  async getCustomInput(): Promise<string> {
    return await this.customInput.getAttribute('value');
  }

  async setCreatedByInput(createdBy: string): Promise<void> {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput(): Promise<string> {
    return await this.createdByInput.getAttribute('value');
  }

  async setCreateDateInput(createDate: string): Promise<void> {
    await this.createDateInput.sendKeys(createDate);
  }

  async getCreateDateInput(): Promise<string> {
    return await this.createDateInput.getAttribute('value');
  }

  async setModifyDateInput(modifyDate: string): Promise<void> {
    await this.modifyDateInput.sendKeys(modifyDate);
  }

  async getModifyDateInput(): Promise<string> {
    return await this.modifyDateInput.getAttribute('value');
  }

  async projectSelectLastOption(): Promise<void> {
    await this.projectSelect.all(by.tagName('option')).last().click();
  }

  async projectSelectOption(option: string): Promise<void> {
    await this.projectSelect.sendKeys(option);
  }

  getProjectSelect(): ElementFinder {
    return this.projectSelect;
  }

  async getProjectSelectedOption(): Promise<string> {
    return await this.projectSelect.element(by.css('option:checked')).getText();
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

export class WaveDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-wave-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-wave'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
