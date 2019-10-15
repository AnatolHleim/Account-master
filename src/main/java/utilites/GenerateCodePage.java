package utilites;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class GenerateCodePage {
    private static SelenideElement code = $(By.xpath("//pre"));
    private static final String URL_GENERATOR = "https://bcsrvwebn.local.st.by/Alfa-Account/Bia.Portlets.Ib.Alfa.Membership.Login/Login/GetMCodeTest?phone=";

    public static String getSMSCode(String phoneInput) {
        executeJavaScript("window.open('" + URL_GENERATOR + phoneInput + "', '_blank');");
        switchTo().window(1);
        String sms = code.getText().substring(1, 5);
        switchTo().window(1).close();
        switchTo().window(0);
        return sms;
    }
}
