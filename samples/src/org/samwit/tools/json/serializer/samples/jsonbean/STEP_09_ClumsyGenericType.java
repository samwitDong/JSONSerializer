package org.samwit.tools.json.serializer.samples.jsonbean;
import java.util.ArrayList;
import java.util.Arrays;

import org.samwit.tools.json.serializer.GenericTypeBuilder;
import org.samwit.tools.json.serializer.JSONBean;
import org.samwit.tools.json.serializer.samples.demodata.TDB;


public class STEP_09_ClumsyGenericType<X, Y> extends JSONBean {
    /*
     * This time it's a bit different that the sample class itself is generic with the type parameters not specified until its instantiation.
     * In order to make it work, we have to take some ugly and clumsy actions even though I know you might hate it so very much. :(
     * Just as the name reads, clumsy it is.
     */
    
    private X x;
    
    private Y[] y;
    
    private TDB<X, Y> z;

    public STEP_09_ClumsyGenericType() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_09_ClumsyGenericType)) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        STEP_09_ClumsyGenericType other = (STEP_09_ClumsyGenericType) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (!Arrays.equals(y, other.y)) {
            return false;
        }
        if (z == null) {
            if (other.z != null) {
                return false;
            }
        } else if (!z.equals(other.z)) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        STEP_09_ClumsyGenericType<ArrayList<Integer>, String> src = new STEP_09_ClumsyGenericType<ArrayList<Integer>, String>();
        src.x = new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 3, 5, 7, 9}));
        src.y = new String[] {"Google", "Facebook", "Twitter", "wikipedia"};
        src.z = new TDB<ArrayList<Integer>, String>(new ArrayList<Integer>(Arrays.asList(new Integer[] {0, 2, 4, 6, 8})), "Long time ago...");
        System.out.println(src.toJSONString());
        
        // Let's do something to tell it what the generic types are.
        // Introduce the GenericTypeBuilder to generate the generic types we actually use.
        GenericTypeBuilder builder = new GenericTypeBuilder().setParameterizedType(TDB.class, 
                new GenericTypeBuilder(Integer.class).deriveParameterizedType(ArrayList.class).toGenericType(),
                String.class);
        // Now the builder represents TDB<ArrayList<Integer>, String>
        // More guides to the GenericTypeBuilder, please refer to samples under "org.samwit.tools.json.serializer.samples.generictypebuilder".
        
        STEP_09_ClumsyGenericType<ArrayList<Integer>, String> tgt = new STEP_09_ClumsyGenericType<ArrayList<Integer>, String>();
        tgt.loadJSONString(src.toJSONString(), builder.toGenericType());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
