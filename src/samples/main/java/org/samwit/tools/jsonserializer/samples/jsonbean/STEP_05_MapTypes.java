package org.samwit.tools.jsonserializer.samples.jsonbean;

import java.util.HashMap;
import java.util.TreeMap;

import org.samwit.tools.jsonserializer.JSONBean;

public class STEP_05_MapTypes extends JSONBean {
    
    /*
     * For those descendants of Map<?, ?>
     */
    
    private HashMap<Integer, String> a;
    
    private TreeMap<Double, String> b;
    
    /* ######################### CATIONS 1 #########################
     * Abstract classes and interfaces are not supported, for they cannot be instantiated:
     * private AbstractMap<?, ?> c; [WRONG: Abstract class]
     * private SortedMap<?, ?> d;   [WRONG: Interface type]
     */
    
    /* ######################### CATIONS 2 #########################
     * Map is no recommendations, for under most circumstances it can be substituted by JSONBeans:
     * _________________________________________________________________
     * Scenario 1:
     * HashMap<String, Integer> data = new HashMap<String, Integer>();
     * data.put("id", 1);
     * data.put("sno", 9002465);
     * _________________________________________________________________
     * Scenario 2:
     * JSONBean data = new JSONBean() {
     *     private int id = 1;
     *     private int sno = 9002465; 
     * };
     * _________________________________________________________________
     */
    
    /* ######################### CATIONS 3 #########################
     * In order to make it easy and sensible, the key type of the Map should be one of the below types:
     * 01. Byte
     * 02. Character
     * 03. Short
     * 04. Integer
     * 05. Long
     * 06. Float
     * 07. Double
     * 08. String
     * 09. Enum<?>
     * 
     * Otherwise, the map will not be exported.
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_05_MapTypes)) {
            return false;
        }
        STEP_05_MapTypes other = (STEP_05_MapTypes) obj;
        if (a == null) {
            if (other.a != null) {
                return false;
            }
        } else if (!a.equals(other.a)) {
            return false;
        }
        if (b == null) {
            if (other.b != null) {
                return false;
            }
        } else if (!b.equals(other.b)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        STEP_05_MapTypes src = new STEP_05_MapTypes();
        src.a = new HashMap<Integer, String>();
        src.a.put(1, "Open");
        src.a.put(2, "Closed");
        src.b = new TreeMap<>();
        src.b.put(5.1, "Level 1");
        src.b.put(6.3, "Level 2");
        src.b.put(8.5, "Level 3");
        System.out.println(src.toJSONString());
        
        STEP_05_MapTypes tgt = new STEP_05_MapTypes();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
