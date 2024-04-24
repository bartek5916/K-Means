import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Iris {
    private String type;
    private final ArrayList<Double> attributes;
    public static ArrayList<Iris> allIrises = new ArrayList<>();

    public Iris(ArrayList<Double> attributes) {
        this.attributes = attributes;
    }
    public Iris(ArrayList<Double> attributes, String type) {
        this.attributes = attributes;
        this.type = type;
    }


    public static double calculateDistBetweenTwoPoints(Iris i1, Iris i2){
        double distance = 0;
        for(int i = 0; i < i1.getAttributes().size(); i++){
            distance = distance + Math.pow(i1.getAttributes().get(i) - i2.getAttributes().get(i), 2);
        }
        return distance;
    }

    //Gettery
    public ArrayList<Double> getAttributes() {
        return attributes;
    }

    public int getAttributesAmount(){
        return attributes.size();
    }


    //Przesloniete metody
    @Override
    public int hashCode() {
        return this.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Iris centroid = (Iris) o;

        return this.getAttributes().equals(centroid.getAttributes());
    }

    @Override
    public String toString() {
        return "Iris - " +
                "Atrybuty: " + attributes +
                " Typ: " + type;
    }
}
