package io.github.roony11_1.error.quarkus;

import io.github.roony11_1.error.core.ErrorCategory;
import io.github.roony11_1.error.core.StandardErrorCategories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class HttpStatusRegistry 
{
    private static final Map<ErrorCategory, Integer> STATUS_MAP = new ConcurrentHashMap<>();

    static 
    {
        STATUS_MAP.put(StandardErrorCategories.NOT_FOUND, 404);
        STATUS_MAP.put(StandardErrorCategories.ALREADY_EXISTS, 409);
        STATUS_MAP.put(StandardErrorCategories.INVALID_INPUT, 400);
        STATUS_MAP.put(StandardErrorCategories.ACCESS_DENIED, 401);
        STATUS_MAP.put(StandardErrorCategories.INTERNAL_ERROR, 500);
    }

    private HttpStatusRegistry() {}

    public static void register(ErrorCategory category, int httpStatus) 
    {
        STATUS_MAP.put(category, httpStatus);
    }

    public static int getStatus(ErrorCategory category) 
    {
        return STATUS_MAP.getOrDefault(category, 500);
    }
}