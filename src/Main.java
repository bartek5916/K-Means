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

        for(Cluster c :  Cluster.getClusters()){
            System.out.println(c);
            c.updateCentroid();
            System.out.println(c.getCentroid());
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
                Iris.allIrises.add(new Iris(attributes));
            }
        } catch (Exception e) {
            System.out.println("Nie znaleziono pliku");
        }
    }

}