package utilites;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class GenerateCodePage {
    private GenerateCodePage(){}
    private static SelenideElement code = $(By.xpath("//pre"));
    private static final String JSON_PATH = "data.json";

    public static String getSMSCode(String phoneInput) {
        executeJavaScript("window.open('" + new ParserJson(JSON_PATH).value("URL_SMS_GENERATE") + phoneInput + "', '_blank');");
        switchTo().window(1);
        String sms = code.getText().substring(1, 5);
        switchTo().window(1).close();
        switchTo().window(0);
        return sms;
    }
}
