import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.*;

import java.awt.Color;
/**
 * Description: Makes Bug do stuff
 *
 * @author Aaron Johnson
 */
public class myBug extends Bug {
    Flower flower;
    public void act() {
        if (getGrid() == null)
            return;  
        if (canMove()) { // makes bugs continuously move
            move();
        } else {
            turn(); // turns right if hits wall
        }
        if (seeFlower()){ //sees a flower in front 
            lookAtFlower(); // looks at flower in front and gets its info
            if (flower.getColor().equals(Color.RED)){ // determines if the flower in front is red
                System.out.print("\f"); // clears previous game determinations
                System.out.println("You Dead, Computer Wins.");
                System.exit(0); // ends game 
            } 
        }
    }
    public void move(){ // moves the bug forward and puts flower behind it
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        myFlower flower = new myFlower(getColor()); // creates a flower and sets color to the bug color
        flower.putSelfInGrid(gr, loc); // puts flower behind bug
    }
    public boolean seeFlower() { // allows bug to see flowers in front
        return getNeighbor() instanceof Flower;
    }
    private Actor getNeighbor() { // needed for seeFlower
        Location next = getNextLocation();
        if (getGrid().isValid(next)) {
            return getGrid().get(next);
        } else {
            return null;
        }
    }
    private Location getNextLocation() { // needed for getNeighbor
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        return next;
    }
    public void lookAtFlower() { // Looks at a flower so bug can determine its color 
        Actor neighbor = getNeighbor();
        if (neighbor instanceof Flower) {
            flower = (Flower) neighbor;
        } 
    }
}
