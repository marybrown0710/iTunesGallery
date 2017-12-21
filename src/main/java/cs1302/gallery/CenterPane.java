package cs1302.gallery;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import javafx.scene.control.ContentDisplay;
//import javafx.scene.control.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.image.*;

import javafx.application.Platform;
//import javafx.application.*;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.*;

import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;

import java.lang.Thread;


/**
 * Handles all functions of the "CenterPane", a.k.a. the center of the app.
 * 
 * @author Mary Brown
 *
 */
public class CenterPane extends FlowPane {

    ImageGallery imgGal;
    public ArrayList<String> images;
    public ProgressBar p1;
    //public ImageView imgGal = new 
	
    /**
     * Default constructor for CenterPane class. Styles the CenterPane object created as needed and brings in ImageGallery and ProgressBar object for reference.
     *
     * @param pane  a BorderPane object holds all controls of the app
     * @param app   a GalleryApp object that represents the application
     * @param img   a ImageGallery object that aids in all functions involving cover art
     * @param p1    a ProgressBar object
     */
    public CenterPane(BorderPane pane, GalleryApp app, ImageGallery img, ProgressBar p1) {
	
	this.setMaxHeight(480);
	this.setMaxWidth(500);
	this.setVisible(true);
	
	imgGal = img;
	this.p1 = p1;
	
	
	//code for play button pushed
	
	//code for pause button pushed
    } // CenterPane
    
    //		public void initDisplay() {
    //			img.initDisplay();
    //		}// initDisplay
    
    /**
     * Return the array holding all the URLs returned after the last search.
     *
     * @return an ArrayList od type string
     */
    public ArrayList<String> getArray() {
	return this.images;
    }// getArray

    /**
     * Returns the centerpane for outside class use.
     *
     * @return the centerpane
     */
    public CenterPane getPane() {
	return this;
    }// getPane

    /**
     * Reads the keyword the user inputs into the search. Makes an
     * images.
     * 
     * @param userInput  the keyword inputted by the user into the searchbar. 
     */
    public void readInput(String userInput, ImageGallery imgGal, BorderPane borderPane, GalleryApp app, CenterPane flow)
    {
	//String userInput = searchBar.getText();                  //take string from textField
	//userInput = userInput.trim();
	//userInput = userInput.replace(" ", "+");
	
	String searchFilter = "&entity=album";
	String itunesURL = "https://itunes.apple.com/search?term=";
	String sUrl = itunesURL + userInput + searchFilter;
	
	String encodedUI = null;
	URL url = null;
	try 
	    {
		encodedUI = URLEncoder.encode(userInput, "UTF-8");
	    } 
	catch (java.io.UnsupportedEncodingException a) 
	    {
		System.out.print("ERROR: Invalid keyword(s).");
	    }
	if (encodedUI != null){
	    
	    try
		{
		    url = new URL(sUrl);
		}
	    catch (java.net.MalformedURLException e) 
		{
		    System.out.println("ERROR: Invalid URL.");
		}
	}// if
	
	InputStreamReader reader = null;
	if (url != null){
	    try
		{
		    reader = new InputStreamReader(url.openStream());
		}
	    catch (java.io.IOException b)
		{
		    System.out.println("ERROR: Could not read URL");
		}
	}// if
	JsonParser jp = new JsonParser();
	JsonElement je = jp.parse(reader);
	
	
	JsonObject root = je.getAsJsonObject();                      // root of response
	JsonArray results = root.getAsJsonArray("results");          // "results" array
	int numResults = results.size();                             // "results" array size
	
	images = new ArrayList<String>(numResults);  
	
	/**
	if (numResults < 20) {
	    
	    Runnable r = () -> {
		//MAKE ALERT
	    };
	    //Thread t = new Thread(r);
	    //t.setDaemon(true);
	    //t.start();
	    Platform.runLater(r);
	}//if
	*/
	
	for (int i = 0; i < numResults; i++) {                       
	    JsonObject result = results.get(i).getAsJsonObject();    // object i in array
	    JsonElement artworkUrl100 = result.get("artworkUrl100"); // artworkUrl100 member
	    if (artworkUrl100 != null) {                             // member might not exist
		String artUrl = artworkUrl100.getAsString();         // get member as string
		System.out.println(artUrl);
		p1.setProgress((double)i/20);
		borderPane.setBottom(app.addHBox(p1));
		images.add(i,artUrl);
		//check for music only tags//
		
		
		
		//for (int i; i < 20; i++){
		//  System.out.println(artUrl);
		
		//while (i < 20) imgGal.makeFrame(i, artUrl);  
		
		//CODE TO MAKE IMAGE
		//if (this == null) System.out.println("I am null.");
		
		
		
		//  flow.getChildren().add(img);
		
		//}// if
		//for (int i; i < 20; i++) imgGal.makeFrame(i, artUrl);
	    } // if
	} // for
	
	System.out.println("HEREEEE." + images.size());
	//boolean failedSearch = false;
	if(images.size() < 20) 
	    {
		
		System.out.println("show error now.");
		showAlert(flow);
		return;
	    }
	else
	    {
		imgGal.clearImages(flow);
		for (int j = 0; j < 20; j ++) {
		    System.out.println("HEREEEE.");
		    if (imgGal == null) System.out.print("Null ImageGallery obj.");
		    if (images.get(j) == null) System.out.print("Null images array");
		    if (this == null) System.out.print("Null CenterPane obj.");
		    
		    imgGal.makeFrame(images.get(j), this);
		    System.out.println("FINISHED MAKING IMAGES!. :) ");
		    
		}// for
	    }//else
	//return failedSearch;
    }// readInput
    
    /**
     * Gets the word user enters into the searchbar.
     *
     * @param searchBar x  a Textfield object that holds the user's keyword
     * @return userInput  a String that represents the user's keywoard search
     */
    public String getUserInput(TextField searchBar) 
    {
	String userInput = searchBar.getText();                  //take string from textField
	userInput = userInput.trim();
	userInput = userInput.replace(" ", "+");
	
	return userInput;
    }// getUserInpt
    
    

    /**
     * Shows user an error messages because of invalid search
     *
     * @param flow  a CenterPane object that represents the center of the current stage
     */
    void showAlert(CenterPane flow) {
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle("ERROR.");
	alert.setHeaderText("Invalid Search.");
	alert.setContentText("We can't show you enough related images! Sorry! Maybe a new search? :P");
	//alert.initModality(Modality.APPLICATION_MODAL);
	//alert.initOwner(stage);
	alert.showAndWait();
	//alert.showAndWait()
	//    .filter(response -> response == ButtonType.OK)
	//    .ifPresent(response -> formatSystem());
	//flow.getChildren().add(alert);
	if (alert.getResult() == ButtonType.OK)
	    {
		alert.close();
	    }
    }// showError
    
   
}// CenterPane
