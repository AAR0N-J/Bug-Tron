import info.gridworld.grid.BoundedGrid;
import info.gridworld.actor.ActorWorld;

import java.awt.Color;
/** 
 * Description: Makes a gridworld with 2 players to play a tron game, but with bugs and flowers.
 * 
 * Rules of the game: 
 * 1. You are Blue player and the computer is Red. 
 * 2. They are Bugs and The Bug will move foward on its own, you do not need to continuously hit the arrow keys. 
 * 3. You control the direction of the Blue Bug using the arrow keys.
 * 4. Both players can go over their own flowers.
 * 5. Make the other player hit your flower.
 * 
 * Options:
 * 1. to make the game harder, turn up the speed.
 * 2. to make it easier, uncomment specified section in computerBug class.
 * 
 * @author Aaron Johnson
 */
public class BugTron
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new BoundedGrid(37, 37)); // Makes the grid to play on that is 36 squares big, 
                                                                    // its large enough so you do not die too quick
        myBug player = new myBug(); // Creates Player, the user 
        computerBug Computer = new computerBug(); // creates a computer bug
        player.setColor(Color.BLUE); // The Users Color will be blue       
        Computer.setColor(Color.RED); // The computer color will be red
        
        world.add(player); // adds the player to the world at a random location
        world.add(Computer); // adds the computer to a random location
        
        java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(new java.awt.KeyEventDispatcher(){ //controls the player Bug with arrow keys
                public boolean dispatchKeyEvent(java.awt.event.KeyEvent event){
                    String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();
                    if (key.equals("pressed UP"))
                        player.setDirection(0);
                    if (key.equals("pressed RIGHT"))
                        player.setDirection(90);
                    if (key.equals("pressed DOWN"))
                        player.setDirection(180);
                    if (key.equals("pressed LEFT"))
                        player.setDirection(270);
                    return true;
                }
            });
 
        world.show(); //shows world
    }
}