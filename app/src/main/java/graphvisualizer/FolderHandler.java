package graphvisualizer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderHandler {
    static List<String> getAllFilesInFolder(String path) {
        File folder = new File(path);
        List<String> fileList = new ArrayList<String>();
        
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // Process each file
                    fileList.add(file.getName());
                }
            }
        }
        return fileList;
    }
}
