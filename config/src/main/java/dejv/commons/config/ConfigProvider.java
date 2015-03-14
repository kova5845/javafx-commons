package dejv.commons.config;

/**
 * Interface for configuration persistence backends.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public interface ConfigProvider {

    /**
     * Loads the configuration.
     *
     * @param clazz Runtime class of the configuration type
     * @param <T>   Configuration type
     * @return The configuration object
     * @throws ConfigException When loading operation of the configuration fails.
     */
    <T> T load(Class<T> clazz) throws ConfigException;

    /**
     * Stores the given configuration.
     *
     * @param config The configuration object
     * @param <T>    Configuration type
     * @throws ConfigException When storing operation of the configuration fails.
     */
    <T> void store(T config) throws ConfigException;
}
