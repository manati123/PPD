import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    public static Integer nrRowsFirstMatrix;
    public static Integer nrColumnsFirstMatrix;
    public static Integer nrColumnsSecondMatrix;
    public static String fType;
    public static String scanType;
    public static Integer taskNr;

    private static int getIndex(String line) {
        int index;
        index = 0;
        while(index < line.length() && !(Character.isSpaceChar(line.charAt(index)) && line.charAt(index - 1) == '=')) {
            index++;
        }
        index++;
        return index;
    }

    public static void readParamsFromFile() throws RuntimeException {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/data.txt"));
            String line;
            int index;

            line = reader.readLine();
            index = getIndex(line);
            nrRowsFirstMatrix = 0;
            while(index < line.length()) {
                nrRowsFirstMatrix = nrRowsFirstMatrix * 10 + (line.charAt(index) - '0');
                index++;
            }

            line = reader.readLine();
            index = getIndex(line);
            nrColumnsFirstMatrix = 0;
            while(index < line.length()) {
                nrColumnsFirstMatrix = nrColumnsFirstMatrix * 10 + (line.charAt(index) - '0');
                index++;
            }

            line = reader.readLine();
            index = getIndex(line);
            nrColumnsSecondMatrix = 0;
            while(index < line.length()) {
                nrColumnsSecondMatrix = nrColumnsSecondMatrix * 10 + (line.charAt(index) - '0');
                index++;
            }

            line = reader.readLine();
            index = getIndex(line);
            fType = "";
            while(index < line.length()) {
                fType += line.charAt(index);
                index++;
            }
            if(!fType.equals("task") && !fType.equals("pool")) {
                throw new RuntimeException("The function type is wrong!");
            }

            line = reader.readLine();
            index = getIndex(line);
            scanType = "";
            while(index < line.length()) {
                scanType = scanType + line.charAt(index);
                index++;
            }
            if(!scanType.equals("col") && !scanType.equals("row") && !scanType.equals("kth")) {
                throw new RuntimeException("The scan type is wrong!");
            }

            line = reader.readLine();
            index = getIndex(line);
            taskNr = 0;
            while(index < line.length()) {
                taskNr = taskNr * 10 + (line.charAt(index) - '0');
                index++;
            }

            reader.close();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}
