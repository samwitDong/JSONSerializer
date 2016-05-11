package org.samwit.tools.jsonserializer.samples.jsonbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.samwit.tools.jsonserializer.JSONBean;
import org.samwit.tools.jsonserializer.samples.demodata.TDB;

public class STEP_10_Extremities extends JSONBean {

    /*
     * If you find it necessary, do not hesitate.
     * Mostly, it works well.
     * And I believe there are thousands of many others you can come up with.
     * Here we push it to the limit...
     */
    
    private ArrayList<String[]> a;
    
    private ArrayList<Integer>[] b;
    
    private ArrayList<ArrayList<TDB<Integer, String>>> c;
    
    private ArrayList<HashMap<Integer, TDB<String, Integer>>> d;
    
    private HashMap<Integer, ArrayList<String>[]> e;
    
    public STEP_10_Extremities() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_10_Extremities)) {
            return false;
        }
        STEP_10_Extremities other = (STEP_10_Extremities) obj;
        if (a == null) {
            if (other.a != null) {
                return false;
            }
        } else if (!compareA(a, other.a)) {
            return false;
        }
        if (!Arrays.equals(b, other.b)) {
            return false;
        }
        if (c == null) {
            if (other.c != null) {
                return false;
            }
        } else if (!c.equals(other.c)) {
            return false;
        }
        if (d == null) {
            if (other.d != null) {
                return false;
            }
        } else if (!d.equals(other.d)) {
            return false;
        }
        if (e == null) {
            if (other.e != null) {
                return false;
            }
        } else if (!compareE(e, other.e)) {
            return false;
        }
        return true;
    }
    
    private static boolean compareA(ArrayList<String[]> a1, ArrayList<String[]> a2) {
        if (a1 == null || a2 == null || a1.size() != a2.size()) {
            return false;
        }
        Iterator<String[]> e1 = a1.iterator();
        Iterator<String[]> e2 = a2.iterator();
        while (e1.hasNext() && e2.hasNext()) {
            String[] s1 = e1.next();
            String[] s2 = e2.next();
            if (!Arrays.equals(s1, s2)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean compareE(HashMap<Integer, ArrayList<String>[]> e1, HashMap<Integer, ArrayList<String>[]> e2) {
        if (e1 == null || e2 == null || e1.size() != e2.size()) {
            return false;
        }
        for (Entry<Integer, ArrayList<String>[]> entry : e1.entrySet()) {
            ArrayList<String>[] v1 = entry.getValue();
            ArrayList<String>[] v2 = e2.get(entry.getKey());
            if (!Arrays.equals(v1, v2)) {
                return false;
            }
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        STEP_10_Extremities src = new STEP_10_Extremities();
        src.a = new ArrayList<String[]>(Arrays.asList(new String[][] {{"A"}, {"B", "C"}, {"D", "E", "F"}}));
        src.b = new ArrayList[] {new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 2})), new ArrayList<Integer>(Arrays.asList(new Integer[] {3, 4, 5}))};
        src.c = new ArrayList<ArrayList<TDB<Integer, String>>>();
        ArrayList<TDB<Integer, String>> c1 = new ArrayList<TDB<Integer, String>>((Collection<? extends TDB<Integer, String>>) Arrays.asList(new TDB[] {new TDB<Integer, String>(1, "A"), new TDB<Integer, String>(2, "B")}));
        ArrayList<TDB<Integer, String>> c2 = new ArrayList<TDB<Integer, String>>((Collection<? extends TDB<Integer, String>>) Arrays.asList(new TDB[] {new TDB<Integer, String>(3, "C"), new TDB<Integer, String>(4, "D"), new TDB<Integer, String>(5, "E")}));
        src.c.add(c1);
        src.c.add(c2);
        src.d = new ArrayList<HashMap<Integer, TDB<String, Integer>>>();
        HashMap<Integer, TDB<String, Integer>> d1 = new HashMap<Integer, TDB<String, Integer>>();
        d1.put(1, new TDB<String, Integer>("A", 1));
        d1.put(2, new TDB<String, Integer>("B", 2));
        HashMap<Integer, TDB<String, Integer>> d2 = new HashMap<Integer, TDB<String, Integer>>();
        d2.put(3, new TDB<String, Integer>("C", 3));
        d2.put(4, new TDB<String, Integer>("D", 4));
        d2.put(5, new TDB<String, Integer>("E", 5));
        src.d.add(d1);
        src.d.add(d2);
        src.e = new HashMap<Integer, ArrayList<String>[]>();
        ArrayList<String>[] e1 = new ArrayList[] {new ArrayList<String>(Arrays.asList(new String[] {"A"})), new ArrayList<String>(Arrays.asList(new String[] {"B", "C"}))};
        ArrayList<String>[] e2 = new ArrayList[] {new ArrayList<String>(Arrays.asList(new String[] {"D", "E", "F"})), new ArrayList<String>(Arrays.asList(new String[] {"G", "H", "I", "J"})), new ArrayList<String>(Arrays.asList(new String[] {"K", "L", "M", "N", "O"}))};
        src.e.put(1, e1);
        src.e.put(2, e2);
        System.out.println(src.toJSONString());
        
        STEP_10_Extremities tgt = new STEP_10_Extremities();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
