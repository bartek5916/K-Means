import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cluster{
    private static List<Cluster> clusters = new ArrayList<>();
    private List<Iris> clusterIrises;
    private static int nextId = 0;
    private int Id;
    private Iris centroid;
    public Cluster() {
        clusterIrises = new ArrayList<>();
        clusters.add(this);
        this.Id = ++nextId;
    }

    public static void createClusters(int k){
        for(int i = 0; i < k; i++){
            new Cluster();
        }
    }
    public static void assignIrisesRandomly(List<Iris> irises){
        for (Iris iris : irises) {
            int randomClusterNumber = ThreadLocalRandom.current().nextInt(0, clusters.size());

            clusters.get(randomClusterNumber).addIris(iris);
        }
    }

    public void updateCentroid(){
        if(clusterIrises.size() == 0){
            return;
        }

        //Sumowanie poszczególnych atrybutów w clustrze
        int attributesAmount = clusterIrises.get(0).getAttributesAmount();
        var sumOfEachAttributeValue = new ArrayList<Double>(Collections.nCopies(attributesAmount, 0.0));
        for(int i = 0; i < clusterIrises.size(); i++){
            for(int j = 0; j < attributesAmount; j++){
                double value = sumOfEachAttributeValue.get(j);
                value += clusterIrises.get(i).getAttributes().get(j);
                sumOfEachAttributeValue.set(j, value);
            }
        }

        //Dzielenie zsumowanych atrybutow wszystkich wektorow przez ilosc Irysow w clustrze
        for(int i = 0; i < sumOfEachAttributeValue.size(); i++){
            sumOfEachAttributeValue.set(i, sumOfEachAttributeValue.get(i)/clusterIrises.size());
        }

        this.centroid = new Iris(sumOfEachAttributeValue);
    }

    //Sumowanie odleglosci Irysow w clustrze od jego centroidu
    public double calculateCombinedDistance(){
        double distance = 0;
        for(Iris i : clusterIrises){
            distance += Iris.calculateDistBetweenTwoPoints(i, this.centroid);
        }

        return distance;
    }

    public void addIris(Iris iris){
        this.clusterIrises.add(iris);
    }


    //Gettery
    public int getId() {
        return Id;
    }
    public Iris getCentroid(){
        return this.centroid;
    }

    public static List<Cluster> getClusters() {
        return clusters;
    }


    public List<Iris> getClusterIrises() {
        return clusterIrises;
    }


    //Przesloniete metody

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Cluster cluster = (Cluster) o;
        return centroid.equals(cluster.centroid);
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

    @Override
    public String toString() {
        String irises = "";
        for (Iris clusterIris : clusterIrises) {
            irises = irises + clusterIris + "\n";
        }

        return "Cluster: " + this.Id + "\n" +irises;
    }
}
