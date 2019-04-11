package uk.ac.ucl.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Model
{

  ArrayList<Patient> arr = new ArrayList();

  public void readFile(File csvFile){

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

  }

  public List<Patient> getPatientNames() {
    return arr;
  }

  public List<Patient> searchFor(String keyword) {

    List<Patient> result = new LinkedList<>();
    int arrsize = arr.size();
    String key = keyword.toLowerCase();

    for (int i=1; i<=arrsize-1; i++) {

      int itemsize = arr.get(i).getsize();

      for (int x = 0; x <= itemsize - 1; x++) {
        String ss = arr.get(i).getelement(x);
        String s = ss.toLowerCase();
        int index=0;

        for(int j=0; j<ss.length(); j++){
          if (key.charAt(index)==s.charAt(j)){
            String sub="";
            try{
              sub = s.substring(j, j+key.length());
            }catch(Exception e){}
            if (key.compareTo(sub)==0){
              result.add(arr.get(i));
            }
          }
        }
      }
    }

    return result;
  }

  public List<String> search(String keyword){

    List<String> result = new LinkedList<>();
    int arrsize = arr.size();
    String key = keyword.toLowerCase();

    for (int i=1; i<=arrsize-1; i++) {

        int itemsize = arr.get(i).getsize();

        for (int x = 0; x <= itemsize - 1; x++) {
          String ss = arr.get(i).getelement(x);
          String s = ss.toLowerCase();
          int index = 0;

          for (int j = 0; j < ss.length(); j++) {
            if (key.charAt(index) == s.charAt(j)) {
              String sub = "";
              try {
                sub = s.substring(j, j + key.length());
              } catch (Exception e) {
              }
              if (key.compareTo(sub) == 0) {
                result.add(ss);
              }
            }
          }
        }
      }
    return result;
  }

  public List<String> search_name(String keyword) {
    List<String> result = new LinkedList<>();
    int arrsize = arr.size();
    String key = keyword.toLowerCase();

    for (int i = 1; i <= arrsize - 1; i++) {

      String Name = arr.get(i).getelement(7) + arr.get(i).getelement(8);
      String name = Name.toLowerCase();
      int index = 0;

      for (int j = 0; j < name.length(); j++) {
        if (key.charAt(index) == name.charAt(j)) {
          String sub = "";
          try {
            sub = name.substring(j, j + key.length());
          } catch (Exception e) {
          }
          if (key.compareTo(sub) == 0) {
            result.add(Name);
          }
        }
      }
    }
    return result;
  }

  public List<String> search_age(String keyword1, String keyword2){

    List<String> result = new LinkedList<>();
    int arrsize = arr.size();
    int lower=0;
    int upper=0;
    String key1 = keyword1.toLowerCase();
    String key2 = keyword2.toLowerCase();

    if (key1!=""){
      try {
        lower = Integer.valueOf(key1);
      }catch (Exception e){}
    }

    if (key2!=""){
      try {
        upper = Integer.valueOf(key2);
      }catch (Exception e){}
    }

    if (lower>upper){
         int x = upper;
         upper = lower;
         lower = x;
       }


    if (lower!=upper){
      for (int i = 1; i <= arrsize - 1; i++) {
        int age = 2019-Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
        if ((age<=upper)&&(age>=lower)){
          result.add(arr.get(i).getelement(7)+" "+arr.get(i).getelement(8)+": "+age);
        }
      }
    }
    else { for (int i = 1; i <= arrsize - 1; i++) {
        int age = 2019-Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
        if ((age==lower)|(age==upper)){
          result.add(arr.get(i).getelement(7)+" "+arr.get(i).getelement(8)+": "+age);
        }
      }
    }

    return result;
  }

  public List<Patient> searchfor_age(String keyword1, String keyword2){

    List<Patient> result = new LinkedList<>();
    int arrsize = arr.size();
    int lower=-1;
    int upper=-1;
    String key1 = keyword1.toLowerCase();
    String key2 = keyword2.toLowerCase();

    if (key1!=""){
      try {
        lower = Integer.valueOf(key1);
      }catch (Exception e){}
    }

    if (key2!=""){
      try {
        upper = Integer.valueOf(key2);
      }catch (Exception e){}
    }

    if (lower>upper){
      int x = upper;
      upper = lower;
      lower = x;
    }


    if (lower!=upper){
      for (int i = 1; i <= arrsize - 1; i++) {
        int age = 2019-Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
        if ((age<=upper)&&(age>=lower)){
          result.add(arr.get(i));
        }
      }
    }
    else {
      for (int i = 1; i <= arrsize - 1; i++) {
        int age = 2019 - Integer.valueOf(arr.get(i).getelement(1).substring(0, 4));
        if (age==upper) {
          result.add(arr.get(i));
        }
      }
    }
    return result;
  }

  public List<Patient> getyoungest(){
    int youngest=0;
    List<Patient> p = new LinkedList<>();

    for(int i=1; i<arr.size(); i++){

      int year = Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
      if (youngest==year){
        p.add(arr.get(i));
      }
      if (youngest<year){
        youngest=year;
        p.clear();
        p.add(arr.get(i));
      }

    }
//    System.out.println(p.size());
    return p;
  }

  public List<Patient> getoldest(){
    int oldest=2019;
    List<Patient> p = new LinkedList<>();

    for(int i=1; i<arr.size(); i++){

      int year = Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
      if (oldest==year){
        p.add(arr.get(i));
      }
      if (oldest>year){
        oldest=year;
        p.clear();
        p.add(arr.get(i));
      }

    }
//    System.out.println(p.size());
    return p;
  }

  public String getaverage(){
    double average=0;
    for(int i=1; i<arr.size(); i++) {
      average = average + 2019-Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
    }
    average = average / (arr.size()-1);
    return String.format("%.2f", average);
  }

  public int[] getrange(){
    int[] range = new int[15];
    for (int i=1; i<arr.size(); i++){
      int year = Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
//      System.out.println((2019-year)/10);
      range[(2019-year)/10]++;
    }
//    for (int i=0; i<18; i++){
//      System.out.println(i+": "+range[i]);
//    }
    return range;
  }

}

