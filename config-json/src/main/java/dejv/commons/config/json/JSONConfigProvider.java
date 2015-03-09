package dejv.commons.config.json;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import dejv.commons.config.ConfigException;
import dejv.commons.config.ConfigProvider;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class JSONConfigProvider<T>
        implements ConfigProvider<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONConfigProvider.class);
    private static final String MSG_LOADING_CONFIG = "Loading configuration from: {}";
    private static final String MSG_STORING_CONFIG = "Storing configuration to: {}";

    private static final String ERR_UNABLE_TO_LOAD_CONFIGURATION = "Unable to load configuration";
    private static final String ERR_UNABLE_TO_STORE_CONFIGURATION = "Unable to store configuration";
    private static final String ERR_UNABLE_TO_INITIALIZE_FILE = "Unable to initialize config file: ";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file;


    public JSONConfigProvider(Path filePath) throws ConfigException {
        file = filePath.toFile();
        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }

        } catch (IOException | SecurityException e) {

            throw new ConfigException(ERR_UNABLE_TO_INITIALIZE_FILE + this.file.getAbsolutePath(), e);
        }
    }


    @Override
    public T load(Class<T> clazz) throws ConfigException {
        LOGGER.info(MSG_LOADING_CONFIG, file.getPath());

        try {
            return objectMapper.readValue(file, clazz);

        } catch (IOException e) {

            throw new ConfigException(ERR_UNABLE_TO_LOAD_CONFIGURATION, e);
        }
    }


    @Override
    public void store(T config) throws ConfigException {
        try {
            requireNonNull(config, "Parameter 'config' is null");

            LOGGER.info(MSG_STORING_CONFIG, file.getPath());

            objectMapper.writeValue(file, config);

        } catch (IOException | NullPointerException e) {

            throw new ConfigException(ERR_UNABLE_TO_STORE_CONFIGURATION, e);
        }
    }
}
