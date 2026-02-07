package hw02_dynamic_programming_and_testing.test.config;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public Properties loadFromClasspath(String resourceName) throws Exception {
        Properties p = new Properties();

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (InputStream is = cl.getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IllegalArgumentException("Properties not found on classpath: " + resourceName);
            }
            p.load(is);
        }

        return p;
    }
}
