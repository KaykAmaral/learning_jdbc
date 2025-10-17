// We created this class cause SQLException u need fix and our class not

package src.db;

public class DbException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DbException(String msg){ super(msg); }

}