import { element, by, ElementFinder } from 'protractor';

export class BlueprintComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-blueprint div table .btn-danger'));
  title = element.all(by.css('jhi-blueprint div h2#page-heading span')).first();
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

export class BlueprintUpdatePage {
  pageTitle = element(by.id('jhi-blueprint-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  titleInput = element(by.id('field_title'));
  typeInput = element(by.id('field_type'));
  descriptionInput = element(by.id('field_description'));
  templateInput = element(by.id('field_template'));
  customInput = element(by.id('field_custom'));

  workloadSelect = element(by.id('field_workload'));

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

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setTemplateInput(template: string): Promise<void> {
    await this.templateInput.sendKeys(template);
  }

  async getTemplateInput(): Promise<string> {
    return await this.templateInput.getAttribute('value');
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

export class BlueprintDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-blueprint-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-blueprint'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
