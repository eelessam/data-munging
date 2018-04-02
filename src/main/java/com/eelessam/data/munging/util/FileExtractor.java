package com.eelessam.data.munging.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileExtractor {

    private FileExtractor() {

    }

    private static final Logger LOGGER = Logger.getLogger(FileExtractor.class.getName());

    public static List<String> readInFile(String filePath) {

        List<String> lines = new ArrayList<>();
        try (BufferedReader lineNumberReader = new BufferedReader(new FileReader(new File(FileExtractor.class.getClassLoader().getResource(filePath).getFile())))) {

            String currentLine;

            while ((currentLine = lineNumberReader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File not found", e);
        }

        return lines;
    }
}
