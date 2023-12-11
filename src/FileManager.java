import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static ArrayList<ArrayList<Object>> read(){
        try (FileReader reader = new FileReader("src/in.txt")) {
            Scanner sc = new Scanner(reader);
            ArrayList<ArrayList<Object>> array = new ArrayList<>();
            while (sc.hasNextLine()) {
                Scanner line = new Scanner(sc.nextLine());
                ArrayList<Object> arrLine = new ArrayList<>();
                while (line.hasNext()){
                    String lineVal = line.next();
                    try {
                        arrLine.add(Integer.parseInt(lineVal));
                    }catch (NumberFormatException e){
                        try{
                            arrLine.add(Double.parseDouble(lineVal));
                        }catch (NumberFormatException ex) {
                            arrLine.add(lineVal);
                        }
                    }
                }
                array.add(arrLine);
                line.close();
            }
            reader.close();
            sc.close();
            return array;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(ArrayList<ArrayList<Object>> array){
        try (FileWriter writer = new FileWriter("src/out.txt")){
            for (var line : array){
                for (var elem : line){
                    writer.write(elem + "\t");
                }
                writer.write("\n");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
