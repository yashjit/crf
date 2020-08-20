import { element, by, ElementFinder } from 'protractor';

export class ProjectComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-project div table .btn-danger'));
  title = element.all(by.css('jhi-project div h2#page-heading span')).first();
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

export class ProjectUpdatePage {
  pageTitle = element(by.id('jhi-project-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  projectNameInput = element(by.id('field_projectName'));
  projectTypeInput = element(by.id('field_projectType'));
  targetCloudInput = element(by.id('field_targetCloud'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  customInput = element(by.id('field_custom'));
  statusInput = element(by.id('field_status'));
  createdByInput = element(by.id('field_createdBy'));
  createDateInput = element(by.id('field_createDate'));
  modifyDateInput = element(by.id('field_modifyDate'));

  organizationSelect = element(by.id('field_organization'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setProjectNameInput(projectName: string): Promise<void> {
    await this.projectNameInput.sendKeys(projectName);
  }

  async getProjectNameInput(): Promise<string> {
    return await this.projectNameInput.getAttribute('value');
  }

  async setProjectTypeInput(projectType: string): Promise<void> {
    await this.projectTypeInput.sendKeys(projectType);
  }

  async getProjectTypeInput(): Promise<string> {
    return await this.projectTypeInput.getAttribute('value');
  }

  async setTargetCloudInput(targetCloud: string): Promise<void> {
    await this.targetCloudInput.sendKeys(targetCloud);
  }

  async getTargetCloudInput(): Promise<string> {
    return await this.targetCloudInput.getAttribute('value');
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

  async organizationSelectLastOption(): Promise<void> {
    await this.organizationSelect.all(by.tagName('option')).last().click();
  }

  async organizationSelectOption(option: string): Promise<void> {
    await this.organizationSelect.sendKeys(option);
  }

  getOrganizationSelect(): ElementFinder {
    return this.organizationSelect;
  }

  async getOrganizationSelectedOption(): Promise<string> {
    return await this.organizationSelect.element(by.css('option:checked')).getText();
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

export class ProjectDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-project-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-project'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
