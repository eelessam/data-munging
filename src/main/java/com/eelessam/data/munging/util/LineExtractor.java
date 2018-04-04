package com.eelessam.data.munging.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineExtractor {

    private LineExtractor() {

    }

    private static final Logger LOGGER = Logger.getLogger(LineExtractor.class.getName());

    public static List<String> extractLinesFromFile(String filePath, List<Integer> linesToExclude) {

        List<String> lines = new ArrayList<>();
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(new File(LineExtractor.class.getClassLoader().getResource(filePath).getFile())))) {

            String currentLine;

            while ((currentLine = lineNumberReader.readLine()) != null) {
                if (linesToExclude.contains(lineNumberReader.getLineNumber())) {
                    continue;
                }
                lines.add(currentLine);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File not found", e);
        }

        return lines;
    }
}
