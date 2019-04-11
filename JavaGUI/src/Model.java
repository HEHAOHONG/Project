import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Model {
    private ArrayList<Patient> arr; //array to hold Patient

    public void read(String path){

        ReadCSV obj = new ReadCSV();
        arr=obj.run(path); //load to array
    }

    public File getpatients() throws FileNotFoundException, UnsupportedEncodingException {

        JSON obj = new JSON();
        return obj.outputpatients(arr);
    }

    public File getpatient(int index) throws IOException {

        JSON obj = new JSON();
        return obj.outputpatient(arr,index);  //chose a patient
    }

    public File search(String key) throws FileNotFoundException {

        JSON obj = new JSON();
        return obj.search(arr, key);
    }

    public File getstatistics() throws FileNotFoundException {

        JSON obj = new JSON();
        return obj.statistics(arr);
    }

    public String getname(int index){

        JSON obj = new JSON();
        return obj.outputname(arr, index);
    }

    public int getsize(){
        return arr.size();
    }

}
