package com.softserve.osbb.util.resources;

import org.springframework.hateoas.Resource;

/**
 * Created by nazar.dovhyy on 19.07.2016.
 */
public interface ResourceLinkCreator<T> {

    Resource<T> createLink(Resource<T> resourceT);
}
