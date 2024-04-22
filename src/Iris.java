import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Iris {

    private final ArrayList<Double> attributes;
    public static ArrayList<Iris> allIrises = new ArrayList<>();
    private String type;


    public Iris(ArrayList<Double> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Double> getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }

    public int getAttributesAmount(){
        return attributes.size();
    }

    public static double calculateDistBetweenTwoPoints(Iris i1, Iris i2){
        double distance = 0;
        for(int i = 0; i < i1.getAttributes().size(); i++){
            distance = distance + Math.pow(i1.getAttributes().get(i) - i2.getAttributes().get(i), 2);
        }
        return Math.sqrt(distance);
    }

    @Override
    public String toString() {
        return "Iris{" +
                "attributes=" + attributes +
                ", type='" + type + '\'' +
                '}';
    }
}
