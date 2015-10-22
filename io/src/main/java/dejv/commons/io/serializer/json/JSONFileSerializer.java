package dejv.commons.io.serializer.json;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dejv.commons.io.serializer.FileSerializer;
import dejv.commons.io.serializer.exceptions.SerializationException;

/**
 * JSON File serializer
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.2.0
 */
public class JSONFileSerializer
        implements FileSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONFileSerializer.class);
    private static final String MSG_LOADING_DATA = "Loading structure from: {}";
    private static final String MSG_STORING_DATA = "Storing structure to: {}";

    private static final String ERR_UNABLE_TO_LOAD = "Unable to load structure from file";
    private static final String ERR_UNABLE_TO_STORE = "Unable to store structure to a file";
    private static final String ERR_UNABLE_TO_INITIALIZE_FILE = "Unable to initialize file: ";


    @Override
    public <T> T load(String path, Class<T> clazz) throws SerializationException {

        requireNonNull(path, "Parameter 'path' is null");
        requireNonNull(clazz, "Parameter 'clazz' is null");

        final ObjectMapper objectMapper = new ObjectMapper();

        try {

            LOGGER.debug(MSG_LOADING_DATA, path);

            return objectMapper.readValue(getFile(path, false), clazz);

        } catch (Exception e) {

            throw new SerializationException(ERR_UNABLE_TO_LOAD, e);
        }
    }


    @Override
    public <T> void store(String path, T data) throws SerializationException {

        requireNonNull(path, "Parameter 'path' is null");
        requireNonNull(data, "Parameter 'structure' is null");

        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        try {
            LOGGER.debug(MSG_STORING_DATA, path);

            objectMapper.writeValue(getFile(path, true), data);

        } catch (Exception e) {

            throw new SerializationException(ERR_UNABLE_TO_STORE, e);
        }
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    private File getFile(String path, boolean create) throws SerializationException {
        final Path filePath = Paths.get(path);
        final File file = filePath.toFile();

        try {
            if (!file.exists() && create) {
                file.createNewFile();
            }
            return file;

        } catch (IOException | SecurityException e) {
            throw new SerializationException(ERR_UNABLE_TO_INITIALIZE_FILE + path, e);
        }
    }

}
