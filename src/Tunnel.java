import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
        try {
            MainClass.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
                try {
                    System.out.println(c.getName() + " начал этап: " + description);
                    Thread.sleep(length / c.getSpeed() * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(c.getName() + " закончил этап: " + description);
                    MainClass.endOfRace.countDown();
                    MainClass.semaphore.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
