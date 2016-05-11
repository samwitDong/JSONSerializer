package org.samwit.tools.jsonserializer;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

class GenericArrayTypeImpl implements GenericArrayType {

    private Type genericComponentType;

    public GenericArrayTypeImpl() {
    }

    @Override
    public Type getGenericComponentType() {
        return genericComponentType;
    }

    public void setGenericComponentType(Type genericComponentType) {
        this.genericComponentType = genericComponentType;
    }

    @Override
    public String toString() {
        return genericComponentType.getTypeName() + "[]";
    }

}
