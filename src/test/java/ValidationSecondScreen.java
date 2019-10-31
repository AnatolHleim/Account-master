import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.exceptionscreen.ExceptionEntityScreen;
import pages.firstscreen.FirstScreenInitAccounts;
import pages.secondscreencurrencyandpackageselect.SecondScreenSelectPackageAndCurrency;
import utilites.GenerateCodePage;
import utilites.ParserJson;
import utilites.RandomString;

import static com.codeborne.selenide.Selenide.*;

public class ValidationSecondScreen {

    private SecondScreenSelectPackageAndCurrency secondScreenSelectPackageAndCurrency;
    private ExceptionEntityScreen entityScreenException;
    private ParserJson parserJson;

    @BeforeMethod
    public void init(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        FirstScreenInitAccounts firstScreenInitAccounts = new FirstScreenInitAccounts();
        secondScreenSelectPackageAndCurrency = new SecondScreenSelectPackageAndCurrency();
        entityScreenException = new ExceptionEntityScreen();
        parserJson = new ParserJson("data.json");
        RandomString randomString = new RandomString();
        open(parserJson.value("URL"));
        try {
            switchTo().alert().accept();
        } catch (Exception e) {
            e.getMessage();
        }
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNP"))
                .inputDataPhoneField(randomString.nextString())
                .inputDataSMSField(GenerateCodePage.getSMSCode(firstScreenInitAccounts.getDataPhoneField()))
                .clickGoToSecondScreenButton();
    }

    @Test
    public void validateNotificationWhenNotSelectedPackage(){
        secondScreenSelectPackageAndCurrency.clickNextToThirdScreen();
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.getNotifyText(), parserJson.value("notifyNotSelectedPackage"));
    }

    @Test
    public void verifyCompanyNameThisUnp() {
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.getTextOwnerUNP(), parserJson.value("companyName"));
    }

    @Test
    public void verifyCompanyUNPisEnteredOnFirstPage() {
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.getTextUNP(), parserJson.value("UNP"));
    }

    @Test
    public void verifyExceptionScreenIfSelectedRadioButtonRepresent(){
        secondScreenSelectPackageAndCurrency.clickRadioButtonRepresent()
                                            .clickNextToThirdScreen();
        Assert.assertEquals(entityScreenException.getTitleEntityPage(), parserJson.value("titleEntityScreenException"));
    }

    @Test
    public void verifyHiddenBlockPackageIfSelectedRadioButtonRepresent(){
        secondScreenSelectPackageAndCurrency.clickRadioButtonRepresent();
        Assert.assertFalse(secondScreenSelectPackageAndCurrency.isBlockPackagePresented());
    }

    @Test
    public void verifySaveSelectedCurrencyAfterChangeRadioButton(){
        secondScreenSelectPackageAndCurrency.selectCurrencyEuroUsdRub()
                .clickRadioButtonRepresent()
                .clickRadioButtonOwner();
        Assert.assertTrue(secondScreenSelectPackageAndCurrency.isCheckBoxEuroSelected());
    }

    @Test
    public void verifySaveSelectedPackageAfterChangeRadioButton(){
        secondScreenSelectPackageAndCurrency.selectPackage(1)
                .clickRadioButtonRepresent()
                .clickRadioButtonOwner();
        Assert.assertTrue(secondScreenSelectPackageAndCurrency.isPackageSelected(1));
    }

    @Test
    public void verifyCollapsedSelectedPackageAfterDoubleClick(){
        secondScreenSelectPackageAndCurrency.selectPackage(1)
                                            .selectPackage(1);
        Assert.assertTrue(secondScreenSelectPackageAndCurrency.isPackageCollapsed(1));
    }

    @Test
    public void verifyButtonBackRedirectToFirstScreen(){
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.clickButtonBackToFirstScreen().getTextDownTitle(), parserJson.value("textDownTitleFirstScreen"));
    }

    @AfterMethod
    public void finalized() {
        clearBrowserCookies();

    }
}
