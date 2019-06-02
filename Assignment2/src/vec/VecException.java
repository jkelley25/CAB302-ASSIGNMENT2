package vec;

/**
 * Exception class for VecIO
 */
public class VecException extends Exception {
    public VecException(){
        super();
    }

    public VecException(String msg){
        super(msg);
    }

    public VecException(Throwable cause){
        super(cause);
    }

    public VecException(String msg, Throwable cause){
        super(msg, cause);
    }
}
