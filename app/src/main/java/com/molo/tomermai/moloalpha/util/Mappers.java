package com.molo.tomermai.moloalpha.util;


import com.molo.tomermai.moloalpha.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mappers {

    private static Map<Integer, Integer> classPopulationMap;
    static {
        Map<Integer, Integer> aMap = new HashMap<>();
        aMap.put(0, R.drawable.empty_sign);
        aMap.put(1, R.drawable.a_few_sign);
        aMap.put(2, R.drawable.a_few_sign);
        classPopulationMap = Collections.unmodifiableMap(aMap);
    }

    private static Map<Integer, Integer> classNoiseMap;
    static {
        Map<Integer, Integer> aMap = new HashMap<>();
        aMap.put(0, R.drawable.silent);
        aMap.put(1, R.drawable.shape_1_copy);
        aMap.put(2, R.drawable.shape_1_copy);
        classNoiseMap = Collections.unmodifiableMap(aMap);
    }

    public static Integer getClassPopulationOrEmpty(int key) {
        if (key < classPopulationMap.size())
            return classPopulationMap.get(key);
        else {
            return classPopulationMap.get(0);
        }
    }

    public static Integer getClassNoiseOrEmpty(int key) {
        if (key < classNoiseMap.size())
            return classNoiseMap.get(key);
        else {
            return classNoiseMap.get(0);
        }
    }
}
