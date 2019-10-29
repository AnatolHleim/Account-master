import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.firstscreen.FirstScreenInitAccounts;
import utilites.ParserJson;
import utilites.RandomString;

import static com.codeborne.selenide.Selenide.*;


public class ValidationFirstScreen {
    private FirstScreenInitAccounts firstScreenInitAccounts;
    private RandomString randomString;
    private ParserJson parserJson;

    @BeforeMethod
    public void init() {
        firstScreenInitAccounts = new FirstScreenInitAccounts();
        randomString = new RandomString(9);
        parserJson = new ParserJson("data.json");
        open(parserJson.value("URL"));
        try {
            switchTo().alert().accept();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    protected void verifyNoMoreMaxSizePhoneInput () {
        firstScreenInitAccounts
                .inputDataPhoneField(parserJson.value("phoneMoreThan9Digit"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataPhoneField().length(), 18);
    }

    @Test
    protected void verifyMaxSizePhoneInput() {
        firstScreenInitAccounts
                .inputDataPhoneField(randomString.nextString())
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataPhoneField().length(), 18);
    }

    @Test
    protected void verifyMaxSizeUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNP"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataUNPField().length(), 9);
    }

    @Test
    protected void verifyNoMoreMaxSizeUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPMoreThan9Digit"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataUNPField().length(), 9);
    }

    @Test
    protected void verifyErrorMessageEmptyUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPEmpty"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorUNP(), parserJson.value("textErrorEmptyUNP"));
    }

    @Test
    protected void verifyErrorMessageIncorrectMaskUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPIncorrectMask"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorUNP(), parserJson.value("textErrorInvalidMaskUNP"));
    }

    @Test
    protected void verifyErrorMessageLessThanValidCountDigitsUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPLessThan9Digit"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorUNP(), parserJson.value("textErrorEmptyUNP"));
    }

    @Test
    protected void verifyErrorMessageLessThanValidCountDigitsPhoneInput() {
        firstScreenInitAccounts
                .inputDataPhoneField(parserJson.value("phoneLessThan9Digit"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorPhone(), parserJson.value("textErrorEmptyPhone"));
    }

    @Test
    protected void verifyErrorMessageEmptyPhoneInput() {
        firstScreenInitAccounts
                .inputDataPhoneField(parserJson.value("phoneEmpty"))
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorPhone(), parserJson.value("textErrorEmptyPhone"));
    }

    @Test
    protected void verifyErrorMessageEmptyAgreeCheckBox() {
        firstScreenInitAccounts
                .getCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorCheckBoxValidation(), parserJson.value("textErrorEmptyCheckBox"));
    }
    @Test
    protected void verifyHelpIconClickTextIsVisible() {
        firstScreenInitAccounts
                .clickHelpIcon();
        Assert.assertEquals(firstScreenInitAccounts.getTextPopUpHelp(), parserJson.value("helpPopUpFirstScreen"));
    }
    @Test
    protected void verifyErrorMessageEmptySMSCode() {
        firstScreenInitAccounts
                .enterAndSendValidDataToSMSCode(parserJson.value("UNP"), randomString.nextString())
                .clickSubmitToSecondScreen();
        Assert.assertEquals(firstScreenInitAccounts.getErrorSMS(), parserJson.value("textErrorEmptySMS"));
    }
    @Test
    protected void verifyErrorMessageThanLess4CharSMSCode() {
        firstScreenInitAccounts
                .enterAndSendValidDataToSMSCode(parserJson.value("UNP"), randomString.nextString())
                .inputDataSMSField("1")
                .clickSubmitToSecondScreen();
        Assert.assertEquals(firstScreenInitAccounts.getErrorSMS(), parserJson.value("textErrorLess4CharSMS"));
    }

    @Test
    protected void verifyTextNearCheckBoxIsCorrect() {
        Assert.assertEquals(firstScreenInitAccounts.getTextNearCheckBox(), parserJson.value("textNearCheckbox"));
    }

    @Test(enabled = false, description = "15 minutes")
    protected void verifyNotificatorMoreThanMaxTryingSMSCode() {
        firstScreenInitAccounts
                .enterAndSendValidDataToSMSCode(parserJson.value("UNP"), randomString.nextString());
        firstScreenInitAccounts.sendTry(5);
        Assert.assertEquals(firstScreenInitAccounts.getNotifyText(), parserJson.value("textMoreTrying"));
    }
    @AfterMethod
    public void finalized() {
        clearBrowserCookies();
    }
}
