package util;

import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelper {

    public String sketchPath( String fileName ) {
        return "assets/audio/"+fileName;
    }

    public InputStream createInput(String fileName) {
        InputStream is = null;
        try{
            is = new FileInputStream(sketchPath(fileName));
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return is;
    }
}