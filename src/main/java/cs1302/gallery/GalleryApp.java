package cs1302.gallery;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.image.*;

//import javafx.event.ActionEvent;
import javafx.event.*;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ContentDisplay;

//import javafx.scene.control.*;

import java.net.URL;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

//import javafx.scene.*;
//import javafx.stage.*;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
//import javafx.animation.*;

import javafx.util.Duration;
//import javafx.util.*;

import java.time.Duration.*;
//import javafx.event.*;

/**
 * Handles the creation of the app, and all of its basic functions.
 * 
 * @author Mary Brown
 *
 */
public class GalleryApp extends Application {
	
    BorderPane borderPane = new BorderPane();
    public CenterPane flow = null;  //??
    public ProgressBar p1 = new ProgressBar();
    public TextField searchBar = null;
  
    
    final Timeline timeline = new Timeline();
    //    
    //ImageGallery imgGal = new ImageGallery();   //?? StackOverFlow HERE ??
    ImageGallery imgGal;


    Button cmbUpdate = new Button();
    Shuffler cmbPause;

    //CenterFrame canvas;

    @Override
    public void start(Stage stage) {
	
	flow = addFlow();
	

	if (flow == null) System.out.println("I AM NULL.");
	//borderPane.setCenter(addFlow()); 
	borderPane.setCenter(flow);
	cmbPause = new Shuffler(flow);
	borderPane.setTop(addVBox(makeMenu(), makeToolBar()));
	

	borderPane.setBottom(addHBox(p1));
	borderPane.setMinWidth(500); 
	borderPane.setMinHeight(490); 
	
	System.out.println("HERE.");
	if (flow == null) System.out.println("I am null.");

	if (flow != null) {
	    System.out.println("HEREE.");

	imgGal = new ImageGallery(flow);
	

	if (imgGal != null)
	    {
		System.out.println("HEREEE.");
		//if (flow == null) System.out.println("I am null.");
		imgGal.initDisplay(flow, borderPane, this);
		System.out.println("HEREEEE. - SCREEN DISPLAYED");
		borderPane.setCenter(flow);
	
		//canvas = new CenterPane(stage, this); //handles all actions of centerPane
		//
		//imgGal = new ImageGallery();
		//flow.getChildren().add(imgGal);
		
		cmbUpdate.setOnAction(actionEvent ->
				      {
					  //imgGal.clearImages(flow);
					  flow.readInput(flow.getUserInput(searchBar), imgGal, borderPane, this, flow);
					  
					  borderPane.setCenter(flow);
					  //borderPane.setBottom(addHBox(p1));
				      });
		
		// cmbPause.setOnAction(actionEvent -> 
	    // 			     {
	    // 				 Thread t = new Thread(() -> {
	    // 					 if (cmbPause.getText().equals("Play"))
	    // 					     {
							 
	    // 						 Platform.runLater(() ->  {
	    // 							 cmbPause.setText("Pause");
	    // 							 flow.getChildren().add(cmbPause);
	    // 							 borderPane.setCenter(flow);
	    // 						     });
	    // 						 EventHandler<ActionEvent> shuffle = event -> cmbPause.shuffleImages(imgGal.getGallery(), flow.getArray(), flow);
	    // 						 startTimer(shuffle);
	    // 						 cmbPause.setOnAction(shuffle);
	    // 						 Platform.runLater(() -> borderPane.setCenter(flow));
 	
	    // 					     }
	    // 					 else if (cmbPause.getText().equals("Pause"))
	    // 					     {
	    // 						 cmbPause.setText("Play");
	    // 						 cmbPause.setOnAction(event -> stopTimer());
	    // 						 Platform.runLater(() -> borderPane.setCenter(flow));
	    // 					     }
	    // 				     });
					 
	    // 				 t.setDaemon(true);
	    // 				 t.start();
	    // 			     });
	    }
	//catch (java.lang.NullPointerException a)
	else
	    {
		System.out.println("ERROR: imgGal is still NULL.");
		Platform.exit();
		System.exit(0);
	    } // else
	}// if

	Scene scene = new Scene(borderPane);
	stage.setMaxWidth(800);
	stage.setMaxHeight(800);
	stage.setTitle("Gallery");
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
		
    }// start
    
	
    public static void main(String[] args) {
	
	try
	    {
		Application.launch(args);
	    }
	catch (UnsupportedOperationException e) 
	    {
		System.out.println(e);
		System.err.println("If this is a DISPLAY problem, then your X server connection");
		System.err.println("has likely timed out. This can generally be fixed by logging");
		System.err.println("out and logging back in.");
		System.exit(1);
	    } // try
    } //main
    
    /**
     * Creates the node resembling a vertical box.
     * 
     * @param m  a MenuBar
     * @param t  a ToolBar
     * @return   the created and assembled VBox
     */
    public VBox addVBox(MenuBar m, ToolBar t) 
    {
	VBox uiBar = new VBox(m, t);
	uiBar.setStyle("-fx-background-color: lightsteelblue;");
	return uiBar;
    }// addVBox
    
    
    
    /**
     * Creates the node resembling a vertical box.
     * 
     * @param p1  a ProgressBar
     * @return    the created and assembled VBox
     */
    public HBox addHBox(ProgressBar p1)
    {
    	Label courtesy = new Label(" Images provided courtesy of iTunes");
    	HBox bottom = new HBox(p1, courtesy);
	bottom.setStyle("-fx-background-color: lightsteelblue;");
	return bottom;
    }// addBVBox
    
    /**
     * Creates the control resembling a flowpane. Calls on CenterPane class.
     * 
     * @return the created and assembled flowpane.
     */
    public CenterPane addFlow()
    {
	if (flow != null)
	    {
		flow = new CenterPane(borderPane, this, imgGal, p1);
		System.out.println("Made not null");
	    }
	else
	    {
		//CenterPane flow = new CenterPane(borderPane, this, imgGal, p1);
		flow = new CenterPane(borderPane, this, imgGal, p1);
		System.out.println("Here.");
	    }
    	return flow;
    }// addFlow
    
    /**	
     * Creates the control resembling a toolbar.
     * 
     * @return the created and assembled toolbar.
     */
    public ToolBar makeToolBar() 
    {
    	cmbPause.setText("Play");
    	Separator sep = new Separator(); 
    	sep.setStyle("-fx-border-style: solid;");
    	sep.setStyle("-fx-border-width: 1px");
    	cmbUpdate.setText("Update Images");
    	searchBar = new TextField();
    	Label searchLabel = new Label("Search Query: ");
    	searchLabel.setContentDisplay(ContentDisplay.TEXT_ONLY);
    	ToolBar toolbar = new ToolBar(cmbPause, sep, searchLabel, searchBar, cmbUpdate);
	
	return toolbar;
    }// makeToolBar

    /**
     * Creates the control resembling a menubar.
     * 
     * @return the created and assembled menubar.
     */
    public MenuBar makeMenu() 
    {
    	Menu mfile = new Menu("File");
    	MenuItem exitItem = new MenuItem("Exit");
    	exitItem.setOnAction(event -> {
    		Platform.exit();
    		System.exit(0);
	    	});
    	mfile.getItems().add(exitItem);

	// Menu mhelp = new Menu("Help");
	// MenuItem about = new MenuItem("About");
	// about.setOnAction(event ->{
	// 		  showAboutPage();
	//     } );
	// mhelp.getItems().add(about);

	MenuBar menubar = new MenuBar(mfile);
	//MenuBar menubar = new MenuBar(mfile, mhelp);

	return menubar;
    }// makeMenu

    /**
     * Creates a new keyframe to use in timer. Delays handler execution to every 2 seconds.
     * 
     * @param handler  an EventHandler of type ActionEvent that represents the actions to occur when a button is clicked
     */
    void startTimer(EventHandler handler) {
    	KeyFrame keyframe = new KeyFrame(Duration.seconds(2), handler);
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyframe);
	timeline.play();
    }// useTimeline
    
    /**
     * Temporarily stops the timer.
     */
    void stopTimer() {
    	timeline.pause();
    }// stopTimer

    // void showAboutPage() {
    // 	Parent root;
    // 	Stage about = new Stage();
    // 	stage.setTitle("ABOUT MARY-BROWN");
	
    // 	DialogPane about = new DialogPane();
    // 	HBox personalInfo = new HBox();
    // 	HBox.getChildren().addAll(new Label("Name: Mary Elizabeth Sade Brown"), new Label("email: meb43885@uga.edu"), new Label("Version: Happiness 2.0"));
    // 	about.setTop(personalInfo);

	
    // 	about.setCenter(face);
    // }// shpwAboutPage
} // GalleryApp
