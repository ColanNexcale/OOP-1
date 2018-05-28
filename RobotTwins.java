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


public class RobotTwins {
    
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    
    public static void main(String[] args) {
        
        int fieldsize = 0;
        
        
        System.out.print(LINE_SEPARATOR + "Willkommen zum Roboter-Zwillinge Spiel." + LINE_SEPARATOR
                + "Gespielt wird auf einem quadratischen Spielfeld zwischen 4x4 und 16x16 Feldern." + LINE_SEPARATOR
                + "Das Spiel endet, wenn die beiden Roboter Sichtkontakt haben." + LINE_SEPARATOR); 
        
         do {
            System.out.print(LINE_SEPARATOR + LINE_SEPARATOR + "Bitte eine Spielfeldgröße zwischen 4 und 16 eingeben: "); 
            String userInput = In.readLine().trim();
            
            if (userInput.matches("[0-9]+") && Integer.parseInt(userInput) >= 4 && Integer.parseInt(userInput) <= 16) {
                fieldsize = Integer.parseInt(userInput);
            } else {        
                System.out.print(LINE_SEPARATOR + "Eingabe nicht gültig!" + LINE_SEPARATOR
                + "Bitte eine Zahl zwischen 4 und 16 eingeben" + LINE_SEPARATOR);
            }       
        } while (fieldsize == 0);
         
         
        Grid.create (fieldsize,fieldsize);
        
        Robot robot_1 = new Robot (0,0);
        Robot robot_2 = new Robot (fieldsize-1,fieldsize-1);
                
        if (fieldsize % 2 == 0) {     
            if (!Grid.askQuestion("Bei geraden Spielfeldgrößen kommst Du nie zum Spielende!" + LINE_SEPARATOR
                + "Trotzdem spielen? ")) {
                System.out.println("Programm wird beendet");
                System.exit(0);
            }    
        }
        
        Grid.showMessage("OK, mit folgenden Tastaturkommandos kannst Du Deinen Robotor über das Spielfeld bewegen:" + LINE_SEPARATOR + LINE_SEPARATOR
            + "MIt 'a' geht es nach links" + LINE_SEPARATOR
            + "MIt 'd' geht es nach rechts" + LINE_SEPARATOR 
            + "MIt 'w' geht es nach oben" + LINE_SEPARATOR 
            + "MIt 's' geht es nach unten" + LINE_SEPARATOR );
        
        do {         
            switch (Grid.readKey()) { 
                case 'a':             
                    if (checkMovementIfValid (robot_1,robot_2,0,-1)) {
                        robot_1.goLeft(); robot_2.goRight();
                    } 
                    break;
                case 'd':         
                    if (checkMovementIfValid (robot_1,robot_2,0,1)) {
                        robot_1.goRight(); robot_2.goLeft();
                    } 
                    break;
                case 'w':
                    if (checkMovementIfValid (robot_1,robot_2,-1,0)) {
                        robot_1.goUp(); robot_2.goDown(); 
                    } 
                    break;
                case 's':
                    if (checkMovementIfValid (robot_1,robot_2,1,0)) {
                        robot_1.goDown(); robot_2.goUp();
                    } 
                    break;
                default:
                    Grid.showMessage("Ungültiges Tastaturkommando! " + LINE_SEPARATOR + LINE_SEPARATOR
                        + "MIt 'a' geht es nach links" + LINE_SEPARATOR
                        + "MIt 'd' geht es nach rechts" + LINE_SEPARATOR 
                        + "MIt 'w' geht es nach oben" + LINE_SEPARATOR 
                        + "MIt 's' geht es nach unten");
            }
        
        } while (!robot_1.canSee(robot_2));
        
        
        if (robot_1.verticalPosition == robot_2.verticalPosition) {
            for (int i=0 ; i< fieldsize; i++) {
                Grid.paintFloor(robot_1.verticalPosition , i, Grid.GREEN);
            } 
        } else if (robot_1.horizontalPosition == robot_2.horizontalPosition) {
            for (int i=0 ; i< fieldsize; i++) {
                Grid.paintFloor(i , robot_1.horizontalPosition, Grid.GREEN);
            } 
        }
        
        
        System.out.println("Das Spiel endet, wenn die beiden Roboter Sichtkontakt haben");
        Grid.showMessage("Beide Roboter haben Sichtkontakt! " + LINE_SEPARATOR
        + "Das Spiel ist somit am Ende.");
        
        System.out.println("Programm wird beendet");
        System.exit(0);  
    }

    static boolean checkMovementIfValid (Robot robot_1, Robot robot_2, int stepVerticalPosition, int stepHorizontalPosition) {

        if (!Grid.insideBounds (robot_1.verticalPosition + stepVerticalPosition, robot_1.horizontalPosition + stepHorizontalPosition)) {
            Grid.showMessage("Den Robotor aus dem Spielfeld zu bewegen ist nicht möglich!");
            return false;
        } 
        
    return true;
    }    
}
