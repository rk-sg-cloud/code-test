package com.test.axway.hashmap;

public class Main {

    public static void main(String[] args) {
        CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();
        addDataToHashMap(customHashMap, 10);
        customHashMap.delete("Sumee2");
        System.out.println("customHashMap.get(Sumee4) = " + customHashMap.get("Sumee4"));
        System.out.println("customHashMap.getAt(8) = " + customHashMap.getAt(8));
    }

    private static void addDataToHashMap(CustomHashMap<String, Integer> hashMap, int keysNumber) {
        for (int i = 0; i < keysNumber; i++) {
            hashMap.put("Sumee"+i, i);
        }
    }
}