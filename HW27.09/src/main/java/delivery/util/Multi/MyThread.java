package delivery.util.Multi;
import java.lang.Thread;

public class MyThread extends Thread {
    @Override
    public void run(){
        System.out.println("[Thread using Thread Class: "+ MyThread.currentThread().getName()+"]");
    }
}
