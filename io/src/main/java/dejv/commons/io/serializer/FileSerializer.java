package dejv.commons.io.serializer;

import dejv.commons.io.serializer.exceptions.SerializationException;

/**
 * Interface for file serialization backends.
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.2.0
 */
public interface FileSerializer {

    /**
     * Loads and deserializes the file.
     *
     * @param <T>   Configuration type
     * @param path Path of a file to load from
     * @param clazz Runtime type of the data
     * @return The configuration object
     * @throws SerializationException When loading or deserialization fails
     */
    <T> T load(String path, Class<T> clazz) throws SerializationException;

    /**
     * Serializes and stores the file.
     *
     * @param <T> Runtime type of the data object
     * @param path Path of a file to store the data into
     * @param data Data object
     * @throws SerializationException When storing or serialization fails
     */
    <T> void store(String path, T data) throws SerializationException;
}
