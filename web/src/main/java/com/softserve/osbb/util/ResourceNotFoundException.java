package com.softserve.osbb.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nazar.dovhyy on 19.07.2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Resource not found")
public class ResourceNotFoundException extends RuntimeException {
}
