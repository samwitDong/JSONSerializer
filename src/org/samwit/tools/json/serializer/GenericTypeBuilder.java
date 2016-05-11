package org.samwit.tools.json.serializer;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * A helper for building quickly the generic types of a particular generic definition
 * @author samwitDong
 *
 */
public class GenericTypeBuilder {
    
    private Type type;

    /**
     * Default parameterless constructor with Object.class as its base type
     */
    public GenericTypeBuilder() {
        type = Object.class;
    }
    
    /**
     * Constructor with specifying a base type
     * @param baseType
     */
    public GenericTypeBuilder(Type baseType) {
        type = (baseType == null) ? Object.class : baseType;
    }

    /**
     * Return the generic type object
     * @return
     */
    public Type toGenericType() {
        return type;
    }

    /**
     * Change the base type
     * @param baseType
     */
    public void setBaseType(Type baseType) {
        type = baseType;
    }
    
    /**
     * Changed to a generic-parameterized type with the rawType and the typeParameters
     * @param rawType, any type that has at least one type parameter such as Collection<T>, Comparator<T> and etc.
     * @param typeParameters 
     * @return this object
     */
    public GenericTypeBuilder setParameterizedType(Class<?> rawType, Type... typeParameters) {
        if (rawType == null) {
            throw new IllegalArgumentException("Null argument: rawType");
        }
        if (typeParameters == null || typeParameters.length == 0) {
            throw new IllegalArgumentException("Null argument: typeParameters");
        }
        
        ParameterizedTypeImpl pType = new ParameterizedTypeImpl();
        pType.setRawType(rawType);
        int length = typeParameters.length;
        Type[] actualTypes = new Type[length];
        for (int i = 0; i < length; i++) {
            actualTypes[i] = typeParameters[i];
        }
        pType.setActualTypeArguments(actualTypes);
        
        this.type = pType;
        return this;
    }
    
    /**
     * Changed to a generic array type
     * @param genericComponentType, the component type which could be a generic type or not
     * @param dimension, the array dimension, should be no less than 1
     * @return this object
     */
    public GenericTypeBuilder setGenericArrayType(Type genericComponentType, int dimension) {
        if (genericComponentType == null) {
            throw new IllegalArgumentException("Null argument: " + genericComponentType);
        }
        GenericTypeBuilder builder = new GenericTypeBuilder(genericComponentType).deriveGenericArrayType(dimension);
        this.type = builder.type;
        
        return this;
    }
    
    /**
     * To create a new GenericTypeBuilder with a specified rawType and this object as the only type parameter
     * @param rawType
     * @return a new GenericTypeBuilder
     */
    public GenericTypeBuilder deriveParameterizedType(Class<?> rawType) {
        if (rawType == null) {
            throw new IllegalArgumentException("Null argument: rawType");
        }
        
        ParameterizedTypeImpl pType = new ParameterizedTypeImpl();
        pType.setActualTypeArguments(new Type[] {this.type});
        pType.setRawType(rawType);
        
        GenericTypeBuilder ret = new GenericTypeBuilder();
        ret.type = pType;
        
        return ret;
    }
    
    /**
     * To create a new GenericTypeBuilder with a specified array dimension and this object as the component type
     * @param dimension
     * @return a new GenerictypeBuilder
     */
    public GenericTypeBuilder deriveGenericArrayType(int dimension) {
        if (dimension < 1) {
            throw new IllegalArgumentException("Invalid value of dimension (ought to be no less than 1): dimension = " + dimension);
        }
        
        Type result = null;
        if (type instanceof Class) {
            int[] dimensions = new int[dimension];
            Arrays.fill(dimensions, 0);
            result = Array.newInstance((Class<?>) type, dimensions).getClass();
            
        } else {
            GenericArrayTypeImpl genericArrayType = null;
            Type genericComponentType = this.type;
            for (int i = 0; i < dimension; i++) {
                genericArrayType = new GenericArrayTypeImpl();
                genericArrayType.setGenericComponentType(genericComponentType);
                genericComponentType = genericArrayType;
            }
            result = genericArrayType;
        }
        
        GenericTypeBuilder ret = new GenericTypeBuilder();
        ret.type = result;
        
        return ret;
    }
    
    @Override
    public String toString() {
        return String.format("%s represents: %s", this.getClass().getSimpleName(), type.getTypeName());
    }
}
