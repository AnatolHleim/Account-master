import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.firstscreen.FirstScreenInitAccounts;
import pages.fouthscreen.FourthScreenProfile;
import pages.secondscreen.SecondScreenSelectPackageAndCurrency;
import pages.thirdscreen.ThirdScreenLoadPhoto;
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
        firstScreenInitAccounts.enterAndSendValidDataToSMSCode(parserJson.value("UNP"), randomString.nextString())
                .inputDataSMSField(GenerateCodePage.getSMSCode(firstScreenInitAccounts.getDataPhoneField()))
                .clickSubmitToSecondScreen();
        secondScreenSelectPackageAndCurrency.selectPackage(1)
                .clickNext();
        thirdPageLoadPhoto.inputImageOne("01.jpg")
                .inputImageTwo("02.jpg")
                .inputImageThree("03.jpg")
                .clickNextOnThirdPage();
        fouthPageProfile.enterAddress("ауэзова");
    }
}
