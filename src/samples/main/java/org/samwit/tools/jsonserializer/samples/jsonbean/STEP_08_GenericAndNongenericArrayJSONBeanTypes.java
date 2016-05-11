package org.samwit.tools.jsonserializer.samples.jsonbean;

import java.util.Arrays;

import org.samwit.tools.jsonserializer.JSONBean;
import org.samwit.tools.jsonserializer.samples.demodata.TDA;
import org.samwit.tools.jsonserializer.samples.demodata.TDB;

public class STEP_08_GenericAndNongenericArrayJSONBeanTypes extends JSONBean {

    /*
     * All those component objects (other than String and the package types)
     * must be of JSONBean-extended types and be able to be instantiated via a default parameterless constructor
     * if you want to export them in JSON string and load them back later on
     */
    
    private TDA[] a;
    
    private TDB<Long, String>[] b;

    public STEP_08_GenericAndNongenericArrayJSONBeanTypes() {
    }

    public STEP_08_GenericAndNongenericArrayJSONBeanTypes(TDA[] a, TDB<Long, String>[] b) {
        this.a = a;
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
        if (!(obj instanceof STEP_08_GenericAndNongenericArrayJSONBeanTypes)) {
            return false;
        }
        STEP_08_GenericAndNongenericArrayJSONBeanTypes other = (STEP_08_GenericAndNongenericArrayJSONBeanTypes) obj;
        if (!Arrays.equals(a, other.a)) {
            return false;
        }
        if (!Arrays.equals(b, other.b)) {
            return false;
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        STEP_08_GenericAndNongenericArrayJSONBeanTypes src = new STEP_08_GenericAndNongenericArrayJSONBeanTypes();
        src.a = new TDA[] {new TDA(0, "Open"), new TDA(1, "Closed"), new TDA(2, "Ajar")};
        src.b = new TDB[] {new TDB<Long, String>(0L, "On"), new TDB<Long, String>(1L, "Off")};
        System.out.println(src.toJSONString());
        
        STEP_08_GenericAndNongenericArrayJSONBeanTypes tgt = new STEP_08_GenericAndNongenericArrayJSONBeanTypes();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
