import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basicpage.BasicElementOnScreen;
import pages.exceptionscreen.ExceptionReservedAccount;
import pages.firstscreen.FirstScreenInitAccounts;
import utilites.GenerateCodePage;
import utilites.ParserJson;
import utilites.RandomString;

import static com.codeborne.selenide.Selenide.*;


public class ValidationFirstScreen {
    private FirstScreenInitAccounts firstScreenInitAccounts;
    private RandomString randomString;
    private ParserJson parserJson;
    private BasicElementOnScreen basicElementOnScreen;
    private ExceptionReservedAccount exceptionReservedAccount;

    @BeforeMethod
    public void init() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        firstScreenInitAccounts = new FirstScreenInitAccounts();
        exceptionReservedAccount = new ExceptionReservedAccount();
        randomString = new RandomString(9);
        parserJson = new ParserJson("data.json");
        basicElementOnScreen = new BasicElementOnScreen();
        open(parserJson.value("URL"));
        try {
            switchTo().alert().accept();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    protected void verifyBackGroundImage () {
        Assert.assertEquals(basicElementOnScreen.getBackGroundImageName(), parserJson.value("backGroundImageAccount"));
    }

    @Test
    protected void verifyTextOnFooter () {
        Assert.assertEquals(basicElementOnScreen.getTextOnFooter(), parserJson.value("textOnFooter"));
    }

    @Test
    protected void verifyLinkOnFooter () {
        Assert.assertEquals(basicElementOnScreen.linkOnAlfaBankTitleFooter(), parserJson.value("titleAlfaSite"));
    }

    @Test
    protected void verifySupportPhoneVisible () {
        Assert.assertTrue(basicElementOnScreen.isVisibleBlockSupportPhone());
    }

    @Test
    protected void verifySupportChatVisible () {
        Assert.assertTrue(basicElementOnScreen.isVisibleBlockSupportChat());
    }
    @Test
    protected void verifyLogoPopUpHiddenWhenClickContinue () {
        basicElementOnScreen.clickOnLogo().clickContinueOnPopUp();
        Assert.assertFalse(basicElementOnScreen.isVisibleFramePopUp());
    }

    @Test
    protected void verifyDataSaveWhenClickContinueOnLogoPopUp () {
        firstScreenInitAccounts.inputDataUNPField(parserJson.value("UNP"));
        basicElementOnScreen.clickOnLogo().clickContinueOnPopUp();
        Assert.assertEquals(firstScreenInitAccounts.getDataUNPField(),parserJson.value("UNP"));
    }

    @Test
    protected void verifyRedirectToFirstScreenAfterClickAbortOnPopUpLogo () {
        Assert.assertEquals(basicElementOnScreen.clickOnLogo().clickButtonAbort().getTextDownTitle(), parserJson.value("textDownTitleFirstScreen"));
    }

    @Test
    protected void verifyDataNotSaveAfterClickAbortOnPopUpLogo () {
        firstScreenInitAccounts.inputDataUNPField(parserJson.value("UNP"));
        basicElementOnScreen.clickOnLogo().clickButtonAbort();
        Assert.assertTrue(firstScreenInitAccounts.getDataUNPField().isEmpty());
    }

    @Test
    public void verifyExceptionIfReservedAccount(){
        firstScreenInitAccounts.inputDataUNPField(parserJson.value("UNP_RESERVED_ACCOUNT"))
                .inputDataPhoneField(randomString.nextString())
                .activateCheckBoxAgree()
                .clickToGetCodeButton()
                .inputDataSMSField(GenerateCodePage.getSMSCode(firstScreenInitAccounts.getDataPhoneField()))
                .clickGoToSecondScreenButton();
        Assert.assertEquals(exceptionReservedAccount.getTitleReservedAccountPage(), parserJson.value("titleReservedAccountException"));
    }

    @Test
    protected void verifyNoMoreMaxSizePhoneInput () {
        firstScreenInitAccounts
                .inputDataPhoneField(parserJson.value("phoneMoreThan9Digit"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataPhoneField().length(), 18);
    }

    @Test
    protected void verifyMaxSizePhoneInput() {
        firstScreenInitAccounts
                .inputDataPhoneField(randomString.nextString())
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataPhoneField().length(), 18);
    }

    @Test
    protected void verifyMaxSizeUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNP"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataUNPField().length(), 9);
    }

    @Test
    protected void verifyNoMoreMaxSizeUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPMoreThan9Digit"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getDataUNPField().length(), 9);
    }

    @Test
    protected void verifyErrorMessageEmptyUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPEmpty"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorUNP(), parserJson.value("textErrorEmptyUNP"));
    }

    @Test
    protected void verifyErrorMessageIncorrectMaskUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPIncorrectMask"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorUNP(), parserJson.value("textErrorInvalidMaskUNP"));
    }

    @Test
    protected void verifyErrorMessageLessThanValidCountDigitsUNPInput() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNPLessThan9Digit"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorUNP(), parserJson.value("textErrorEmptyUNP"));
    }

    @Test
    protected void verifyErrorMessageLessThanValidCountDigitsPhoneInput() {
        firstScreenInitAccounts
                .inputDataPhoneField(parserJson.value("phoneLessThan9Digit"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorPhone(), parserJson.value("textErrorEmptyPhone"));
    }

    @Test
    protected void verifyErrorMessageEmptyPhoneInput() {
        firstScreenInitAccounts
                .inputDataPhoneField(parserJson.value("phoneEmpty"))
                .clickToGetCodeButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorPhone(), parserJson.value("textErrorEmptyPhone"));
    }

    @Test
    protected void verifyErrorMessageEmptyAgreeCheckBox() {
        firstScreenInitAccounts
                .clickToGetCodeButton();
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
                .inputDataUNPField(parserJson.value("UNP"))
                .inputDataPhoneField(randomString.nextString())
                .clickToGetCodeButton()
                .inputDataSMSField(firstScreenInitAccounts.getDataPhoneField())
                .clickGoToSecondScreenButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorSMS(), parserJson.value("textErrorEmptySMS"));
    }
    @Test
    protected void verifyErrorMessageThanLess4CharSMSCode() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNP"))
                .inputDataPhoneField(randomString.nextString())
                .clickToGetCodeButton()
                .inputDataSMSField(parserJson.value("shortSms"))
                .clickGoToSecondScreenButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorSMS(), parserJson.value("textErrorLess4CharSMS"));
    }

    @Test
    protected void verifyErrorMessageThanIncorrectSMSCode() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNP"))
                .inputDataPhoneField(randomString.nextString())
                .clickToGetCodeButton()
                .inputDataSMSField(parserJson.value("incorrectSms"))
                .clickGoToSecondScreenButton();
        Assert.assertEquals(firstScreenInitAccounts.getErrorSMS(), parserJson.value("textErrorLess4CharSMS"));
    }
    @Test
    protected void verifyTextNearCheckBoxIsCorrect() {
        Assert.assertEquals(firstScreenInitAccounts.getTextNearCheckBox(), parserJson.value("textNearCheckbox"));
    }

    @Test(enabled = false, description = "15 minutes")
    protected void verifyNotificatorMoreThanMaxTryingSMSCode() {
        firstScreenInitAccounts
                .inputDataUNPField(parserJson.value("UNP"))
                .inputDataPhoneField(randomString.nextString())
                .clickToGetCodeButton();
        firstScreenInitAccounts.sendTryToGetSmsCode(5);
        Assert.assertEquals(firstScreenInitAccounts.getNotifyText(), parserJson.value("textMoreTrying"));
    }
    @AfterMethod
    public void finalized() {
        clearBrowserCookies();

    }
}
