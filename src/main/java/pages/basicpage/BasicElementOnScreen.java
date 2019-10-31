package pages.basicpage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.firstscreen.FirstScreenInitAccounts;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class BasicElementOnScreen {
    private SelenideElement backGroundAccountScreen = $("[class='alfa-background alfa-background_open-account-image']");
    private SelenideElement logoAlfa = $("[class='header-container__logo logo']");
    private SelenideElement framePopUp = $("[class='k-widget k-window']");
    private SelenideElement popUpButtonContinue = $("[data-bind='click: closeWindowWithCallBack']");
    private SelenideElement popUpButtonAbort = $("[data-bind='click: closeWindow']");
    private SelenideElement footerText = $("[class='extra-small-text']");
    private SelenideElement linkOnFooter = $("[class='link link-extra-small']");
    private SelenideElement blockSupportPhone = $(By.id("support-menu"));
    private SelenideElement blockSupportChat = $("[data-bind='click:openChat']");

    public String getBackGroundImageName() {
        return backGroundAccountScreen.getCssValue("background-image");
    }

    public BasicElementOnScreen clickOnLogo() {
        logoAlfa.waitUntil(Condition.visible, 10000).click();
        return this;
    }

    public void clickContinueOnPopUp() {
        isVisibleFramePopUp();
        popUpButtonContinue.waitUntil(Condition.visible, 10000).click();
    }

    public FirstScreenInitAccounts clickButtonAbort() {
        isVisibleFramePopUp();
        popUpButtonAbort.waitUntil(Condition.visible, 15000).click();
        return new FirstScreenInitAccounts();
    }

    public boolean isVisibleFramePopUp() {
        return framePopUp.isDisplayed();
    }

    public String getTextOnFooter() {
        return footerText.waitUntil(Condition.visible, 10000).getText();
    }

    public String linkOnAlfaBankTitleFooter() {
        linkOnFooter.waitUntil(Condition.visible, 10000).click();
        String title = switchTo().window(1).getTitle();
        switchTo().window(1).close();
        switchTo().window(0);
        return title;
    }

    public boolean isVisibleBlockSupportPhone() {
        return blockSupportPhone.isDisplayed();
    }

    public boolean isVisibleBlockSupportChat() {
        return blockSupportChat.isDisplayed();
    }
}
