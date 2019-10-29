package pages.exceptionscreen;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EntityScreen {
    private SelenideElement titleEntityPage = $("");

    public String getTitleEntityPage(){
        return titleEntityPage.waitUntil(Condition.visible,10000).getText();
    }
}
