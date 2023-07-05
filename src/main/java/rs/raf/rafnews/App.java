package rs.raf.rafnews;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import rs.raf.rafnews.annotations.Repository;
import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.entities.Category;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.GenericRepository;
import rs.raf.rafnews.respositories.category.CategoryRepositoryImpl;
import rs.raf.rafnews.respositories.news.NewsRepositoryImpl;
import rs.raf.rafnews.respositories.user.UserRepositoryImpl;
import rs.raf.rafnews.utils.EntityManager;
import rs.raf.rafnews.utils.Utils;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;

@ApplicationPath("/")
public class App extends ResourceConfig {

    public App() {
        // Dependencies
        bind();

        // Load resources
        packages("rs.raf.rafnews");
    }

    @PostConstruct
    public void init() {
        register(CorsFilter.class);
    }

    private void bind() {
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                // Bind services
                try {
                    List<Class<?>> classes = Utils.getClassesAnnotatedWith(Service.class);
                    if (classes == null) throw new RuntimeException("Get classes failed");
                    for (Class<?> serviceClass : classes) {
                        bindAsContract(serviceClass);
                    }
                } catch (MalformedURLException | ClassNotFoundException e) {
                    throw new RuntimeException("Bind services failed: " + e);
                }

                // Bind repository
                try {
                    List<Class<?>> classes = Utils.getClassesAnnotatedWith(Repository.class);
                    if (classes == null) throw new RuntimeException("Get classes failed");
                    for (Class<?> repositoryClass : classes) {
                        Class<?> repositoryImplementationClass =
                                Class.forName(repositoryClass.getName() + "Impl");
                        bind(repositoryImplementationClass).to(repositoryClass).in(Singleton.class);

                        // Entity Manager
                        Class<?> entityType = getEntityType(repositoryImplementationClass);
                        EntityManager.setRepository(entityType, (GenericRepository<?>) repositoryImplementationClass.newInstance());
                    }
                } catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("Bind repository failed: " + e);
                }
            }
        });
    }

    private Class<?> getEntityType(Class<?> clazz) {
        Type superclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        Type entityType = typeArguments[0];
        return (Class<?>) entityType;
    }

    @Provider
    public static class CorsFilter implements ContainerResponseFilter {
        @Override
        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        }
    }
}