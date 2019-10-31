package pages.exceptionscreen;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ExceptionEntityScreen {

    private SelenideElement titleEntityPage = $("[class='open-account__legal_person__subtitle']");

    public String getTitleEntityPage(){
        return titleEntityPage.waitUntil(Condition.visible,10000).getText();
    }
}
