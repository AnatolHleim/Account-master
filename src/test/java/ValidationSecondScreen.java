import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.exceptionscreen.EntityScreen;
import pages.firstscreen.FirstScreenInitAccounts;
import pages.secondscreen.SecondScreenSelectPackageAndCurrency;
import utilites.GenerateCodePage;
import utilites.ParserJson;
import utilites.RandomString;

import static com.codeborne.selenide.Selenide.open;

public class ValidationSecondScreen {
    private SecondScreenSelectPackageAndCurrency secondScreenSelectPackageAndCurrency;
    private EntityScreen entityScreenException;
    private ParserJson parserJson;
    @BeforeClass
    public void init(){
        FirstScreenInitAccounts firstScreenInitAccounts = new FirstScreenInitAccounts();
        secondScreenSelectPackageAndCurrency = new SecondScreenSelectPackageAndCurrency();
        entityScreenException = new EntityScreen();
        parserJson = new ParserJson("data.json");
        RandomString randomString = new RandomString();
        open(parserJson.value("URL"));
        firstScreenInitAccounts.enterAndSendValidDataToSMSCode(parserJson.value("UNP"), randomString.nextString())
                .inputDataSMSField(GenerateCodePage.getSMSCode(firstScreenInitAccounts.getDataPhoneField()))
                .clickSubmitToSecondScreen();
    }
    @Test
    public void validateNotificationWhenNotSelectedPackage(){
        secondScreenSelectPackageAndCurrency.clickNext();
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.getNotifyText(), parserJson.value("notifyNotSelectedPackage"));
    }
    @Test
    public void verifyCompanyNameThisUnp() {
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.getTextOwnerUNP(), parserJson.value("companyName"));
    }

    @Test
    public void verifyCompanyUNPisEnteredOnFirstPage() {
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.getTextOwnerUNP(), parserJson.value("UNP"));
    }

    @Test
    public void verifyExceptionScreenIfSelectedRadioButtonRepresent(){
        secondScreenSelectPackageAndCurrency.clickRadioButtonRepresent()
                                            .clickNext();
        Assert.assertEquals(entityScreenException.getTitleEntityPage(), parserJson.value("notifyNotSelectedPackage"));
    }
    @Test
    public void verifyExceptionIfSelectedRadioButtonRepresent(){
        secondScreenSelectPackageAndCurrency.clickRadioButtonRepresent()
                .clickNext();
        Assert.assertEquals(secondScreenSelectPackageAndCurrency.getNotifyText(), parserJson.value("notifyNotSelectedPackage"));
    }
}
