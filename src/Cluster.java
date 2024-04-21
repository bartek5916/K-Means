import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cluster{
    private static List<Cluster> clusters = new ArrayList<>();
    private List<Iris> clusterIrises;
    private static int nextId = 1;
    private int Id;
    public Cluster() {
        clusterIrises = new ArrayList<>();
        clusters.add(this);
        this.Id = nextId++;
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

    public void addIris(Iris iris){
        this.clusterIrises.add(iris);
    }

    public static List<Cluster> getClusters() {
        return clusters;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return Id;
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
