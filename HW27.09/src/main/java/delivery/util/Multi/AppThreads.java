package delivery.util.Multi;
import java.lang.Thread;

public class AppThreads {
    public static void main(String[] args){

        (new Thread(new ThreadRunnable())).start();
        (new MyThread()).start();


    }
}
