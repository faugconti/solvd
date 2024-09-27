package delivery.util.Multi;

public class ThreadRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("[Thread using Runnable interface: "+ MyThread.currentThread().getName()+"]");
    }
}
