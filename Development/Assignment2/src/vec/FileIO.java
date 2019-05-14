package vec;

/**
 * Abstract for handling File input and output
 */
public abstract class FileIO {

    String fileName;

    public FileIO(String fileName){
        this.fileName = fileName;
    }

    public abstract void ReadFile();

}
