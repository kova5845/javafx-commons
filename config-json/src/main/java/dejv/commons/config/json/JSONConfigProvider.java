package dejv.commons.config.json;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dejv.commons.config.ConfigException;
import dejv.commons.config.ConfigProvider;

/**
 * JSON configuration persistence backend.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class JSONConfigProvider
        implements ConfigProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONConfigProvider.class);
    private static final String MSG_LOADING_CONFIG = "Loading configuration from: {}";
    private static final String MSG_STORING_CONFIG = "Storing configuration to: {}";

    private static final String ERR_UNABLE_TO_LOAD_CONFIGURATION = "Unable to load configuration";
    private static final String ERR_UNABLE_TO_STORE_CONFIGURATION = "Unable to store configuration";
    private static final String ERR_UNABLE_TO_INITIALIZE_FILE = "Unable to initialize config file: ";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file;


    /**
     * Initialize the JSON backend, and link it to the physical file.
     *
     * @param filePath Path of the file to load (resp. store) the configuration from (resp. to).
     * @throws ConfigException When it is not possible to open the file on the given path.
     */
    public JSONConfigProvider(Path filePath) throws ConfigException {
        file = filePath.toFile();
        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            }

            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        } catch (IOException | SecurityException e) {

            throw new ConfigException(ERR_UNABLE_TO_INITIALIZE_FILE + this.file.getAbsolutePath(), e);
        }
    }


    @Override
    public <T> T load(Class<T> clazz) throws ConfigException {
        LOGGER.info(MSG_LOADING_CONFIG, file.getPath());

        try {
            return objectMapper.readValue(file, clazz);

        } catch (IOException e) {

            throw new ConfigException(ERR_UNABLE_TO_LOAD_CONFIGURATION, e);
        }
    }


    @Override
    public <T> void store(T config) throws ConfigException {
        try {
            requireNonNull(config, "Parameter 'config' is null");

            LOGGER.info(MSG_STORING_CONFIG, file.getPath());

            objectMapper.writeValue(file, config);

        } catch (IOException | NullPointerException e) {

            throw new ConfigException(ERR_UNABLE_TO_STORE_CONFIGURATION, e);
        }
    }
}
