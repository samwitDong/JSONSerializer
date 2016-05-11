package org.samwit.tools.jsonserializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class ParameterizedTypeImpl implements ParameterizedType {

    private Type[] actualTypeArguments;

    private Type rawType;

    public ParameterizedTypeImpl() {
    }

    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @Override
    public Type getRawType() {
        return rawType;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    public void setActualTypeArguments(Type[] actualTypeArguments) {
        this.actualTypeArguments = actualTypeArguments;
    }

    public void setRawType(Type rawType) {
        this.rawType = rawType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Type type : actualTypeArguments) {
            builder.append(type.getTypeName()).append(", ");
        }
        if (builder.length() != 0) {
            builder.setLength(builder.length() - 2);
        }
        return String.format("%s<%s>", rawType.getTypeName(), builder.toString());
    }
}
