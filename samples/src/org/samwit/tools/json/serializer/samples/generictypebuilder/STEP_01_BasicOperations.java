package org.samwit.tools.json.serializer.samples.generictypebuilder;

import org.samwit.tools.json.serializer.GenericTypeBuilder;

/**
 * To show the basic usage
 * @author samwitDong
 *
 */
public class STEP_01_BasicOperations {

    public static void main(String[] args) {
        // Create a default builder
        GenericTypeBuilder builder1 = new GenericTypeBuilder();
        System.out.println(String.format("Is builder1 an Object.class? %s", Object.class.equals(builder1.toGenericType())));
        
        // Create a builder with a specified type
        GenericTypeBuilder builder2 = new GenericTypeBuilder(String.class);
        System.out.println(String.format("Is builder2 a String.class? %s", String.class.equals(builder2.toGenericType())));
        
        // Change the base type
        builder2.setBaseType(Integer.class);
        System.out.println(String.format("Is builder2 an Integer.class? %s", Integer.class.equals(builder2.toGenericType())));
    }
}
