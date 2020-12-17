package Visualizer;

/**
 * Creates State object to store information pertaining to
 * state-specific COVID data from the input file
 * 
 * @author xdyer
 *
 */
public class State {
    
    private String sName;
    private int sCases;
    private int sDeaths;
    
    /**
     * Constructor for State object
     * 
     * @param name
     * @param cases
     * @param deaths
     */
    public State(String name, int cases, int deaths)
    {
        sName = name;
        sCases = cases;
        sDeaths = deaths;
    }
    
    /**
     * Getter method for state name
     * @return state name
     */
    public String getName() 
    {
        return sName;
    }
    
    /**
     * Getter method for state cases
     * @return number of cases
     */
    public int getCases() 
    {
        return sCases;
    }
    
    /**
     * Getter method for state deaths
     * @return number of deaths
     */
    public int getDeaths() 
    {
        return sDeaths;
    }

}
