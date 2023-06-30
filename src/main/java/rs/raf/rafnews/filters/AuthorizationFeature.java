package rs.raf.rafnews.filters;

import lombok.RequiredArgsConstructor;
import rs.raf.rafnews.annotations.Authorize;
import rs.raf.rafnews.entities.enums.UserType;
import rs.raf.rafnews.services.user.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthorizationFeature implements DynamicFeature {

    @Inject
    protected UserService userService;

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext featureContext) {
        if (resourceInfo.getResourceMethod().isAnnotationPresent(Authorize.class)) {
            UserType level = resourceInfo.getResourceMethod().getAnnotation(Authorize.class).value();
            featureContext.register(new Filter(level));
        }
    }

    @RequiredArgsConstructor
    private class Filter implements ContainerRequestFilter {

        private final UserType level;

        @Override
        public void filter(ContainerRequestContext containerRequestContext) throws IOException {
            try {
                String token = containerRequestContext.getHeaderString("Authorization");
                if (token == null || !token.startsWith("Bearer ")) abort(containerRequestContext);
                token = token.replace("Bearer ", "");
                if (token.trim().equals("")) abort(containerRequestContext);
                if (!userService.isAuthorized(token, level)) abort(containerRequestContext);
            } catch (NullPointerException e) {
                abort(containerRequestContext);
            }
        }

        private void abort(ContainerRequestContext containerRequestContext) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
