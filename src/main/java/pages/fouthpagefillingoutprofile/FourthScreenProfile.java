package pages.fouthpagefillingoutprofile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FourthScreenProfile {
    private SelenideElement addressRegistration = $(By.id("Address"));
    SelenideElement switcherUsa = $(By.id("IsUsaResident"));
public void enterAddress(String address){
    addressRegistration.waitUntil(Condition.visible,10000).val(address);

}
}
