package com.ibs.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibs.managers.PropManager;
import com.ibs.models.Good;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.ibs.utils.constants.PropConst.PATH_TO_TEST_RESOURCES;

public class TestDataUtils {
    private static final PropManager propManager = PropManager.getPropManager();

    /**
     * Retrieves a Good object from a JSON file.
     *
     * @param filename the name of the JSON file
     * @return a Good object parsed from the file
     */
    public static Good getGoodFromJsonFile(String filename) {
        String fileContent = getFileContent(filename);
        Good good = null;
        try {
            good = new ObjectMapper().readValue(fileContent, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return good;
    }

    /**
     * Writes a Good object to a JSON file.
     *
     * @param good the Good object to write
     * @param filename the name of the JSON file with extension
     */
    public static void writeGoodToJson(Good good, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(propManager.getProperty(PATH_TO_TEST_RESOURCES) + filename), good);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the content of a file as a string.
     *
     * @param fileName the name of the file  with extension
     * @return the content of the file as a string
     */
    public static String getFileContent(String fileName) {
        try {
            return Files.readString(getFullPath(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs the full path to a file based on its name.
     *
     * @param fileName the name of the file
     * @return the full path to the file
     */
    private static Path getFullPath(String fileName) {
        return Paths.get(propManager.getProperty(PATH_TO_TEST_RESOURCES), fileName).toAbsolutePath().normalize();
    }
}
