package org.samwit.tools.jsonserializer.samples.jsonbean;

import org.samwit.tools.jsonserializer.JSONBean;

public class STEP_02_PackageTypesAndString extends JSONBean {
    
    /*
     * The package types as well as String are supported.
     */
    
    private Byte a;
    
    private Character b;
    
    private Short c;
    
    private Integer d;
    
    private Long e;
    
    private Float f;
    
    private Double g;
    
    private String plus;
    
    public STEP_02_PackageTypesAndString() {
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_02_PackageTypesAndString)) {
            return false;
        }
        STEP_02_PackageTypesAndString other = (STEP_02_PackageTypesAndString) obj;
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
        } else if (!e.equals(other.e)) {
            return false;
        }
        if (f == null) {
            if (other.f != null) {
                return false;
            }
        } else if (!f.equals(other.f)) {
            return false;
        }
        if (g == null) {
            if (other.g != null) {
                return false;
            }
        } else if (!g.equals(other.g)) {
            return false;
        }
        if (plus == null) {
            if (other.plus != null) {
                return false;
            }
        } else if (!plus.equals(other.plus)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        STEP_02_PackageTypesAndString src = new STEP_02_PackageTypesAndString();
        src.a = 1;
        src.b = 'b';
        src.c = 2;
        src.d = 3;
        src.e = 4L;
        src.f = 5.0F;
        src.g = 6.0;
        src.plus = "Plus";
        System.out.println(src.toJSONString());
        
        STEP_02_PackageTypesAndString tgt = new STEP_02_PackageTypesAndString();
        tgt.loadJSONObject(src.toJSONObject());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }

}
