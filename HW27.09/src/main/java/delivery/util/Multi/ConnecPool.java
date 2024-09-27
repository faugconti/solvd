package delivery.util.Multi;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnecPool {

    private static ConnecPool connPool;
    private int size;
    private BlockingDeque<Connection> connections; //thread safe queue

    //singleton
    private ConnecPool(int poolSize){
        this.size = poolSize;
        this.connections = null;
    }
    public static synchronized ConnecPool getPool(int poolSize){
        if (connPool == null) {
            connPool = new ConnecPool(poolSize);
        }
        return connPool;
    }

    // lazy initialized, create the queue of connections when used in retrieving a Connection
    private void init() throws IllegalStateException{
        if (this.connections == null) {
            this.connections = new LinkedBlockingDeque<>(size);
            for(int i=0;i<size;i++){
                connections.add(new Connection(i)); //add() throws exception when full
            }
        }
    }

    public synchronized Connection getConnection() throws InterruptedException{
        if(this.connections==null){
            this.init();
        }
        return this.connections.take(); // wait for element if empty
    }

    public void releaseConnection(Connection conn) throws InterruptedException {
        this.connections.put(conn); // wait if no space
        System.out.println("[" + Thread.currentThread().getName() + " released connection " + conn + "]");

    }


}
