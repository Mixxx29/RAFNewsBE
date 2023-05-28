package rs.raf.rafnews.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Class<?>> getClassesAnnotatedWith(
            Class<? extends Annotation> annotation
    ) throws ClassNotFoundException, MalformedURLException {
        String packageName = "rs.raf.rafnews";
        String packagePath = "rs/raf/rafnews";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageURL = classLoader.getResource(packagePath);
        if (packageURL == null) return null;

        return getClassesAnnotatedWith(annotation, packageURL, packageName);
    }

    private static List<Class<?>> getClassesAnnotatedWith(
            Class<? extends Annotation> annotation,
            URL packageURL,
            String packageName
    ) throws ClassNotFoundException, MalformedURLException {
        List<Class<?>> classes = new ArrayList<>();

        File packageDirectory = new File(packageURL.getFile());
        File[] files = packageDirectory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().replace(".class", "");
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(annotation))  {
                        classes.add(clazz);
                    }
                    continue;
                }
                URL subPackageURL = file.toURI().toURL();
                String subPackageName = packageName + "." + file.getName();
                classes.addAll(getClassesAnnotatedWith(annotation, subPackageURL, subPackageName));
            }
        }

        return classes;
    }
}
