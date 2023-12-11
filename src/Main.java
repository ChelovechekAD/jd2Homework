import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static List<List<Object>> sort(List<List<Object>> array) {
        List<RowCollector> arrayRowCollector = new ArrayList<>();

        for (var line : array) {
            arrayRowCollector.add(new RowCollector(line));
        }
        Collections.sort(arrayRowCollector);

        List<List<Object>> finalArray = new ArrayList<>();
        arrayRowCollector.forEach(elem -> finalArray.add(elem.getLine()));
        return finalArray;
    }

    public static void main(String[] args) {

        List<List<Object>> array = FileManager.read();

        //Для проверки типов в двумерке.
        for (var i : array) {
            for (var j : i) {
                System.out.print(j.getClass() + " ");
            }
            System.out.println();
        }

        array = sort(array);

        FileManager.write(array);
    }
}