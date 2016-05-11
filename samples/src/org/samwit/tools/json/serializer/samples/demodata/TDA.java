package org.samwit.tools.json.serializer.samples.demodata;

import org.samwit.tools.json.serializer.JSONBean;

public class TDA extends JSONBean {
    
    private int a;
    
    private String b;
    
    public TDA() {
    }

    public TDA(int a, String b) {
        super();
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TDA)) {
            return false;
        }
        TDA other = (TDA) obj;
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
}
