class Box1<T extends Number> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public double doubleValue() {
        return value.doubleValue();
    }
}

public class boundedgenerics {
    public static void main(String[] args) {
        Box1<Integer> intBox = new Box1<>();
        intBox.setValue(100);
        System.out.println("Integer Value: " + intBox.getValue());
        System.out.println("Double Value: " + intBox.doubleValue());

        Box1<Double> doubleBox = new Box1<>();
        doubleBox.setValue(45.67);
        System.out.println("Double Value: " + doubleBox.getValue());
        System.out.println("Double Value (converted): " + doubleBox.doubleValue());

        // Box<String> strBox = new Box<>(); // Not allowed (String is not a subclass of Number)
    }
}
