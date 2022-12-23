import info.gridworld.actor.*; 

import java.awt.Color;
/**
 * Makes flower not wilt/change color
 *
 * @author Aaron Johnson
 */
public class myFlower extends Flower {
    public myFlower(Color initialColor){
        setColor(initialColor); // sets color of bug to flower color
    }
    public void act(){
        setColor(getColor()); // keeps the color the same as what it is set to 
    }
}
