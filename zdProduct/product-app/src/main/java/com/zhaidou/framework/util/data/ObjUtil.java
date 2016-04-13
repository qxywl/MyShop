package com.zhaidou.framework.util.data;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.zhaidou.framework.util.code.CodeUtil;
import com.zhaidou.framework.util.data.MapObj.MapOneObj;
import com.zhaidou.framework.util.reflect.ReflectUtil;
import com.zhaidou.framework.util.string.StringUtil;

public class ObjUtil {
    
    public static Obj toObj(Object object)
    {
        Obj result;
        
        if(ReflectUtil.isArray(null, object)||object instanceof Collection)
        {
            ListObj list = new ListObj();
            if(ReflectUtil.isArray(null,object))
            {
                Object[] objectArray = ReflectUtil.getArray(object);
                int length = objectArray.length;
                Obj[] objArray = new Obj[length];
                for (int i = 0; i < length; i++) {
                    objArray[i] = toObj(objectArray[i]);
                }
                list.setArray(objArray);
            }
            else
            {
                Collection<?> objectCollection = (Collection<?>)object;
                int length = objectCollection.size();
                Obj[] objArray = new Obj[length];
                int i = 0;
                for (Object objectOne : objectCollection) {
                    objArray[i++] = toObj(objectOne);
                }
                list.setArray(objArray);
            }
            result = list;
        }
        else if(ReflectUtil.isModel(object, null)||object instanceof Map)
        {
            MapObj map = new MapObj();
            if(ReflectUtil.isModel(object, null))
            {
                Class<?> objectClazz = object.getClass();
                Map<String, Method> fieldNameGetmethodMap = ReflectUtil.getFieldNameAndGetmethodMap(objectClazz);
                int length = fieldNameGetmethodMap.size();
                MapOneObj[] mapOneObjArray = new MapOneObj[length];
                Set<Entry<String, Method>> fieldNameGetmethodEntrySet = fieldNameGetmethodMap.entrySet();
                int i=0;
                for (Entry<String, Method> entry : fieldNameGetmethodEntrySet) {
                    String key = entry.getKey();
                    Object fieldValue = ReflectUtil.methodInvoke(object, entry.getValue(), new Object[]{});
                    Obj value = toObj(fieldValue);
                    MapOneObj mapOneObj = new MapOneObj();
                    mapOneObj.setKey(key);
                    mapOneObj.setValue(value);
                    mapOneObjArray[i++] = mapOneObj;
                }
                map.setArray(mapOneObjArray);
            }
            else
            {
                Map<?, ?> objectMap = (Map<?,?>)object;
                int length = objectMap.size();
                MapOneObj[] mapOneObjArray = new MapOneObj[length];
                Set<?> objectKeySet = objectMap.keySet();
                int i=0;
                for (Object objectKey : objectKeySet) {
                    String key = StringUtil.toString(objectKey);
                    Object objectValue = objectMap.get(objectKey);
                    Obj value = toObj(objectValue);
                    MapOneObj mapOneObj = new MapOneObj();
                    mapOneObj.setKey(key);
                    mapOneObj.setValue(value);
                    mapOneObjArray[i++] = mapOneObj;
                }
                map.setArray(mapOneObjArray);
            }
            result = map;
        }
        else
        {
            OneObj obj = new OneObj();
            obj.setOne(object);
            result = obj;
        }
        return result;
    }
    
    public static <T> T toModel(MapObj map, Class<T> clazz)
    {
        CodeUtil.emptyCheck(ObjUtil.class+".toObject clazz is null", new Object[]{clazz});
        
        T result = null;
        if(!CodeUtil.isEmpty(map))
        {
            result = ReflectUtil.newModel(clazz);
            Map<String, Method> fieldNameSetmethodMap = ReflectUtil.getFieldNameAndSetmethodMap(clazz);
            
            MapObj.MapOneObj[] array = map.getArray();
            if(!CodeUtil.isEmpty(new Object[]{array}))
            {
                for (MapObj.MapOneObj one : array) {
                    String key = one.getKey();
                    Obj value = one.getValue();
                    
                    Object fieldValue = null;
                    
                    Method setMethod = fieldNameSetmethodMap.get(key);
                    if(setMethod!=null)
                    {
                        Class<?> fieldType = setMethod.getParameterTypes()[0];
                        if(value instanceof MapObj)
                        {
                            fieldValue = toModel((MapObj)value, fieldType);
                        }
                        else if(value instanceof ListObj)
                        {
                            if(fieldType.isArray())
                            {
                                Class<?> oneClazz = fieldType.getComponentType();
                                fieldValue = toArray((ListObj)value, oneClazz);
                            }
                            else if(Collection.class.isAssignableFrom(fieldType))
                            {
                                Class<?> oneClazz = (Class<?>) ((ParameterizedType)setMethod.getGenericParameterTypes()[0]).getActualTypeArguments()[0];
                                Collection<?> collection = toCollection((ListObj)value, oneClazz);
                                fieldValue = newCollection(collection,fieldType);
                            }
                        }
                        else if(value instanceof OneObj)
                        {
                            fieldValue = toObject((OneObj)value, fieldType);
                        }
                    }
                    
                    if(fieldValue!=null)
                    {
                        ReflectUtil.setMethodInvoke(result, key, fieldValue);
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * 从collection中组装指定的子类
     * @param collection
     * @param collectionClass
     * @return
     */
    public static <T> Object newCollection(Collection<T> collection, Class<?> collectionClass) {
        // TODO Auto-generated method stub
        CodeUtil.emptyCheck(ReflectUtil.class+".newCollection collectionClass is null", collectionClass);
        
        Object result;
        if(ArrayList.class.isAssignableFrom(collectionClass))
        {
            result = new ArrayList<T>(collection);
        }
        else if(LinkedList.class.isAssignableFrom(collectionClass))
        {
            result = new LinkedList<T>(collection);
        }
        else if(Vector.class.isAssignableFrom(collectionClass))
        {
            result = new Vector<T>(collection);
        }
        else if(List.class.isAssignableFrom(collectionClass))
        {
            result = new ArrayList<T>(collection);
        }
        else if(LinkedHashSet.class.isAssignableFrom(collectionClass))
        {
            result = new LinkedHashSet<T>(collection);
        }
        else if(HashSet.class.isAssignableFrom(collectionClass))
        {
            result = new HashSet<T>(collection);
        }
        else if(TreeSet.class.isAssignableFrom(collectionClass))
        {
            result = new TreeSet<T>(collection);
        }
        else if(SortedSet.class.isAssignableFrom(collectionClass))
        {
            result = new TreeSet<T>(collection);
        }
        else if(ConcurrentLinkedQueue.class.isAssignableFrom(collectionClass))
        {
            result = new ConcurrentLinkedQueue<T>(collection);
        }
        else if(PriorityQueue.class.isAssignableFrom(collectionClass))
        {
            result = new PriorityQueue<T>(collection);
        }
        else if(Queue.class.isAssignableFrom(collectionClass))
        {
            result = new ConcurrentLinkedQueue<T>(collection);
        }
        else
        {
            result = collection;
        }
        return result;      
    }
    
    public static <T> T[] toArray(ListObj list, Class<T> clazz)
    {
        CodeUtil.emptyCheck(ObjUtil.class+".toObject clazz is null", new Object[]{clazz});
        
        T[] result = ReflectUtil.newArray(clazz, 0);
        if(!CodeUtil.isEmpty(list))
        {
            Obj[] array = list.getArray();
            if(!CodeUtil.isEmpty(new Object[]{array}))
            {
                int length = array.length;
                result = ReflectUtil.newArray(clazz, length);
                
                for (int i = 0; i < length; i++) {
                    Obj one = array[i];
                    
                    if(one instanceof MapObj)
                    {
                        result[i] = toModel((MapObj)one, clazz);
                    }
                    else if(one instanceof ListObj)
                    {
                        if(clazz.isArray())
                        {
                            Class<?> oneClazz = clazz.getComponentType();
                            @SuppressWarnings("unchecked")
                            T t = (T) toArray((ListObj)one, oneClazz);
                            result[i] = t;
                        }
                    }
                    else if(one instanceof OneObj)
                    {
                        result[i] = toObject((OneObj)one, clazz);
                    }
                }
            }
        }
        return result;
    }
    
    public static <T> Collection<T> toCollection(ListObj list, Class<T> clazz)
    {
        CodeUtil.emptyCheck(ObjUtil.class+".toObject clazz is null", new Object[]{clazz});
        
        Collection<T> result = new ArrayList<T>();
        if(!CodeUtil.isEmpty(list))
        {
            Obj[] array = list.getArray();
            if(!CodeUtil.isEmpty(new Object[]{array}))
            {
                int length = array.length;
                for (int i = 0; i < length; i++) {
                    Obj one = array[i];
                    
                    if(one instanceof MapObj)
                    {
                        result.add(toModel((MapObj)one, clazz));
                    }
                    else if(one instanceof ListObj)
                    {
                        if(clazz.isArray())
                        {
                            Class<?> oneClazz = clazz.getComponentType();
                            @SuppressWarnings("unchecked")
                            T t = (T) toArray((ListObj)one, oneClazz);
                            result.add(t);
                        }
                    }
                    else if(one instanceof OneObj)
                    {
                        result.add(toObject((OneObj)one, clazz));
                    }
                }
            }
        }
        
        return result;
    }
    
    public static <T> T toObject(OneObj obj, Class<T> clazz)
    {
        CodeUtil.emptyCheck(ObjUtil.class+".toObject clazz is null", new Object[]{clazz});
        
        T result = null;
        if(!CodeUtil.isEmpty(obj))
        {
            Object object = obj.getOne();
            if(object!=null)
            {
                Class<?> objectClazz = object.getClass();
                if(ReflectUtil.isSimpleClazz(objectClazz))
                {
                    result = StringUtil.getValueFromString(object.toString(), clazz);
                }
                else if(clazz.isAssignableFrom(objectClazz))
                {
                    @SuppressWarnings("unchecked")
                    T t = (T)object;
                    result = t;
                }
            }
        }
        return result;
    }
    
}
