package Integration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModelConsumption {
    private Map<String,Double> map = new ConcurrentHashMap<>();

    public void put(String name, double watts) {
        map.put(name,watts);
    }

    public Map<String,Double> getCurrentConsumption() {
        return Map.copyOf(map);
    }
}

