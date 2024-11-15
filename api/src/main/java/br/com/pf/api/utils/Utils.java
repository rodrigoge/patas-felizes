package br.com.pf.api.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static Object compareValues(Object oldValue, Object newValue) {
        return oldValue.equals(newValue) ? oldValue : newValue;
    }
}
