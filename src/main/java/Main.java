import pools.ResumeUpdaterPool;
import resume.ResumeHeadHunter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ResumeUpdaterPool resumeUpdaterPool = new ResumeUpdaterPool();
        resumeUpdaterPool.add(new ResumeHeadHunter(), "myLogin", "myPassword");
        Thread.sleep(1000 * 60 * 2); // заглушка
        resumeUpdaterPool.closeAll();
    }
}
