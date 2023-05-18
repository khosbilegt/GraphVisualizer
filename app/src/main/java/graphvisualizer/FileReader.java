package graphvisualizer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
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
}
