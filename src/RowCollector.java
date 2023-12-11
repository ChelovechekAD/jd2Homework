
import java.util.List;

public class RowCollector implements Comparable<Object> {

    private final List<Object> line;

    public RowCollector(List<Object> line) {
        this.line = line;
    }

    public List<Object> getLine() {
        return line;
    }

    private int compareTwoElem(Object o, int pos) {
        var value1 = this.getLine().get(pos);
        var value2 = ((RowCollector) o).getLine().get(pos);
        if ((value1 instanceof Integer || value1 instanceof Double) && (value2 instanceof Integer || value2 instanceof Double)) {
            if (value1 instanceof Double || value2 instanceof Double) {
                return ((value1 instanceof Double) ? (Double) value1 : Double.valueOf((Integer) value1))
                        .compareTo((value2 instanceof Double) ? (Double) value2 : Double.valueOf((Integer) value2));
            } else return ((Integer) value1).compareTo((Integer) value2);
        } else if (value1 instanceof String && value2 instanceof String) {
            return ((String) value1).compareTo((String) value2);
        } else {
            if (value1 instanceof Integer || value1 instanceof Double) {
                return -1;
            } else if (value2 instanceof Integer || value2 instanceof Double) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        int pos = 0;
        int out = compareTwoElem(o, pos);
        while (out == 0 && pos < ConstValues.countOfColumnsForSortOnThem) { // Сортировка только по первым двум столбцам,
            // но можно убрать (&& pos < 2) и получить сортировку на основе всех столбцов.
            try {
                out = compareTwoElem(o, pos);
                pos++;
            } catch (IndexOutOfBoundsException e) {
                out = (pos >= this.line.size()) ? -1 : 1;
            }
        }
        return out;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (var i : this.line) {
            str.append(i).append(" ");
        }
        return str.toString();
    }
}