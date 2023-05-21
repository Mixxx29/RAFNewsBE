package rs.raf.rafnews;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import rs.raf.rafnews.respositories.user.UserPostgresRepository;
import rs.raf.rafnews.respositories.user.UserRepository;
import rs.raf.rafnews.services.user.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

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
}