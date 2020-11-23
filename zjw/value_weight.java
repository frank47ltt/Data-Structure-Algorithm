//This class create a value_weight struct
    //and overload comparator operator to sort based on value

public class value_weight implements Comparable<value_weight> {
    private int value;
    private int weight;
    private int index;

    value_weight(int value, int weight, int index){
        this.value = value;
        this.weight = weight;
        this.index = index;
    }

    public int compareTo(value_weight a) {
        return a.value - this.value;
    }

    public int getValue(){
        return value;
    }

    public int getWeight(){
        return weight;
    }

    public int getIndex(){
        return index;
    }

}

