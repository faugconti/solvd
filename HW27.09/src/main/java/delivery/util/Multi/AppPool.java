package delivery.util.Multi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AppPool {

    private static final int THREAD_COUNT = 7;
    private static final int POOL_SIZE = 5;


    public static void main(String[] args){

        run_thread_pool();
        System.out.println("**Finished running simple threads**\n");

        run_javaThread_pool();
        System.out.println("**Finished running Java Thread Pool**\n");

        run_with_interfaces();
        System.out.println("**Finished running Java Pool using the interface future**\n");
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

    private static void run_with_interfaces(){
        ConnecPool pool = ConnecPool.getPool(POOL_SIZE);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    Connection conn = pool.getConnection();
                    conn.use();
                    pool.releaseConnection(conn);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }, executor);

            futures.add(future);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        //wait for ending all threads
        try {
            allOf.get(100000, TimeUnit.MILLISECONDS); // Wait with a timeout
        } catch (InterruptedException | ExecutionException | TimeoutException e ) {
            //exceptions related to method .get
            throw new RuntimeException(e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
