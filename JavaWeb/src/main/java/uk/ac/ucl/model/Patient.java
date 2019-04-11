package uk.ac.ucl.model;

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

    public String getid(){
        return arr.get(0);
    }

    public String getname(){
        return (arr.get(7)+" "+arr.get(8));
    }

    public int getage(){
        return 2019-Integer.valueOf(arr.get(1).substring(0,4));
    }
}