package delivery.util.Multi;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnecPool {

    private static volatile ConnecPool connPool;
    private int size;
    private BlockingDeque<Connection> connections; //thread safe queue

    //singleton
    private ConnecPool(int poolSize){
        this.size = poolSize;
        this.connections = null;
    }
    public static ConnecPool getPool(int poolSize){
        if (connPool == null) {
            synchronized (ConnecPool.class) {
                if (connPool == null) {
                    connPool = new ConnecPool(poolSize);
                }
            }
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

    public Connection getConnection() throws InterruptedException{
        if(this.connections==null){
            this.init();
        }
        //return this.connections.take();
        Connection conn = connections.take();// wait for element if empty
        System.out.println("[" + Thread.currentThread().getName() + " acquired " + conn + "]");
        return conn;
    }

    public void releaseConnection(Connection conn) throws InterruptedException {
        this.connections.put(conn); // wait if no space
        System.out.println("[" + Thread.currentThread().getName() + " released connection " + conn + "]");

    }


}
