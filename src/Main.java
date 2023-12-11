import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static ArrayList<ArrayList<Object>> sort(ArrayList<ArrayList<Object>> array){
        ArrayList<RowCollector> arrayRowCollector = new ArrayList<>();

        for (var line : array){
            arrayRowCollector.add(new RowCollector(line));
        }
        Collections.sort(arrayRowCollector);

        ArrayList<ArrayList<Object>> finalArray = new ArrayList<>();
        arrayRowCollector.forEach(elem -> finalArray.add(elem.getLine()));
        return finalArray;
    }

    public static void main(String[] args){

        ArrayList<ArrayList<Object>> array = FileManager.read();

        for (var i : array){
            for(var j : i){
                System.out.print(j.getClass() + " ");
            }
            System.out.println();
        }

        array = sort(array);

        FileManager.write(array);
    }
}