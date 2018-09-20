import java.util.Observable;

/**
 * @author anthony-pc
 */
public class ObserverTank extends Observable {

    public ObserverTank() {
    }

    protected synchronized void setChanged() {
        super.setChanged();
    }

}