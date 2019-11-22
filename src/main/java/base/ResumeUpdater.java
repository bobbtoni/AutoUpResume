package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public abstract class ResumeUpdater {

    protected Timer timer;
    protected WebDriver driver;
    public ResumeUpdater(){
        timer = new Timer(true);
    }
    /*
    @param username - логин
    @param password - пароль
    @return - возвращает True в случае, если авторизация прошла успешно
     */
    abstract public boolean login(String username, String password) throws InterruptedException;

    /*
    @return - возвращает False в случае, если резюме не найдено
     */
    abstract public boolean resumeUp() throws InterruptedException;

    /*
    Запуск циклического выполнения {@link ResumeUpdater#resumeUp} в фоновом режиме
    @param intervalMinutes - интервал времени в минутах
     */
    public void startTimer(int intervalMinutes){
        TimerTask ts = new TimerTask() {
            @Override
            public void run() {
                try {
                    resumeUp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(ts, 0, 1000*60*intervalMinutes);
    }

    /*
    Прервать циклический запуск {@link ResumeUpdater#resumeUp}
    Фактически будет работать, пока не выполнится текущая задача или
    все пользовательские потоки не закончат свое выполнение.
     */
    public void stopTimer(){
        timer.cancel();
    }

    /*
    Инициализация драйвера
    @pathToDriver - путь к драйверу браузера Chrome (абсолютный или относительный)
    @args - аргументы запуска браузера ("--headless" - зауск без графической оболочки)
     */
    public void initDriverChrome(String pathToDriver){
        initDriverChrome(pathToDriver, "");
    }
    public void initDriverChrome(String pathToDriver, String args){
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        ChromeOptions options = new ChromeOptions();
        if (!args.equals("")) options.addArguments(args);
        driver = new ChromeDriver(options);
        driver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS)
                .setScriptTimeout(10, TimeUnit.SECONDS);
    }

    /*  Закрыть драйвер  */
    public void closeDriver(){
        driver.close();
    }
}
