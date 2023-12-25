package com.tpo.fizio.context;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Tadej Delopst
 */

@RequestScope
@Component
public class RequestContext {


    public RequestContext(HttpServletRequest request) {

    }

    private final OffsetDateTime offsetDateTime = OffsetDateTime.now();
    private final String requestId = UUID.randomUUID().toString();


    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
