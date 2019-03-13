package com.prilaga.kotlintest.education;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Oleg Tarashkevich on 13/01/2019.
 */
public class CollectionsEducation {

    public void test(){

        Hashtable<String, String> hashtable = new Hashtable<>();

        Enumeration<String> enumeration = hashtable.elements();
        while (enumeration.hasMoreElements()){
            enumeration.nextElement();
        }

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("");

        Set<String> set = Collections.synchronizedSet(new HashSet<String>());

        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override public int compare(String o1, String o2) {
                return 0;
            }
        });

        TreeMap<String, Integer> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override public int compare(String o1, String o2) {
                return 0;
            }
        });
    }
}
