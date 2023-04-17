package br.com.nudemo.ces.core.message;

import java.util.HashMap;
import java.util.Map;

public interface Message {

    String target();

    Object value();

    default String key() {
        return null;
    }

    default Map<String, Object> headers() {
        return new HashMap<>();
    }

}
