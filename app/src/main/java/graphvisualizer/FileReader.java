package graphvisualizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class FileReader {
    List<String> lines;
    
    public FileReader(String path) {
        try {
            Path filepath = Path.of(path);
            lines = Files.readAllLines(filepath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getLines() {
        return lines;
    }
    
    public Boolean writeToFile(String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            // Iterate over the lines and write them to the file
            for (String line : lines) {
                writer.write(line);
                writer.newLine(); // Add a new line after each line
            }
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
        return false;
    }
}
