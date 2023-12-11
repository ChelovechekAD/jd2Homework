import java.util.ArrayList;

public class RowCollector implements Comparable<Object>{

    ArrayList<Object> line;

    public RowCollector(ArrayList<Object> line){
        this.line = line;
    }

    public ArrayList<Object> getLine() {
        return line;
    }

    private int compareTwoElem(Object o, int pos){
        var value1 = this.getLine().get(pos);
        var value2 = ((RowCollector)o).getLine().get(pos);
        if (value1 instanceof Integer && value2 instanceof Integer){
            return ((Integer)value1).compareTo((Integer)value2);
        } else if (value1 instanceof String && value2 instanceof String) {
            return ((String)value1).compareTo((String) value2);
        } else {
            if (value1 instanceof Integer){
                return 1;
            } else if (value2 instanceof Integer) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        int pos = 0;
        int out = compareTwoElem(o, pos);
        while (out == 0 && pos < 2){ // Сортировка только по первым двум столбцам,
            // но можно убрать (&& pos < 2) и получить сортировку на основе всех столбцов.
            try {
                out = compareTwoElem(o, pos);
                pos++;
            }catch (IndexOutOfBoundsException e){
                out = (pos >= this.line.size()) ? -1 : 1;
            }
        }
        return out;
    }

    @Override
    public String toString() {
        String str = "";
        for (var i : this.line){
            str += i + " ";
        }
        return str;
    }
}