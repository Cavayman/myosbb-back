package com.softserve.osbb.util.resources;

import org.springframework.hateoas.Resource;

import java.util.ArrayList;

/**
 * Created by nazar.dovhyy on 19.07.2016.
 */
public abstract class EntityResourceList<T> extends ArrayList<Resource<T>> implements ResourceLinkCreator<T> {

    @Override
    public boolean add(Resource<T> tResource) {
        return super.add(createLink(tResource));
    }


}
