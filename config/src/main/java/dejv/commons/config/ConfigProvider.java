package dejv.commons.config;

/**
 * <p>
 * @since 1.0.0
 * @author dejv78 (dejv78.github.io)
 */
public interface ConfigProvider<T> {

    T load(Class<T> clazz) throws ConfigException;

    void store(T config) throws ConfigException;
}
