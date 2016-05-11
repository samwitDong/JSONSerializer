package org.samwit.tools.json.serializer;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.samwit.tools.json.serializer.annotations.ExportName;
import org.samwit.tools.json.serializer.annotations.Unexportable;

/**
 * Grant your data object the ability of being serialized into a JSON and deserialized from a JSON by extending this abstract class
 * @author samwitDong
 *
 */
public abstract class JSONBean {
    
    private static final HashMap<Class<?>, Method> TRANS_MAP = new HashMap<Class<?>, Method>();
    
    static {
        try {
            TRANS_MAP.put(boolean.class, Boolean.class.getMethod("valueOf", String.class));
            TRANS_MAP.put(Boolean.class, TRANS_MAP.get(boolean.class));
            TRANS_MAP.put(byte.class, Byte.class.getMethod("valueOf", String.class));
            TRANS_MAP.put(Byte.class, TRANS_MAP.get(byte.class));
            TRANS_MAP.put(char.class, JSONBean.class.getDeclaredMethod("valueOf", String.class));
            TRANS_MAP.put(Character.class, TRANS_MAP.get(char.class));
            TRANS_MAP.put(short.class, Short.class.getMethod("valueOf", String.class));
            TRANS_MAP.put(Short.class, TRANS_MAP.get(short.class));
            TRANS_MAP.put(int.class, Integer.class.getMethod("valueOf", String.class));
            TRANS_MAP.put(Integer.class, TRANS_MAP.get(int.class));
            TRANS_MAP.put(long.class, Long.class.getMethod("valueOf", String.class));
            TRANS_MAP.put(Long.class, TRANS_MAP.get(long.class));
            TRANS_MAP.put(float.class, Float.class.getMethod("valueOf", String.class));
            TRANS_MAP.put(Float.class, TRANS_MAP.get(float.class));
            TRANS_MAP.put(double.class, Double.class.getMethod("valueOf", String.class));
            TRANS_MAP.put(Double.class, TRANS_MAP.get(double.class));
            TRANS_MAP.put(String.class, JSONBean.class.getDeclaredMethod("stringOf", String.class));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JSONBean() {
    }
    
    /**
     * Return the JSON string that represents this object
     * @return JSON string
     */
    public final String toJSONString() {
        return toJSONObject().toString();
    }
    
    /**
     * Return the JSON object that represents this object
     * @return JSON object
     */
    public final JSONObject toJSONObject() {
        Class<?> cls = getClass();
        JSONObject json = new JSONObject();
        
        while (!JSONBean.class.equals(cls)) {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Unexportable.class) != null) {
                    continue;
                }
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }
                
                try {
                    Object value = parseField(field);
                    ExportName exportName = field.getAnnotation(ExportName.class);
                    String name = (exportName != null) ? exportName.value() : field.getName();
                    json.put(name, value);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cls = cls.getSuperclass();
        }
        return json;
    }
    
    private Object parseField(Field field) throws IllegalArgumentException, IllegalAccessException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            Object value = parseObject(field.get(this));
            return value;
        }
        finally {
            field.setAccessible(accessible);
        }
    }
    
    private Object parseObject(Object object) {
        if (object == null) {
            return null;
        }
        Object value = null;
        Class<?> cls = object.getClass();
        Method transMethod = TRANS_MAP.get(cls);
        
        if (cls.isArray()) {
            value = parseArray(object);
            
        } else if (object instanceof Collection) {
            Collection<?> collection = (Collection<?>) object;
            if (collection.size() > 0) {
                Object[] components = collection.toArray();
                value = parseArray(components);
            }
            
        } else if (object instanceof Map) {
            value = parseMap((Map<?, ?>) object);
            
        } else if (object instanceof JSONBean) {
            value = ((JSONBean) object).toJSONObject();
            
        } else if (cls.isEnum()) {
            value = object.toString();
            
        } else if (transMethod != null) {
            try {
                value = transMethod.invoke(null, String.valueOf(object));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            
        } else {
            value = null;
        }
        
        return value;
    }
    
    private Object parseArray(Object components) {
        int length = Array.getLength(components);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            Object value = Array.get(components, i);
            jsonArray.put(parseObject(value));
        }
        return jsonArray;
    }
    
    private Object parseMap(Map<?, ?> map) {
        if (map.size() == 0) {
            return null;
        }
        Class<?> clazz = map.entrySet().iterator().next().getKey().getClass();
        if (!(TRANS_MAP.containsKey(clazz) || clazz.isEnum())) {
            return null;
        }
        
        JSONObject ret = new JSONObject();
        for(Entry<?, ?> entry : map.entrySet()) {
            ret.put(String.valueOf(entry.getKey()), parseObject(entry.getValue()));
        }
        return ret;
    }
    
    /**
     * Parse a JSON string and load its values into this object
     * @param jsonString
     */
    public final void loadJSONString(String jsonString) {
        loadJSONObject(new JSONObject(jsonString));
    }
    
    /**
     * Parse a JSON string and load its values into this object
     * @param jsonString
     * @param pType represents the actual value of generic type parameters 
     */
    public final void loadJSONString(String jsonString, Type pType) {
        loadJSONObject(new JSONObject(jsonString), pType);
    }
    
    /**
     * Parse a JSON object and load its values into this object
     * @param jsonObject
     */
    public final void loadJSONObject(JSONObject jsonObject) {
        loadJSONObject(jsonObject, null);
    }
    
    /**
     * Parse a JSON object and load its values into this object
     * @param jsonObject
     * @param pType represents the actual value of generic type parameters
     */
    public final void loadJSONObject(JSONObject jsonObject, Type pType) {
        if (jsonObject == null) {
            return;
        }
        
        HashMap<String, Type> typeParamsRef =  new HashMap<>();
        if (pType instanceof ParameterizedType) {
            TypeVariable<?>[] typeParamsDefined = this.getClass().getTypeParameters();
            Type[] typeParamsActual  = ((ParameterizedType) pType).getActualTypeArguments();
            int length = typeParamsDefined.length;
            if (typeParamsActual.length < length) {
                throw new RuntimeException("Internal error: type parameters mismatch.");
            }
            for (int i = 0; i < length; i++) {
                typeParamsRef.put(typeParamsDefined[i].getTypeName(), typeParamsActual[i]);
            }
        }
        
        Class<?> cls = getClass();
        while (!JSONBean.class.equals(cls)) {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                Class<?> fieldClass = field.getType();
                if (fieldClass.getAnnotation(Unexportable.class) != null) {
                    continue;
                }
                
                ExportName exportName = field.getAnnotation(ExportName.class);
                String fieldName = (exportName != null) ? exportName.value() : field.getName();
                if (jsonObject.opt(fieldName) == null) {
                    continue;
                }
                
                Type standardGenericType = getStandardGenericType(field.getGenericType(), typeParamsRef);
                Object value = readObject(standardGenericType, jsonObject.opt(fieldName));
                
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                try {
                    field.set(this, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                field.setAccessible(accessible);
            }
            
            cls = cls.getSuperclass();
        }
    }
    
    private Object readObject(Type gType, Object object) {
        if (object == null) {
            return null;
        }
        
        Object value = null;
        Class<?> clazz = extractClass4Interation(gType);
        if (clazz == null) {
            return null;
        }
        
        Method transMethod = TRANS_MAP.get(clazz);
        if (clazz.isArray()) {
            Type type = (gType instanceof GenericArrayType) ? ((GenericArrayType) gType).getGenericComponentType() : clazz.getComponentType();
            value = readArray(clazz.getComponentType(), type, (JSONArray) object);
            
        } else if (Collection.class.isAssignableFrom(clazz)) {
            value = readCollection(clazz, gType, (JSONArray) object);
            
        } else if (Map.class.isAssignableFrom(clazz)) {
            value = readMap(clazz, gType, (JSONObject) object);
            
        } else if (JSONBean.class.isAssignableFrom(clazz)) {
            value = readJSONBean(clazz, gType, (JSONObject) object);
            
        } else if (clazz.isEnum()) {
            Field field = null;
            try {
                field = clazz.getField(String.valueOf(object));
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
            if (field == null) {
                return null;
            }
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);
            try {
                value = field.get(null);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(isAccessible);
            }
            
        } else if (transMethod != null) {
            try {
                value = transMethod.invoke(null, String.valueOf(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            value = object;
        }
        return value;
    }

    private Object readArray(Class<?> componentClass, Type gType, JSONArray components) {
        int length = components.length();
        Object array = Array.newInstance(componentClass, length);
        for (int i = 0; i < length; i++) {
            Array.set(array, i, readObject(gType, components.opt(i)));
        }
        return array;
    }
    
    @SuppressWarnings("unchecked")
    private Object readCollection(Class<?> colClass, Type gType, JSONArray components) {
        Collection<Object> collection = null;
        try {
            collection = (Collection<Object>) colClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (collection == null) {
            return null;
        }
        
        Type componentGenericType = (gType instanceof ParameterizedType) ? ((ParameterizedType) gType).getActualTypeArguments()[0] : Object.class;
        
        int length = components.length();
        for (int i = 0; i < length; i++) {
            collection.add(readObject(componentGenericType, components.opt(i)));
        }
        return collection;
    }
    
    @SuppressWarnings("unchecked")
    private Object readMap(Class<?> mapCls, Type gType, JSONObject jsonObject) {
        Map<Object, Object> map = null;
        try {
            map = (Map<Object, Object>) mapCls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (map == null) {
            return null;
        }
        
        Type keyType = (gType instanceof ParameterizedType) ? ((ParameterizedType) gType).getActualTypeArguments()[0] : Object.class;
        Type valueType = (gType instanceof ParameterizedType) ? ((ParameterizedType) gType).getActualTypeArguments()[1] : Object.class;
        for (String key : jsonObject.keySet()) {
            map.put(readObject(keyType, key), readObject(valueType, jsonObject.opt(key)));
        }
        return map;
    }
    
    private JSONBean readJSONBean(Class<?> cls, Type gType, JSONObject obj) {
        if (obj == null) {
            return null;
        }
        JSONBean bean = null;
        try {
            bean = (JSONBean) cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e);
            Boolean isAccessible = false;
            Constructor<?> constructor = null;
            try {
                constructor = (Constructor<?>) cls.getConstructor(new Class[0]);
                isAccessible = constructor.isAccessible();
                constructor.setAccessible(true);
                bean = (JSONBean) constructor.newInstance(new Object[0]);
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                if (constructor != null) {
                    constructor.setAccessible(isAccessible);
                }
            }
        }
        try {
            bean.loadJSONObject(obj, (ParameterizedType) ((gType instanceof ParameterizedType) ? gType : (null)));
            return bean;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Class<?> extractClass4Interation(Type genericType) {
        if (genericType == null) {
            return null;
        }
        if (genericType instanceof Class) {
            return (Class<?>) genericType;
        }
        if (genericType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) genericType;
            return (Class<?>) pType.getRawType();
        }
        if (genericType instanceof GenericArrayType) {
            return getGenericArrayTypeClass((GenericArrayType) genericType);
        }
        return null;
    }
    
    private Class<?> getGenericArrayTypeClass(GenericArrayType genericArrayType) {
        Type type = null;
        int count = 1;
        GenericArrayType gaType = genericArrayType;
        while ((type = gaType.getGenericComponentType()) instanceof GenericArrayType) {
            count++;
            gaType = (GenericArrayType) type;
        }
        try {
            Class<?> clazz = extractClass4Interation(type);
            if (clazz == null) {
                return null;
            }
            int[] parameters = new int[count];
            Arrays.fill(parameters, 0);
            Object object = Array.newInstance(clazz, parameters);
            return object.getClass();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Type getStandardGenericType(Type gType, HashMap<String, Type> typeParamsRef) {
        if (gType instanceof Class) {
            return gType;
        }
        if (gType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) gType;
            ParameterizedTypeImpl pTypeTarget = new ParameterizedTypeImpl();
            pTypeTarget.setRawType(pType.getRawType());
            Type[] actualTypesTarget = null;
            Type[] actualTypes = pType.getActualTypeArguments();
            if (actualTypes == null) {
                actualTypesTarget = new Type[0];
            } else {
                int length = actualTypes.length;
                actualTypesTarget = new Type[length];
                for (int i = 0; i < length; i++) {
                    actualTypesTarget[i] = getStandardGenericType(actualTypes[i], typeParamsRef);
                }
            }
            pTypeTarget.setActualTypeArguments(actualTypesTarget);
            return pTypeTarget;
        }
        
        if (gType instanceof GenericArrayType) {
            GenericArrayTypeImpl gaTypeTarget = new GenericArrayTypeImpl();
            gaTypeTarget.setGenericComponentType(getStandardGenericType(((GenericArrayType) gType).getGenericComponentType(), typeParamsRef));
            return gaTypeTarget;
        }
        
        if (gType instanceof TypeVariable) {
            return typeParamsRef.get(((TypeVariable<?>) gType).getName());
        }
        
        return null;
    }
    
    @SuppressWarnings("unused")
    private static Character valueOf(String str) {
        return Character.valueOf(str.charAt(0));
    }
    
    @SuppressWarnings("unused")
    private static String stringOf(String str) {
        return str;
    }
}
