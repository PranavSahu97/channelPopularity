package channelpopularity.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Result class to store the rotated words
 * @author
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    /**
     * ArrayList to store each sentence after performing rotation on all words in the sentence
     *
     */
    private ArrayList<String> result= new ArrayList<>();
    private File output=null;

    public Results(String outfilepath){
        output= new File(outfilepath);
    }
    @Override
    public void writeToFile(String str) {
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(output));
            br.append(str);
            br.newLine();

            /*for(String res: result){
                br.append(res);
                br.newLine();
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes to the console
     *
     */

    @Override
    public void writeToStdout(String s)
    {
        System.out.println(s + " \n");
    }


    public void storeNewResult(String res)
    {
        result.add(res);
        writeToStdout(res);
    }

}

