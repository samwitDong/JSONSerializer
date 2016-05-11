package org.samwit.tools.jsonserializer.samples.jsonbean;

import org.samwit.tools.jsonserializer.annotations.ExportName;
import org.samwit.tools.jsonserializer.samples.demodata.TDC;

public class STEP_12_ChangeExportName extends TDC {
    
    /*
     * ChangeExportName is a subclass of TDC
     */
    
    /*
     * The superclass TDC already has a field named "type" which would bring a conflict between them.
     * If you leave it alone, there would be only one "type" exported and obviously it's wrong.
     * Here we must adopt the export name to make them distinct from each other.
     */
    @ExportName(value = "sub-type")
    private String type;
    
    // Here we want to use the capital words instead.
    @ExportName(value = "SNO.")
    private int sno;

    public STEP_12_ChangeExportName() {
    }

    public String getSubType() {
        return type;
    }

    public void setSubType(String type) {
        this.type = type;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof STEP_12_ChangeExportName)) {
            return false;
        }
        STEP_12_ChangeExportName other = (STEP_12_ChangeExportName) obj;
        if (sno != other.sno) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        STEP_12_ChangeExportName src = new STEP_12_ChangeExportName();
        src.setType("TDC.type");
        src.setId(25);
        src.setSubType("this.type");
        src.setSno(966596);
        System.out.println(src.toJSONString());
        
        STEP_12_ChangeExportName tgt = new STEP_12_ChangeExportName();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
