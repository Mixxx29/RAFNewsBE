package rs.raf.rafnews.utils;

import rs.raf.rafnews.respositories.GenericRepository;

import java.util.HashMap;
import java.util.Map;

public enum EntityManager {
    INSTANCE;

    private final Map<Class<?>, GenericRepository<?>> repositories = new HashMap<>();

    public static void setRepository(Class<?> entityClass, GenericRepository<?> repository) {
        INSTANCE.repositories.put(entityClass, repository);
    }

    public static GenericRepository<?> getRepository(Class<?> entityClass) {
        return INSTANCE.repositories.get(entityClass);
    }
}
