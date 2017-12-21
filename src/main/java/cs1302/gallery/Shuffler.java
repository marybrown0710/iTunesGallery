package cs1302.gallery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.image.*;

import javafx.scene.control.Button;
//import javafx.scene.control.*;

import java.util.ArrayList;

import java.util.Random;

/**
 * Handles the shuffle ability of the Play/Pause button.
 * 
 * @author MaryBrown
 *
 */
public class Shuffler extends Button {
	
    Random rand;
    ImageGallery newGallery = null;
    //ImageGallery imgGal; // (gallery - ImageGallery)
    ArrayList<String> imagesC; //bring in images (ArrayList<String> - GalleryApp)
    /**
     * Default constructor for Shuffle class. Creates a random number generator and
     * clones a copy of the ArrayList of strings (images) holding all the URLs returned
     * from the search. 
     * Also sets size of play/pause button.
     */
    public Shuffler(CenterPane flow) {
		
		rand = new Random();
		newGallery = new ImageGallery(flow);
		//imgGal = ;
		//imagesC = new ArrayList<String>(numResults);

		//for(String image: images)
		//    {
		//	imagesC.add(image.clone());
		//    }// for
		    
		//set style of button (size...)
		
	}// Shuffler
		
    /**
	 * Randomly selects and shuffles an image in the app.s
	 */
    public void shuffleImages(Image[] imgGal, ArrayList<String> images, CenterPane flow) {
	Image newImage = null;
		
	if (imgGal != null)
	    {
		int frameIndex = rand.nextInt(20);            //index to change in current frame display
		int index = 0;
		do
		    {
			index = rand.nextInt(images.size());      //index of all image URLs. 
		    } 
		while (index < 20);								  //Pick new URL.
		
		if (index != 0)
		    {
			//newImage = new ImageView(new Image(images.get(index)));
			newImage = new Image(images.get(index));

			if (newImage != null)
			    {
				imgGal[frameIndex] = newImage;

				//newGallery = new ImageGallery();
				for (Image img : imgGal){
				    newGallery.showImage(new ImageView(img), flow);
				}// for
			    }//if
		    }//if
	    }//if
	
	
    }// shuffleImages
		

}// Shuffler
