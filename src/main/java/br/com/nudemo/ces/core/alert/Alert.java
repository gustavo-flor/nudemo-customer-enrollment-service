package br.com.nudemo.ces.core.alert;

import java.util.HashMap;
import java.util.Map;

public interface Alert {

    default String name() {
        return getClass().getSimpleName();
    }

    default Map<String, Object> properties() {
        return new HashMap<>();
    }

}
