package Model;

/*
* this class is sort of a flag class
* It is Like a Enum Class , that carries the condition
* of the network state
* */
public class NetworkState {
    public static final NetworkState LOADED ;
    public static final NetworkState LOADING ;
    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED
    }
    private Status status ;
    private String msg ;

    static {
       LOADED = new NetworkState(Status.SUCCESS , Status.SUCCESS.name());
       LOADING = new NetworkState(Status.RUNNING , Status.RUNNING.name());
    }

    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


}
