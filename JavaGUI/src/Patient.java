import java.util.ArrayList;

public class Patient {
    private ArrayList<String> arr = new ArrayList<>();

    public String getelement(int i) {
        return arr.get(i);
    }

    public void addelement(String x){
        arr.add(x);
    }

    public int getsize(){
        return arr.size();
    }

}
