package org.samwit.tools.jsonserializer.samples.generictypebuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.samwit.tools.jsonserializer.GenericTypeBuilder;

/**
 * To create generic array type
 * @author BOQD
 *
 */
public class STEP_03_CreateGenericArrayType {

    public static void main(String[] args) {
        // Create the 3-dimensioned array type of int
        GenericTypeBuilder b1 = new GenericTypeBuilder().setGenericArrayType(int.class, 3);
        System.out.println(b1);
        
        Type asType = new GenericTypeBuilder(String.class).deriveParameterizedType(ArrayList.class).toGenericType();
        
        // Create the 2-dimensioned array type of ArrayList<String>
        GenericTypeBuilder b2 = new GenericTypeBuilder().setGenericArrayType(asType, 2);
        System.out.println(b2);
        
        // Create the 2-dimensioned array type of ArrayList<String>
        GenericTypeBuilder b3 = new GenericTypeBuilder(asType).deriveGenericArrayType(2);
        System.out.println(b3);
    }
}
