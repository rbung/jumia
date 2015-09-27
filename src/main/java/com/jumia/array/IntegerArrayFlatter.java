package com.jumia.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerArrayFlatter {

    public Integer[] flat(Object toPrint) {
        List<Integer> result = new ArrayList<>();
        if (toPrint instanceof Object[]) {
            Object[] array = (Object[]) toPrint;
            for (Object currentObject : array) {
                if (currentObject instanceof Integer) {
                    Integer currentInteger = (Integer) currentObject;
                    result.add(currentInteger);
                } else if (currentObject instanceof Object[]) {
                    result.addAll(Arrays.asList(flat(currentObject)));
                }
            }
        }
        return result.toArray(new Integer[result.size()]);
    }
}
