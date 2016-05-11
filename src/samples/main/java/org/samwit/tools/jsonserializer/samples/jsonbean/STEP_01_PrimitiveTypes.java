package org.samwit.tools.jsonserializer.samples.jsonbean;

import org.samwit.tools.jsonserializer.JSONBean;

public class STEP_01_PrimitiveTypes extends JSONBean {
    
    /*
     * All the primitive types are supported
     */
    
    private byte a;
    
    private char b;
    
    private short c;
    
    private int d;
    
    private long e;
    
    private float f;
    
    private double g;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        STEP_01_PrimitiveTypes other = (STEP_01_PrimitiveTypes) obj;
        if (a != other.a)
            return false;
        if (b != other.b)
            return false;
        if (c != other.c)
            return false;
        if (d != other.d)
            return false;
        if (e != other.e)
            return false;
        if (Float.floatToIntBits(f) != Float.floatToIntBits(other.f))
            return false;
        if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
            return false;
        return true;
    }

    public STEP_01_PrimitiveTypes() {
    }

    public static void main(String[] args) {
        STEP_01_PrimitiveTypes src = new STEP_01_PrimitiveTypes();
        src.a = 1;
        src.b = 'b';
        src.c = 2;
        src.d = 3;
        src.e = 4L;
        src.f = 5.0F;
        src.g = 6.0;
        System.out.println("JSON String: " + src.toJSONString());
        
        STEP_01_PrimitiveTypes tgt = new STEP_01_PrimitiveTypes();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }

}
