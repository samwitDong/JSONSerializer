package org.samwit.tools.json.serializer.samples.generictypebuilder;

import java.util.ArrayList;
import java.util.HashMap;

import org.samwit.tools.json.serializer.GenericTypeBuilder;

/**
 * To create generic-parameterized types
 * @author samwitDong
 *
 */
public class STEP_02_CreateGenericParameterizedType {

    public static void main(String[] args) {
        // Create the type of "ArrayList<String>"
        GenericTypeBuilder b1 = new GenericTypeBuilder().setParameterizedType(ArrayList.class, String.class);
        System.out.println(b1);
        
        // Create the type of "HashMap<Integer, String>"
        GenericTypeBuilder b2 = new GenericTypeBuilder().setParameterizedType(HashMap.class, Integer.class, String.class);
        System.out.println(b2);
        
        // Another way of creating the type of "ArrayList<String>"
        GenericTypeBuilder b3 = new GenericTypeBuilder(String.class).deriveParameterizedType(ArrayList.class);
        System.out.println(b3);
        
        // Create the type of "HashMap<String, ArrayList<Integer>>"
        GenericTypeBuilder b4 = new GenericTypeBuilder().setParameterizedType(HashMap.class, String.class, b3.toGenericType());
        System.out.println(b4);
    }
}
