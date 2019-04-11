import java.io.*;
import java.util.ArrayList;

public class JSON {

    public File outputpatients(ArrayList<Patient> arr) throws FileNotFoundException, UnsupportedEncodingException {

        File f = new File("patientsfile");
        PrintWriter writer = new PrintWriter(f);
        int arrsize = arr.size();
        System.out.println("patients:"+arrsize);

        writer.println("{");
        writer.println("   \"patients\": [");

        for (int i=1; i<=arrsize-1; i++){
            int itemsize = arr.get(i).getsize();
            boolean lastempty=false;

            writer.println("     {");

            if (itemsize==arr.get(0).getsize()){
                for(int x=0; x<itemsize; x++){
                    writer.print("       ");
                    writer.print("\""+arr.get(0).getelement(x)+"\""+": "); // output title
                    writer.print("\""+arr.get(i).getelement(x)+"\""); // output content
                    if (x<itemsize-1) { writer.println(","); } else {writer.println();}
                }
                if(i==arrsize-1) { writer.println("     }"); }else {writer.println("     },");}
            } //check if the last on is empty
            else {
                for(int x=0; x<itemsize; x++){
                    writer.print("       ");
                    writer.print("\""+arr.get(0).getelement(x)+"\""+": "); // output title
                    writer.print("\""+arr.get(i).getelement(x)+"\""); // output content
                    writer.println(",");
                }
                writer.print("       ");
                writer.println("\""+arr.get(0).getelement(itemsize)+"\""+": \"\""); // output title
                if(i==arrsize-1) { writer.println("     }"); }else {writer.println("     },");}
            }


        }

        writer.println("  ]");
        writer.println("}");
        writer.close();

        return f;

    }

    public File outputpatient(ArrayList<Patient> arr, int index) throws IOException {

        File f = new File("patientfile");
        PrintWriter writer = new PrintWriter(f);

        int itemsize = arr.get(index).getsize();

        writer.println("{");
        writer.println("   \"patients\": [");
        writer.println("     {");

        if (itemsize==arr.get(0).getsize()){
            for(int x=0; x<=itemsize-1; x++){
                writer.print("       ");
                writer.print("\""+arr.get(0).getelement(x)+"\""+": "); //get title
                writer.print("\""+arr.get(index).getelement(x)+"\"");
                if (x<itemsize-1){writer.println(",");}else {writer.println("");}
            }
        }
        else {
            for(int x=0; x<=itemsize-1; x++){
                writer.print("       ");
                writer.print("\""+arr.get(0).getelement(x)+"\""+": "); //get title
                writer.print("\""+arr.get(index).getelement(x)+"\"");
                writer.println(",");
            }
            writer.print("       ");
            writer.println("\""+arr.get(0).getelement(itemsize)+"\""+": \"\""); // output title
        }


        writer.println("     }");
        writer.println("  ]");
        writer.println("}");
        writer.close();

        return f;
    }

    public String outputname(ArrayList<Patient> arr, int index){
        return (arr.get(index).getelement(7) +" "+ arr.get(index).getelement(8));
    }

    public File search(ArrayList<Patient> arr, String keyx) throws FileNotFoundException {

        int arrsize = arr.size();
        boolean found =false;
        String key = keyx.toLowerCase();

        File f = new File("searchfile");
        PrintWriter writer = new PrintWriter(f);

        writer.println("key: "+keyx);
        writer.println("");

        for (int i=1; i<=arrsize-1; i++) {
            int itemsize = arr.get(i).getsize();

            for (int x = 0; x <= itemsize - 1; x++) {

                String sx = arr.get(i).getelement(x);
                String s = sx.toLowerCase();

                for(int j=0; j<sx.length(); j++) {
                    if (key.charAt(0) == s.charAt(j)) {

                        String sub = "";
                        try {
                            sub = s.substring(j, j + key.length());
                        } catch (Exception e) {
                        }
                        if (key.compareTo(sub) == 0) {
                            writer.println(s);
                            found = true;
                        }
                    }
                }
            }
        }

        if (!found){
            writer.println("not found");
        }

        writer.close();
        return f;
    }

    public File statistics(ArrayList<Patient> arr) throws FileNotFoundException {

        File f = new File("statisfile");
        PrintWriter writer = new PrintWriter(f);
        int arrsize = arr.size();
        int[] distirbution = new int[2019];


        double sum=0;
        writer.print("average age: ");
        for (int i=1; i<arrsize; i++) {
            int year = Integer.valueOf(arr.get(i).getelement(1).substring(0,4));
            sum = sum + 2019-year;
            distirbution[year]++;
        }
        writer.println(sum/arrsize);
        writer.println();

        writer.println("birth year distribution: ");
        for (int i=1800; i<2019; i++){
            if(distirbution[i]>=1){
                writer.println(i+": "+distirbution[i]);
            }
        }

        writer.close();
        return f;
    }

}
