package concurrent.component;

import concurrent.annotation.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/5/24
 */
@ThreadSafe
public class PublishVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, SafePoint> getLocations() {
        return locations;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id)) {
            throw new IllegalArgumentException("invalid component name : " + id);
        }
        locations.get(id).set(x, y);
    }
}
