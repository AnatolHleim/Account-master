package pages.threePageLoadPhoto;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;


public class ThirdScreenLoadPhoto {
    private SelenideElement inputFirstImage = $(By.xpath("//div[@page='1']//input"));
    private SelenideElement inputSecondImage = $(By.xpath("//div[@page='2']//input"));
    private SelenideElement inputThirdImage = $(By.xpath("//div[@page='3']//input"));
    private SelenideElement buttonNext = $("[data-bind='click: continueFourthStep']");
    private SelenideElement loaderPhoto = $(("[class='loadmask-msg red']"));
    private SelenideElement labelLoad = $(("[aria-label='Загрузить фото']"));

    public ThirdScreenLoadPhoto inputImageOne(String firstPassportPhoto){
        labelLoad.shouldBe(Condition.visible);
        inputFirstImage.sendKeys(getAbsolutePath("src/test/"+ firstPassportPhoto));
        loaderPhoto.shouldBe(Condition.visible);
        return this;
    }
    public ThirdScreenLoadPhoto inputImageTwo(String secondPassportPhoto){
        loaderPhoto.waitUntil(Condition.hidden,15000);
        inputSecondImage.sendKeys(getAbsolutePath("src/test/"+secondPassportPhoto));
        loaderPhoto.waitUntil(Condition.visible,2000);
        return this;
    }
    public ThirdScreenLoadPhoto inputImageThree(String thirdPassportPhoto){
        loaderPhoto.waitUntil(Condition.hidden,15000);
        inputThirdImage.sendKeys(getAbsolutePath("src/test/"+thirdPassportPhoto));
        return this;
    }
    private String getAbsolutePath(String canonical) {
        File f = new File(canonical);
        return f.getAbsolutePath();
    }
    public void clickNextOnThirdPage(){
        buttonNext.click();
    }
}
