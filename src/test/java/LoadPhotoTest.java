import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.firstscreen.FirstScreenInitAccounts;
import pages.secondscreencurrencyandpackageselect.SecondScreenSelectPackageAndCurrency;
import pages.thirdscreenloadphoto.ThirdScreenLoadPhoto;
import utilites.GenerateCodePage;
import utilites.ParserJson;
import utilites.RandomString;

import static com.codeborne.selenide.Selenide.open;

public class LoadPhotoTest {
    private ThirdScreenLoadPhoto thirdPageLoadPhoto;

    @BeforeClass
    public void init(){
        FirstScreenInitAccounts firstScreenInitAccounts = new FirstScreenInitAccounts();
        thirdPageLoadPhoto = new ThirdScreenLoadPhoto();
        ParserJson parserJson = new ParserJson("data.json");
        open(parserJson.value("URL"));
        firstScreenInitAccounts.inputDataUNPField(parserJson.value("UNP"))
                .inputDataPhoneField(new RandomString(9).nextString())
                .inputDataSMSField(GenerateCodePage.getSMSCode(firstScreenInitAccounts.getDataPhoneField()))
                .clickGoToSecondScreenButton();
        new SecondScreenSelectPackageAndCurrency().selectPackage(1)
                .clickNextToThirdScreen();
    }
    @Test(dataProvider= "data")
    public void verifyAllFormatUploadPhoto(String [] data) {
        thirdPageLoadPhoto.inputImageOne(data[0])
                .inputImageTwo(data[1])
                .inputImageThree(data[2]);
    }

    @DataProvider(name="data")
    public static Object[][] dataProviderFile(){
        return new Object[][]{new String []{"01.jpg","02.jpg","03.jpg"},
                              new String []{"01.png","02.png","03.png"},
                              new String []{"01.pdf","02.pdf","03.pdf"},
                              new String []{"01.bmp","02.bmp","03.bmp"}};
    }
}
