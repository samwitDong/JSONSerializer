package org.samwit.tools.json.serializer.samples.jsonbean;

import org.samwit.tools.json.serializer.JSONBean;
import org.samwit.tools.json.serializer.samples.demodata.TDA;
import org.samwit.tools.json.serializer.samples.demodata.TDB;

public class STEP_07_GenericAndNongenericJSONBeanTypes extends JSONBean {
    
    /*
     * All those objects (other than String and the package types)
     * must be of JSONBean-extended types and be able to be instantiated via a default parameterless constructor
     * if you want to export them in JSON string and load them back later on
     */
    
    private TDA a;

    private TDB<Integer, String> b;

    public STEP_07_GenericAndNongenericJSONBeanTypes() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_07_GenericAndNongenericJSONBeanTypes)) {
            return false;
        }
        STEP_07_GenericAndNongenericJSONBeanTypes other = (STEP_07_GenericAndNongenericJSONBeanTypes) obj;
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
        STEP_07_GenericAndNongenericJSONBeanTypes src = new STEP_07_GenericAndNongenericJSONBeanTypes();
        src.a = new TDA(29, "Failure");
        src.b = new TDB<>(35, "Victory");
        System.out.println(src.toJSONString());
        
        STEP_07_GenericAndNongenericJSONBeanTypes tgt = new STEP_07_GenericAndNongenericJSONBeanTypes();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
