package concurrent.activityRisk;

import concurrent.annotation.ThreadSafe;
import concurrent.component.Point;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/6/2
 */
@ThreadSafe
public class Taxi {
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        boolean reachedDestination;
        synchronized(this) {
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        // 开放调用
        if (reachedDestination) {
            dispatcher.notifyAvailable(this);
        }
    }
}
