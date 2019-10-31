package pages.exceptionscreen;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ExceptionReservedAccount {

    private SelenideElement titleReservedAccountPage = $("[class='step-title']");

    public String getTitleReservedAccountPage(){
        return titleReservedAccountPage.waitUntil(Condition.visible,10000).getText();
    }
}
