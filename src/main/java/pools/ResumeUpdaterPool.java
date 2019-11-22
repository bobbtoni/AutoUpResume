package pools;

import base.ResumeUpdater;

import java.util.ArrayList;
import java.util.List;

public class ResumeUpdaterPool {
    List<ResumeUpdater> poolResume;

    public ResumeUpdaterPool(){
        poolResume = new ArrayList<ResumeUpdater>();
    }

    /*
    Добавление резюме в пул, и запуск циклического выполнения {@link ResumeUpdater#resumeUp}
    @param resumeUpdater - экземпляр класса ResumeUpdater
    @param username - логин
    @param password - пароль
     */
    public void add(ResumeUpdater resumeUpdater, String username, String password) throws InterruptedException {
        resumeUpdater.initDriverChrome("src\\main\\resources\\chromedriver39.exe");
        if (resumeUpdater.login(username, password)){
            poolResume.add(resumeUpdater);
            resumeUpdater.startTimer(1);
        }
    }

    /* Остановить и закрыть все потоки по подъему резюме */
    public void closeAll(){
        for (ResumeUpdater resumeUpdater : poolResume){
            resumeUpdater.stopTimer();
            resumeUpdater.closeDriver();
        }
    }
}
