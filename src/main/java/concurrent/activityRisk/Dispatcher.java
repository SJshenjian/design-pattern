package concurrent.activityRisk;

import concurrent.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/6/2
 */
@ThreadSafe
public class Dispatcher {
    private final Set<Taxi> taxis = Collections.synchronizedSet(new HashSet<>());
    private final Set<Taxi> availableTaxis = Collections.synchronizedSet(new HashSet<>());

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public Image getImage() {
        Set<Taxi> copy;
        synchronized (this) {
            copy = new HashSet<Taxi>(taxis);
        }
        Image image = new Image();
        for (Taxi t : copy) {
            image.drawMarker(t.getLocation());
        }
        return image;
    }
}
