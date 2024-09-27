package delivery.util.Multi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppPool {

    private static final int THREAD_COUNT = 7;
    private static final int POOL_SIZE = 5;


    public static void main(String[] args){

        run_thread_pool();
        System.out.println("**Finished running simple threads**\n");

        run_javaThread_pool();
        System.out.println("**Finished running Java Thread Pool**\n");
    }

    private static void run_thread_pool(){
        ConnecPool pool = ConnecPool.getPool(POOL_SIZE);
        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i =0;i<THREAD_COUNT;i++){
            threads[i] = new Thread(() -> {
                try {
                    Connection conn = pool.getConnection();
                    //System.out.println("[" + Thread.currentThread().getName() + " got connection " + conn + "]");
                    conn.use();
                    pool.releaseConnection(conn);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
            threads[i].start();
        }
        //wait for ending all threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private static void run_javaThread_pool(){
        ConnecPool pool = ConnecPool.getPool(POOL_SIZE);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT); //create 7 threads
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    Connection conn = pool.getConnection();
                    conn.use(); // thread.wait() inside
                    pool.releaseConnection(conn);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(100000, TimeUnit.MILLISECONDS); // wait for all threads to be closed !
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //TODO: Implement previous point but with interfaces Future and CompletableStage.
    private static void run_with_interfaces(){

    }
}
