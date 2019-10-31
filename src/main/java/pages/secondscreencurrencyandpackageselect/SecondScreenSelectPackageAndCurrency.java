package pages.secondscreencurrencyandpackageselect;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.firstscreen.FirstScreenInitAccounts;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SecondScreenSelectPackageAndCurrency {
    private SelenideElement textUNP = $(By.xpath("//label[@for='Unp']/../following::div/div[@class='col-md-8']"));
    private SelenideElement textOwnerUNP = $(By.xpath("//label[@for='CompanyName']/../following::div/div[@class='col-md-8']"));
    private List<SelenideElement> listBankPackage = $$(("[data-bind='events:{click:packageClicked}']"));
    private SelenideElement blockBankPackage = $("[class='packages']");
    private SelenideElement radioButtonOwner = $(By.id("RepresentativeTypes_NoRepresentetive"));
    private SelenideElement radioButtonRepresent = $(By.id("RepresentativeTypes_ThereIsRepresentetive"));
    private SelenideElement buttonBackToFirstScreen = $("[loader-type='start-page-register']");
    private SelenideElement checkBoxUSD = $(By.id("IsUsd"));
    private SelenideElement checkBoxEURO = $(By.id("IsEur"));
    private SelenideElement checkBoxRusRUB = $(By.id("IsRub"));
    private SelenideElement buttonSubmit = $("[data-bind='events:{click:btnContinueClicked}']");
    private SelenideElement notifyAlert = $(By.className("ui-pnotify-text"));

    public String getTextUNP() {
        return textUNP.waitUntil(Condition.visible, 10000).text();
    }

    public String getTextOwnerUNP() {
        return textOwnerUNP.waitUntil(Condition.visible, 10000).text();
    }

    public SecondScreenSelectPackageAndCurrency selectPackage(int position) {
        listBankPackage.get(position).waitUntil(Condition.visible, 10000);
        listBankPackage.get(position).click();
        return this;
    }

    public boolean isPackageSelected(int position) {
        try {
            listBankPackage.get(position).shouldHave(Condition.attribute("class", "package selected-package collapsed-package"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPackageCollapsed(int position) {
        try {
            listBankPackage.get(position).shouldHave(Condition.attribute("class", "package selected-package extended_package"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SecondScreenSelectPackageAndCurrency clickRadioButtonOwner() {
        radioButtonOwner.waitUntil(Condition.exist, 10000).click();
        blockBankPackage.waitUntil(Condition.visible, 5000);
        return this;
    }

    public SecondScreenSelectPackageAndCurrency clickRadioButtonRepresent() {
        radioButtonRepresent.waitUntil(Condition.exist, 10000).click();
        blockBankPackage.waitUntil(Condition.hidden, 5000);
        return this;
    }

    public String getNotifyText() {
        return notifyAlert.waitUntil(Condition.visible, 10000).text();
    }

    public void clickNextToThirdScreen() {
        buttonSubmit.waitUntil(Condition.visible, 10000).click();
    }

    public boolean isBlockPackagePresented() {
        return blockBankPackage.isDisplayed();
    }

    private void selectCurrency(SelenideElement element) {
        element.shouldNot(Condition.checked).click();
    }

    public boolean isCheckBoxEuroSelected() {
        return checkBoxEURO.isSelected() && checkBoxRusRUB.isSelected() && checkBoxUSD.isSelected();
    }

    public SecondScreenSelectPackageAndCurrency selectCurrencyEuroUsdRub() {
        checkBoxEURO.waitUntil(Condition.visible, 15000);
        selectCurrency(checkBoxEURO);
        selectCurrency(checkBoxUSD);
        selectCurrency(checkBoxRusRUB);
        return this;
    }

    public FirstScreenInitAccounts clickButtonBackToFirstScreen() {
        buttonBackToFirstScreen.waitUntil(Condition.visible, 10000).click();
        return new FirstScreenInitAccounts();
    }
}
