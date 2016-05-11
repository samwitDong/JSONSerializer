package org.samwit.tools.jsonserializer.samples.jsonbean;

import org.samwit.tools.jsonserializer.JSONBean;
import org.samwit.tools.jsonserializer.annotations.Unexportable;

public class STEP_11_UnwantedData extends JSONBean {
    
    private int a;
    
    // If you don't want "b" to be exported
    @Unexportable
    private String b;

    public STEP_11_UnwantedData() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_11_UnwantedData)) {
            return false;
        }
        STEP_11_UnwantedData other = (STEP_11_UnwantedData) obj;
        if (a != other.a) {
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
        STEP_11_UnwantedData src = new STEP_11_UnwantedData();
        src.a = 20;
        src.b = "Unwanted";
        System.out.println(src.toJSONString());
        
        STEP_11_UnwantedData tgt = new STEP_11_UnwantedData();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("tgt.a = " + tgt.a);
        System.out.println("tgt.b = " + tgt.b);
    }
}
