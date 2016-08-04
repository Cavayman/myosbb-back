package com.softserve.osbb.util;

import org.springframework.hateoas.Resource;

/**
 * Created by nazar.dovhyy on 19.07.2016.
 */
public class ResourceUtil {

    public static <T> Resource<T> toResource(T entity) {
        if (entity == null) {
            throw new ResourceNotFoundException();
        }
        Resource<T> entityResource = new Resource<>(entity);
        return entityResource;
    }



}
