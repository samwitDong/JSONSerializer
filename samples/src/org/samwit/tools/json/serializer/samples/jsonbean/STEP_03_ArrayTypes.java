package org.samwit.tools.json.serializer.samples.jsonbean;

import java.util.Arrays;

import org.samwit.tools.json.serializer.JSONBean;

public class STEP_03_ArrayTypes extends JSONBean {
    
    private int[] a;
    
    private Long[] b;
    
    private String[] c;
    
    private int[][] d;
    
    public STEP_03_ArrayTypes() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_03_ArrayTypes)) {
            return false;
        }
        STEP_03_ArrayTypes other = (STEP_03_ArrayTypes) obj;
        if (!Arrays.equals(a, other.a)) {
            return false;
        }
        if (!Arrays.equals(b, other.b)) {
            return false;
        }
        if (!Arrays.equals(c, other.c)) {
            return false;
        }
        if (!Arrays.deepEquals(d, other.d)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        STEP_03_ArrayTypes src = new STEP_03_ArrayTypes();
        src.a = new int[] {1, 2, 3};
        src.b = new Long[] {11L, 12L, 13L};
        src.c = new String[] {"A", "b", "C"};
        src.d = new int[][] {{1, 2}, {3, 4, 5}, {6, 7, 8, 9}};
        System.out.println(src.toJSONString());
        
        STEP_03_ArrayTypes tgt = new STEP_03_ArrayTypes();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
