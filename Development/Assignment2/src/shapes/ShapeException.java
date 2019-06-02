package shapes;

public class ShapeException extends Exception{

    public ShapeException(){
        super();
    }

    public ShapeException(String msg){
        super(msg);
    }

    public ShapeException(Throwable cause){
        super(cause);
    }

    public ShapeException(String msg, Throwable cause){
        super(msg, cause);
    }
}
