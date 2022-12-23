
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.*;

import java.awt.Color;
/**
 * Description: Makes computerBug do stuff, he can be too smart sometimes
 * 
 * To win as is: have bug surrounded on 3 sides.
 *
 * @author Aaron Johnson
 */
public class computerBug extends Bug {
    Flower flower;
    private int steps;
    private int steplength;
    public computerBug(){
        steps = 0; // these variables are for making the computerBug move semi randomly
        steplength = 1;
    }
    public void act() {
        if (getGrid() == null)
            return;  
        if (steps < steplength && canMove()) { // makes bugs continuously move 
            move();
            steps++;
        } else {
            randomTurn();
            steplength = steplength+2; // makes a random turn every +2 steps from the last, starts with 1 step
            steps = 0;
        }
        if (seeFlower()) { // tries to avoids flowers
            lookAtFlower();
            if (flower.getColor().equals(Color.BLUE)){
               turn(90); // turns away from the blue flowers 
            }
            /* Uncomment to make bug less smart (makes winning easier):
            randomTurn(); // turns randomly after turning away from the blue flower so that 
                          // he has a better chance of turning back into the blue flower */
        }
        if (seeFlower()){
            lookAtFlower();
            if (flower.getColor().equals(Color.BLUE)){
                System.out.print("\f"); // clears previous game determination
                System.out.println("You Won!"); // if computerBug lost then you win
                System.exit(0); // ends game 
            } 
        }
    }
    public void move(){ // moves the bug forward and puts flower behind
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
    public boolean seeFlower() { // allows bug to see flowers infront of them
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
    public void randomTurn() { //makes random turn 90 degrees 
        if (Math.random() < 0.5) {
            turn(-90);
        } else {
            turn(90);
        }
    }
    public void turn(int degrees) { // needed to turn a certain amount of degrees in turn
         setDirection(getDirection() + degrees);
    }
    public void lookAtFlower() { // looks at a flower so bug can determine the color
        Actor neighbor = getNeighbor();
        if (neighbor instanceof Flower) {
            flower = (Flower) neighbor;
        } 
    }
}
