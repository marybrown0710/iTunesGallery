package cs1302.gallery;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Handles all the functions of the image gallery displayed in the app.
 * 
 * @author Mary Brown
 *
 */
public class ImageGallery {
	
    CenterPane flow; //bring in flowPane
    //ArrayList<ImageView> frames;
    //ArrayList<Image> frames;
    Image[] frames = null;
    private int i = 0;
    
    /**
     * Default constructor for ImageGallery class. Creates new Image array.
     */
    //public ImageGallery () {
	//frames = new ArrayList<ImageView>(20);
    //	frames = new Image[20];
    //}// ImageGallery

     /**
     * Constructor for ImageGallery class. Creates new Image array and
     * brings in CenterPane object for reference.
     */
    public ImageGallery (CenterPane flowP) {
	//frames = new ArrayList<ImageView>(20);
	frames = new Image[20];
	this.flow = flowP;

	//if (firstTime)
	//{
	//	initDisplay();
	//    }
    }// ImageGallery
  
   
    /**
     * Returns the image gallery for outside class use
     *
     * @return a Image array
     */
    //public ArrayList<ImageView> getGallery() {
    public Image[] getGallery() {
	if (frames != null) {
	    return this.frames;
	}
	else 
	    {
		Image[] frames = new Image[20];
		return frames;
	    }
    }// getGallery

    /**
    * Initializes the cover art upon opening.
    */
    public void initDisplay(CenterPane flow, BorderPane borderpane, GalleryApp app) {
	//if (flow == null) System.out.print("I am null.");
	flow.readInput("country", this, borderpane, app, flow);
    }// initDisplay
    
    /**
     * Clears the cover art upon new search.
     */
    public void clearImages(CenterPane flow) {
	flow.getChildren().clear();
    }// clearImages
    
    /**
     * Creates a new ImageView.
     * 
     * @param i        the index of the array holding all array of URLs
     * @param artUrl   the string that represents the url of the image.
     */
    void makeFrame(String artUrl, CenterPane flow){
	 
	
	System.out.println("HEREEEEE. :)" + i);

	Image image = new Image(artUrl);
	ImageView img = new ImageView(image);
	img.setFitWidth(100);
	//img.setFitHeight(100);
	img.setPreserveRatio(true);
	img.setSmooth(true);
	
	//add image to "frames" while within bounds.
	//should be images shown to display.
	if (i < 20)
	    {
		//System.out.println(frames.size());
	    	frames[i] = image;
		//System.out.println(frames.size());
		System.out.println("HEREEEEE." + i);
	    }

	showImage(img, flow);
	i++;
    }// makeFrame
    
    /**
     * Adds image to app, to displays the image in the ImageView
     * 
     * @param img  the ImageView to be added to the form
     */
    void showImage(ImageView img, CenterPane flow) {
	flow.getChildren().add(img);
    }// showImage
    
    /**
     * Shuffles the image.
     */
    
}// ImageGallery