import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV{

    public ArrayList<Patient> run(String path){

        ArrayList<Patient> arr = new ArrayList();
        String csvFile = path;
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null)
            {
                String[] country = line.split(cvsSplitBy);
                Patient p = new Patient();
                for(String i : country) {
                    p.addelement(i);
                }
                arr.add(p);
            }

        }
        catch (IOException e) { e.printStackTrace(); }

        return arr;
    }

}