package jb;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Description
 * @Date 2022/5/17 17:15
 * @Created by jalivv
 */
public class Main {

    public static void main(String[] args) {

    }

    public static Integer getMaxVal(List<Integer> valList) {
        Integer max = Integer.MIN_VALUE;
        for (Integer i : valList) {
            if (i>max) {
                max = i;
            }
        }
        return max;
    }
}

