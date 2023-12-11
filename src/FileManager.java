import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    @Deprecated
    public static Object converterInputString(String str) {

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException ex) {
                return str;
            }
        }
    }

    public static Object converterInputStringOnRegEx(String str) {
        return (str.matches("^[+-]?\\d+$")) ? Integer.parseInt(str) :
                str.matches("^[+-]?(\\d+([.]\\d*)?|[.]\\d+)$") ? Double.parseDouble(str) : str;

    }

    public static List<List<Object>> read() {
        try (FileReader reader = new FileReader(ConstValues.fileInPath)) {
            Scanner sc = new Scanner(reader);
            List<List<Object>> array = new ArrayList<>();
            while (sc.hasNextLine()) {
                Scanner line = new Scanner(sc.nextLine());
                List<Object> arrLine = new ArrayList<>();
                while (line.hasNext()) {
                    String lineVal = line.next();
                    arrLine.add(converterInputStringOnRegEx(lineVal));
                }
                array.add(arrLine);
                line.close();
            }
            return array;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(List<List<Object>> array) {
        try (FileWriter writer = new FileWriter(ConstValues.fileOutPath)) {
            for (var line : array) {
                for (var elem : line) {
                    writer.write(elem + "\t");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
