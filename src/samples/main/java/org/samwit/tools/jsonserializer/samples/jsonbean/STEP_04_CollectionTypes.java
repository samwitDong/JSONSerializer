package org.samwit.tools.jsonserializer.samples.jsonbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.Vector;

import org.samwit.tools.jsonserializer.JSONBean;

public class STEP_04_CollectionTypes extends JSONBean {
    
    /*
     * For those descendants of Collection<?>
     */

    private ArrayList<Integer> a;
    
    private HashSet<Long> b;
    
    private Stack<String> c;
    
    private Vector<Byte> d;
    
    /*
     * Abstract types and interfaces are not supported, for they cannot be instantiated:
     * private Collection<Integer> e; [WRONG: Collection is an interface]
     * private AbstractSet<String> f; [WRONG: AbstractSet is an abstract class]
     */
    
    public STEP_04_CollectionTypes() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof STEP_04_CollectionTypes)) {
            return false;
        }
        STEP_04_CollectionTypes other = (STEP_04_CollectionTypes) obj;
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
        return true;
    }
    
    public static void main(String[] args) {
        STEP_04_CollectionTypes src = new STEP_04_CollectionTypes();
        src.a = new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 2, 3, 4, 5}));
        src.b = new HashSet<Long>(Arrays.asList(new Long[] {11L, 12L, 13L}));
        src.c = new Stack<String>();
        src.c.addAll(Arrays.asList(new String[] {"Win", "Draw", "Lose"}));
        src.d = new Vector<Byte>(Arrays.asList(new Byte[] {1, 2, 3, 4, 5, 6}));
        System.out.println(src.toJSONString());
        
        STEP_04_CollectionTypes tgt = new STEP_04_CollectionTypes();
        tgt.loadJSONString(src.toJSONString());
        System.out.println("src.equals(tgt) = " + src.equals(tgt));
    }
}
