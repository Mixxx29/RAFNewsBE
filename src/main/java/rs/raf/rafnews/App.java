package rs.raf.rafnews;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import rs.raf.rafnews.annotations.Repository;
import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.respositories.user.UserPostgresRepository;
import rs.raf.rafnews.respositories.user.UserRepository;
import rs.raf.rafnews.services.user.UserService;
import rs.raf.rafnews.utils.Utils;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.net.MalformedURLException;
import java.util.List;

@ApplicationPath("/api")
public class App extends ResourceConfig {

    public App() {
        // Dependencies
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                bindRepositories(this);
                bindServices(this);
                bind(UserPostgresRepository.class).to(UserRepository.class).in(Singleton.class);
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
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "*");
        }
    }

    private void bindRepositories(AbstractBinder binder) {
        try {
            List<Class<?>> classes = Utils.getClassesAnnotatedWith(Repository.class);
            if (classes == null) throw new RuntimeException("Get classes failed");
            for (Class<?> clazz : classes) {
            }
        } catch (MalformedURLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void bindServices(AbstractBinder binder) {
        try {
            List<Class<?>> classes = Utils.getClassesAnnotatedWith(Service.class);
            if (classes == null) throw new RuntimeException("Get classes failed");
            for (Class<?> clazz : classes) {
                binder.bindAsContract(clazz);
            }
        } catch (MalformedURLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}