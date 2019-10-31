import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.firstscreen.FirstScreenInitAccounts;
import pages.fouthpagefillingoutprofile.FourthScreenProfile;
import pages.secondscreencurrencyandpackageselect.SecondScreenSelectPackageAndCurrency;
import pages.thirdscreenloadphoto.ThirdScreenLoadPhoto;
import utilites.GenerateCodePage;
import utilites.ParserJson;
import utilites.RandomString;

import static com.codeborne.selenide.Selenide.*;

public class FullProcess {
    private FirstScreenInitAccounts firstScreenInitAccounts;
    private SecondScreenSelectPackageAndCurrency secondScreenSelectPackageAndCurrency;
    private ThirdScreenLoadPhoto thirdPageLoadPhoto;
    private FourthScreenProfile fouthPageProfile;
    private RandomString randomString;
    private ParserJson parserJson;

    @BeforeMethod
    public void init() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        firstScreenInitAccounts = new FirstScreenInitAccounts();
        secondScreenSelectPackageAndCurrency = new SecondScreenSelectPackageAndCurrency();
        thirdPageLoadPhoto = new ThirdScreenLoadPhoto();
        fouthPageProfile = new FourthScreenProfile();
        randomString = new RandomString();
        parserJson = new ParserJson("data.json");
        open(parserJson.value("URL"));
    }

    @Test
    public void validateFullProcess() {
        firstScreenInitAccounts.inputDataUNPField(parserJson.value("UNP"))
                .inputDataPhoneField(randomString.nextString())
                .inputDataSMSField(GenerateCodePage.getSMSCode(firstScreenInitAccounts.getDataPhoneField()))
                .clickGoToSecondScreenButton();
        secondScreenSelectPackageAndCurrency.selectPackage(1)
                .clickNextToThirdScreen();
        thirdPageLoadPhoto.inputImageOne("01.jpg")
                .inputImageTwo("02.jpg")
                .inputImageThree("03.jpg")
                .inputImageFour("04.jpg")
               .clickNextToFourthPage();
        sleep(60000000);
    }

}
