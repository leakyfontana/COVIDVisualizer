package Visualizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads and stores data from the CSV input file to 
 * an ArrayList of State objects
 * @author xdyer
 *
 */
public class DataReader {
    
    private ArrayList<State> states;
    private File input;
    
    /**
     * Constructor for the DataReader object
     * @param fName File Name
     */
    public DataReader(String fName)
    {
        states = new ArrayList<State>();
        input = new File(fName);
    }
    
    /**
     * Reads input file given specified format of three columns
     * (state name, number of cases, number of deaths) and stores
     * data in an ArrayList of State objects
     * 
     * @return ArrayList of State objects
     * @throws IOException when Input file is null or formatted
     * incorrectly
     */
    public ArrayList<State> readData() throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(input));
        String line = "";
        while ((line = in.readLine()) != null)
        {
            if(line.length() > 0)
            {
                String[] columns = line.split(",");
                String nm = columns[0];
                int cas = Integer.parseInt(columns[1]);
                int death = Integer.parseInt(columns[2]);
                State temp = new State(nm, cas, death);
                states.add(temp);
            }
        }
        in.close();
        return states;
    }

}
