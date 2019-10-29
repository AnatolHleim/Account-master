package pages.basicelement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.firstscreen.FirstScreenInitAccounts;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class BasicElementOnScreen {
    private SelenideElement backGroundAccountScreen = $("");
    private SelenideElement logoAlfa = $("");
    private SelenideElement framePopUp = $("");//если не фрейм, то текст
    private SelenideElement popUpButtonContinue = $("");
    private SelenideElement popUpButtonAbort = $("");
    private SelenideElement footerText = $("");
    private SelenideElement linkOnFooter = $("");
    private SelenideElement blockSupportPhone = $("");
    private SelenideElement blockSupportChat = $("");

    public String getBackGroundImageName() {
        return backGroundAccountScreen.getText();
    }

    public BasicElementOnScreen clickOnLogo() {
        logoAlfa.waitUntil(Condition.visible, 10000).click();
        return this;
    }

    public BasicElementOnScreen clickContinueOnPopUp() {
        switchTo().frame(framePopUp);
        popUpButtonContinue.waitUntil(Condition.visible, 10000).click();
        return this;
    }

    public FirstScreenInitAccounts clickButtonAbort() {
        switchTo().frame(framePopUp);
        popUpButtonAbort.waitUntil(Condition.visible, 15000).click();
        return new FirstScreenInitAccounts();
    }

    public String getTextOnFooter() {
        return footerText.waitUntil(Condition.visible, 10000).getText();
    }

    public String linkOnAlfaBankTitle() {
        linkOnFooter.waitUntil(Condition.visible, 10000).click();
        String title = switchTo().frame(1).getTitle();
        switchTo().frame(1).close();
        switchTo().frame(0);
        return title;
    }

    public boolean isVisibleBlockSupportPhone() {
        return blockSupportPhone.isDisplayed();
    }

    public boolean isVisibleBlockSupportChat() {
        return blockSupportChat.isDisplayed();
    }
}
