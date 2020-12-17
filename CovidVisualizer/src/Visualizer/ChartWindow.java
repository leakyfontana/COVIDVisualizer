package Visualizer;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Provides UI for the visualization of State COVID data
 * utilizing Javafx package
 * 
 * @author xanderdyer
 *
 */
public class ChartWindow extends Application {
    
    private ArrayList<State> stateGroup;
    private DataReader ref;
    private final static double radius = 2000;
    private final static int horz = 25;
    private final static int vert = 25;
    
    /**
     * Draws a circle, arc and text node for the state 
     * object given in parameter
     * @param state
     * @return a group of visualization nodes for state
     * from input
     */
    public Group drawState(State state)
    {
        double iRate;
        double dRate;
        double iTemp;
        double dTemp;
        double tInfect = 0;
        for (State s: stateGroup)
        {
            tInfect += s.getCases();
        }
        iTemp = state.getCases();
        dTemp = state.getDeaths();
        iRate = iTemp / tInfect;
        dRate = dTemp / iTemp;
        Circle rep = new Circle(iRate * radius, Color.BLUE);
        String output = String.format("%s: %.1f%%\n%s: %.1f%%\n%s: %d", 
            "Infection Rate", iRate * 100, "Death Rate", dRate * 100, "Total"
                + " Infections", state.getCases());
        Tooltip t = new Tooltip(output);
        Tooltip.install(rep, t);
        Arc arc = new Arc(0, 0, rep.getRadius(), rep.getRadius(), 0, 
            dRate * 360);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.RED);
        arc.toFront();
        Text text = new Text(state.getName());
        text.setX(0 - (state.getName().length() * 4));
        text.setY(15 + (iRate * radius));
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Group group = new Group(rep, text, arc);
        return group;        
    }

    /**
     * Provides input file, reads data and adds nodes in format 
     * specified for the final UI visualization product
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ref = new DataReader("/Users/xanderdyer/Desktop/data_11_09.csv");
        stateGroup = ref.readData();
        FlowPane flow = new FlowPane();
        flow.setHgap(horz);
        flow.setVgap(vert);
        ObservableList<Node> list = flow.getChildren();
        //Loops through List of State objects calling drawState method
        for (State s: stateGroup)
        {
            list.addAll(drawState(s));
        }
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(flow);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.getScene();
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("COVID-19 Visualization");
        primaryStage.show();   
    }
    
    /**
     * Launches UI for visualization
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }

}
