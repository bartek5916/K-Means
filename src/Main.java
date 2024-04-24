import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wartosc k: ");
        int k = scanner.nextInt();


        Cluster.createClusters(k);
        readFile("Data/Iris.txt");
        Cluster.assignIrisesRandomly(Iris.allIrises);

        ArrayList<Iris> previousCentroids = new ArrayList<>();
        ArrayList<Iris> currentCentroids = new ArrayList<>();

        while(true){
            previousCentroids = new ArrayList<>(currentCentroids);

            for(Cluster c :  Cluster.getClusters()){
                c.updateCentroid();
                System.out.println("Suma dystansów od centroidow clustrze " + c.getId() + " : " + c.calculateCombinedDistance());
            }

            System.out.println("-----------------------------------------------------------");

            for(Iris i : Iris.allIrises){
                int newClusterId = getNearestCentroid(Cluster.getClusters(), i);

                for(Cluster c : Cluster.getClusters()){
                    c.getClusterIrises().remove(i);

                    if(c.getId() == newClusterId){
                        c.addIris(i);
                    }
                }
            }

            currentCentroids = assignCentroids(Cluster.getClusters());

            if(currentCentroids.equals(previousCentroids)){
                break;
            }
        }

        for(Cluster c :  Cluster.getClusters()){
            System.out.println("Centroid: " + c.getCentroid());
            System.out.println(c);
        }

    }

    public static void readFile(String fileName) {
        String line;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fileName));

            while ((line = bf.readLine()) != null) {
                String[] arr = line.replace(",", " ").trim().split("\\s+");
                ArrayList<Double> attributes = new ArrayList<>();

                for (int i = 0; i < arr.length - 1; i++) {
                    attributes.add(Double.parseDouble(arr[i]));
                }

                String type = arr[arr.length - 1];
                Iris.allIrises.add(new Iris(attributes, type));
            }
        } catch (Exception e) {
            System.out.println("Nie znaleziono pliku");
        }
    }

    public static int getNearestCentroid(List<Cluster> clusters, Iris i){
        double distance = -1;
        int clusterId = -1;
        for(Cluster c : clusters){

            double distanceToCentroid = Iris.calculateDistBetweenTwoPoints(i,c.getCentroid());
            if(distance < 0 || distanceToCentroid < distance){
                clusterId = c.getId();
                distance = distanceToCentroid;
            }
        }
        return clusterId;
    }

    public static ArrayList<Iris> assignCentroids(List<Cluster> clusters){
        ArrayList<Iris> centroids = new ArrayList<>();

        for(Cluster c : clusters){
            centroids.add(c.getCentroid());
        }

        return centroids;
    }

}