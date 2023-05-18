package graphvisualizer;

import java.util.List;

public class DataProcessor {
    private static Boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static double[][] processData(List<String> lines) {
        double[][] data = new double[2][lines.size()];
        int i = 0;
        for (String line : lines) {
            String[] values = line.split(",");
            if(values.length > 1) {
                if(isDouble(values[0]) && isDouble(values[1])) {
                    data[0][i] = Double.parseDouble(values[0].trim());
                    data[1][i] = Double.parseDouble(values[1].trim());
                    i++;
                }
            }
        }
        return data;
    }
}
