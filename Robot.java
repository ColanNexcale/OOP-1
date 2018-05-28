/**
 *
 * @author David Gonzales
 * 
 * 23.05.2018
 * 392026 Objektorientierte Programmierung in Java (V) (SoSe 2018) (ID: 2255)
 * Bewertungsaufgabe 06.4: Roboter-Zwillinge
 * 
 * 
 */


public class Robot {
    
    int  verticalPosition, horizontalPosition;
    
    
    Robot (int verticalPosition, int horizontalPosition) {
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
        Grid.placeRobot(verticalPosition, horizontalPosition);
    } 
     
    
    void goLeft() {moveRobot (0,-1);}
    
    void goRight() {moveRobot (0,1);}
    
    void goUp() {moveRobot (-1,0);}
    
    void goDown() {moveRobot (1,0);}
    
    
    void moveRobot (int stepVerticalPosition, int stepHorizontalPosition) {
        Grid.removeRobot(verticalPosition, horizontalPosition);
        this.verticalPosition  += stepVerticalPosition;
        this.horizontalPosition += stepHorizontalPosition;
        Grid.placeRobot( verticalPosition, horizontalPosition);
    }
    
    
    boolean canSee(Robot otherRobot) {
        if (this.horizontalPosition == otherRobot.horizontalPosition) return true;
        return this.verticalPosition == otherRobot.verticalPosition;
    }  
}
