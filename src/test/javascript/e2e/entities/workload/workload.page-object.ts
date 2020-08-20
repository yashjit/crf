import { element, by, ElementFinder } from 'protractor';

export class WorkloadComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-workload div table .btn-danger'));
  title = element.all(by.css('jhi-workload div h2#page-heading span')).first();
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

export class WorkloadUpdatePage {
  pageTitle = element(by.id('jhi-workload-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  titleInput = element(by.id('field_title'));
  typeInput = element(by.id('field_type'));
  movegroupInput = element(by.id('field_movegroup'));
  serverNameInput = element(by.id('field_serverName'));
  serverTierInput = element(by.id('field_serverTier'));
  osInput = element(by.id('field_os'));
  cloudInfoInput = element(by.id('field_cloudInfo'));
  descriptionInput = element(by.id('field_description'));
  customInput = element(by.id('field_custom'));
  statusInput = element(by.id('field_status'));
  createDateInput = element(by.id('field_createDate'));
  modifyDateInput = element(by.id('field_modifyDate'));
  createdByInput = element(by.id('field_createdBy'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTitleInput(title: string): Promise<void> {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput(): Promise<string> {
    return await this.titleInput.getAttribute('value');
  }

  async setTypeInput(type: string): Promise<void> {
    await this.typeInput.sendKeys(type);
  }

  async getTypeInput(): Promise<string> {
    return await this.typeInput.getAttribute('value');
  }

  async setMovegroupInput(movegroup: string): Promise<void> {
    await this.movegroupInput.sendKeys(movegroup);
  }

  async getMovegroupInput(): Promise<string> {
    return await this.movegroupInput.getAttribute('value');
  }

  async setServerNameInput(serverName: string): Promise<void> {
    await this.serverNameInput.sendKeys(serverName);
  }

  async getServerNameInput(): Promise<string> {
    return await this.serverNameInput.getAttribute('value');
  }

  async setServerTierInput(serverTier: string): Promise<void> {
    await this.serverTierInput.sendKeys(serverTier);
  }

  async getServerTierInput(): Promise<string> {
    return await this.serverTierInput.getAttribute('value');
  }

  async setOsInput(os: string): Promise<void> {
    await this.osInput.sendKeys(os);
  }

  async getOsInput(): Promise<string> {
    return await this.osInput.getAttribute('value');
  }

  async setCloudInfoInput(cloudInfo: string): Promise<void> {
    await this.cloudInfoInput.sendKeys(cloudInfo);
  }

  async getCloudInfoInput(): Promise<string> {
    return await this.cloudInfoInput.getAttribute('value');
  }

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setCustomInput(custom: string): Promise<void> {
    await this.customInput.sendKeys(custom);
  }

  async getCustomInput(): Promise<string> {
    return await this.customInput.getAttribute('value');
  }

  async setStatusInput(status: string): Promise<void> {
    await this.statusInput.sendKeys(status);
  }

  async getStatusInput(): Promise<string> {
    return await this.statusInput.getAttribute('value');
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

  async setCreatedByInput(createdBy: string): Promise<void> {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput(): Promise<string> {
    return await this.createdByInput.getAttribute('value');
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

export class WorkloadDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-workload-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-workload'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
