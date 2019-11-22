package resume;

import base.ResumeUpdater;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResumeHeadHunter extends ResumeUpdater {

    public ResumeHeadHunter(){
        super();

    }
    public boolean login(String username, String password) throws InterruptedException {
        driver.get("https://hh.ru/account/login?backurl=%2F");
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.cssSelector("input.bloko-button.bloko-button_primary.bloko-button_stretched"));
        element.click();
        if (driver.getTitle().equals("")) return false; // если Title остался пустой, то авторизация не прошла
        return true;
    }

    public boolean resumeUp() throws InterruptedException {
        driver.get("https://barnaul.hh.ru/applicant/resumes?from=header_new");
        Thread.sleep(1000);
        WebElement elemen;
        try {
            WebElement element = driver.
                    findElement(By.xpath(".//*[@id=\"HH-React-Root\"]/div/div/div/div[1]/div[3]/div/div[1]/div[2]/span/button"));
            element.click();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
