package rs.raf.rafnews;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import rs.raf.rafnews.respositories.user.UserPostgresRepository;
import rs.raf.rafnews.respositories.user.UserRepository;
import rs.raf.rafnews.services.user.UserService;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@ApplicationPath("/api")
public class App extends ResourceConfig {

    public App() {
        // Dependencies
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                bind(UserPostgresRepository.class).to(UserRepository.class).in(Singleton.class);
                bindAsContract(UserService.class);
            }
        };
        register(binder);

        // Load resources
        packages("rs.raf.rafnews");
    }

    @PostConstruct
    public void init() {
        register(CorsFilter.class);
    }

    @Provider
    public static class CorsFilter implements ContainerResponseFilter {
        @Override
        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
        }
    }
}