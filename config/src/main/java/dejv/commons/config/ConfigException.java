package dejv.commons.config;

/**
 * Exception related to config persistence operations.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class ConfigException
        extends Exception {

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
