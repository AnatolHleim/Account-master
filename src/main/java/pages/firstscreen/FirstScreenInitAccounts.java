package pages.firstscreen;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.HOME;

public class FirstScreenInitAccounts {
    private SelenideElement setValueInputUNP = $("[name='Unp'][type='text']");
    private SelenideElement getValueInputUNP = $(By.id("Unp"));
    private SelenideElement setValueInputPhone = $("[name='Phone'][type='text']");
    private SelenideElement getValueInputPhone = $(By.id("Phone"));
    private SelenideElement buttonGetCode = $("[data-bind='events:{click: btnGetSmsCodeClicked}']");
    private SelenideElement errorUnpValidation = $(By.id("Unp-error"));
    private SelenideElement errorPhoneValidation = $(By.id("Phone-error"));
    private SelenideElement inputCheckBoxAgree = $(By.id("ConditionAgree"));
    private SelenideElement errorCheckBoxValidation = $(By.id("ConditionAgree-error"));
    private SelenideElement setValueSMSCode = $(By.name("SmsCode"));
    private SelenideElement errorSMSCodeValidation = $(By.id("SmsCode-error"));
    private SelenideElement buttonSubmit = $("[data-bind='events:{click:btnConfirmSmsCodeClicked}']");
    private SelenideElement buttonTryNewSMS = $("[data-bind='disabled: isSendSmsBtnDisabled, events:{click:btnSendAgainClicked}']");
    private SelenideElement timerNextCode = $("[data-bind='text:timerText']");
    private SelenideElement notifyAlert = $(By.className("ui-pnotify-text"));
    private SelenideElement helpIconForPhone = $(By.className("alfa-help-icon"));
    private SelenideElement helpTextPopUp = $(By.className("popup-content"));
    private SelenideElement textNearCheckBox = $(By.xpath("//u"));

    public FirstScreenInitAccounts inputDataUNPField(String text) {
        setValueInputUNP.val(text);
        return this;
    }

    public FirstScreenInitAccounts inputDataSMSField(String text) {
        setValueSMSCode.val(text);
        return this;
    }

    public FirstScreenInitAccounts inputDataPhoneField(String text) {
        setValueInputPhone.sendKeys(HOME);
        setValueInputPhone.sendKeys(text);
        return this;
    }

    public void clickSubmitToSecondScreen() {
        buttonSubmit.click();
    }

    public String getDataPhoneField() {
        return getValueInputPhone.getValue();
    }

    public String getDataUNPField() {
        return getValueInputUNP.getValue();
    }

    public void getCodeButton() {
        buttonGetCode.click();


    }

    public String getErrorUNP() {
        return errorUnpValidation.shouldBe(Condition.visible).text();
    }

    public String getErrorPhone() {
        return errorPhoneValidation.shouldBe(Condition.visible).text();
    }
    public String getErrorSMS() {
        return errorSMSCodeValidation.shouldBe(Condition.visible).text();
    }

    private FirstScreenInitAccounts activateCheckBoxAgree() {
        inputCheckBoxAgree.shouldNot(Condition.checked).click();
        return this;
    }

    public String getErrorCheckBoxValidation() {
        return errorCheckBoxValidation.shouldBe(Condition.visible).text();
    }

    public FirstScreenInitAccounts enterAndSendValidDataToSMSCode(String UNP, String phone) {
        inputDataUNPField(UNP)
                .inputDataPhoneField(phone)
                .activateCheckBoxAgree()
                .getCodeButton();
        setValueSMSCode.waitUntil(Condition.visible,2000);
        return this;
    }
    public void sendTry(int count){
        while (count>0){
            buttonTryNewSMS.waitUntil(Condition.enabled,(Integer.parseInt(timerNextCode.waitUntil(Condition.visible,20000).getText())*1000)+2000);
            buttonTryNewSMS.click();
            count--;
        }
    }
    public String getNotifyText(){
        return notifyAlert.waitUntil(Condition.visible,5000).text();
    }

    public FirstScreenInitAccounts clickHelpIcon(){
        helpIconForPhone.waitUntil(Condition.visible, 10000).click();
        return this;
    }
    public String getTextPopUpHelp(){
        return helpTextPopUp.waitUntil(Condition.visible,2000).getText();
    }

    public String getTextNearCheckBox(){
        return textNearCheckBox.waitUntil(Condition.visible,10000).getText();
    }
}

