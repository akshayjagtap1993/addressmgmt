package com.person.addressmgmt.assignments;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Assignment2ArrayReform {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10,77,92,1,30,81,1,4,1,20,17,1,16,33);
        int elementToMove = 1;
        int valueForMultipleOf = 10;

        List<Integer> resultList;
        Map<Integer, Integer> resultMapForMultiple = new LinkedHashMap<>();

        Map<Boolean, List<Integer>> dividedMap =
                list.stream().collect(Collectors.partitioningBy(i -> i != elementToMove));
        resultList = dividedMap.get(true);
        resultList.addAll(dividedMap.get(false));

        for(int k=0; k<resultList.size(); k++) {
            int val = resultList.get(k);
            if(val != 0 && val % valueForMultipleOf == 0)
                resultMapForMultiple.putIfAbsent(k, val);
        }

        System.out.println("resultList -> " + resultList);
        System.out.println("resultMapForMultiple -> " + resultMapForMultiple);
    }
}
