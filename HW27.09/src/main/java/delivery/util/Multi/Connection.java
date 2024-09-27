package delivery.util.Multi;

public class Connection {

    private final int id;

    public Connection(int id) {
        this.id = id;
    }

    public void use() throws InterruptedException {
        System.out.println("Connection #"+this.id+" being used.");
        Thread.sleep(5000);
    }

    @Override
    public String toString(){
        return "Connection #"+this.id;
    }


}
