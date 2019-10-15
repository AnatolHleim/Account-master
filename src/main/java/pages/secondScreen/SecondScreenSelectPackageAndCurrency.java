package pages.secondScreen;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SecondScreenSelectPackageAndCurrency {
    private SelenideElement textUNP = $(By.xpath("//label[@for='Unp']/../following::div/div[@class='col-md-8']"));
    private SelenideElement textOwnerUNP = $(By.xpath("//label[@for='CompanyName']/../following::div/div[@class='col-md-8']"));
    private List<SelenideElement> listBankPackage = $$("[class='package']");
    private SelenideElement selectRadio = $("#RepresentativeTypes");
    private SelenideElement radioButtonOwner = $(By.id("RepresentativeTypes_NoRepresentetive"));
    private SelenideElement radioButtonRepresent = $("#RepresentativeTypes_ThereIsRepresentetive");
    private SelenideElement buttonBackToFirstScreen = $("[loader-type='start-page-register']");
    private SelenideElement checkBoxUSD = $(By.id("IsUsd"));
    private SelenideElement checkBoxeEURO = $(By.id("IsEur"));
    private SelenideElement checkBoxeRusRUB = $(By.id("IsRub"));
    private SelenideElement buttonSubmit = $("[data-bind='events:{click:btnContinueClicked}']");
    private SelenideElement notifyAlert = $(By.className("ui-pnotify-text"));

    public String getTextUNP() {
        return textUNP.waitUntil(Condition.visible,10000).text();
    }

    public String getTextOwnerUNP() {
        return textOwnerUNP.waitUntil(Condition.visible,10000).text();
    }

    public SecondScreenSelectPackageAndCurrency selectPackage(int position) {
        listBankPackage.get(0).waitUntil(Condition.visible,10000);
        listBankPackage.get(position).click();
        return this;
    }
    public SecondScreenSelectPackageAndCurrency clickRadioButtonOwner(){
        radioButtonOwner.waitUntil(Condition.exist,10000).click();
        return this;
    }

    public SecondScreenSelectPackageAndCurrency clickRadioButtonRepresent(){
        selectRadio.selectRadio("Я представитель этого предпринимателя");
        return this;
    }

    public String getNotifyText(){
        return notifyAlert.waitUntil(Condition.visible,10000).text();
    }

    public void clickNext (){
        buttonSubmit.waitUntil(Condition.visible,10000).click();
    }
}
