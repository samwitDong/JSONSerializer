package org.samwit.tools.jsonserializer.samples.jsonbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.samwit.tools.jsonserializer.JSONBean;
import org.samwit.tools.jsonserializer.samples.demodata.Colors;

public class STEP_06_EnumTypes extends JSONBean {
    
    private Colors a;
    
    private Colors[] b;
    
    private ArrayList<Colors> c;
    
    private HashMap<Colors, Integer> d;

    public STEP_06_EnumTypes() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_06_EnumTypes)) {
            return false;
        }
        STEP_06_EnumTypes other = (STEP_06_EnumTypes) obj;
        if (a != other.a) {
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
        return true;
    }

    public static void main(String[] args) {
        STEP_06_EnumTypes src = new STEP_06_EnumTypes();
        src.a = Colors.EMERALD;
        src.b = new Colors[] {Colors.RED, Colors.GREEN, Colors.BLUE};
        src.c = new ArrayList<Colors>(Arrays.asList(new Colors[] {Colors.BEIGE, Colors.SAPPHIRE, Colors.TAWNY, Colors.VIOLET}));
        src.d = new HashMap<Colors, Integer>();
        src.d.put(Colors.RED, 15);
        src.d.put(Colors.GREEN, 27);
        src.d.put(Colors.BLUE, 72);
        src.d.put(Colors.BLACK, 37);
        src.d.put(Colors.WHITE, 99);
        System.out.println(src.toJSONString());
        
        STEP_06_EnumTypes tgt = new STEP_06_EnumTypes();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
