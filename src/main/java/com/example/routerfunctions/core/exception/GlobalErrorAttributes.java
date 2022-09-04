package com.example.routerfunctions.core.exception;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.example.routerfunctions.core.util.ErrorAttributesUtils;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(final ServerRequest request,
            final ErrorAttributeOptions options) {
        Throwable exception = getError(request);
        Map<String, Object> map = super.getErrorAttributes(request, options);

        ErrorAttributesUtils.populateErrorAttributes(map, exception);

        return map;
    }
}
