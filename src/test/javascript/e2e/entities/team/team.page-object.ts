import { element, by, ElementFinder } from 'protractor';

export class TeamComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-team div table .btn-danger'));
  title = element.all(by.css('jhi-team div h2#page-heading span')).first();
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

export class TeamUpdatePage {
  pageTitle = element(by.id('jhi-team-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  teamNameInput = element(by.id('field_teamName'));
  createDateInput = element(by.id('field_createDate'));

  organizationSelect = element(by.id('field_organization'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTeamNameInput(teamName: string): Promise<void> {
    await this.teamNameInput.sendKeys(teamName);
  }

  async getTeamNameInput(): Promise<string> {
    return await this.teamNameInput.getAttribute('value');
  }

  async setCreateDateInput(createDate: string): Promise<void> {
    await this.createDateInput.sendKeys(createDate);
  }

  async getCreateDateInput(): Promise<string> {
    return await this.createDateInput.getAttribute('value');
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

export class TeamDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-team-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-team'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
