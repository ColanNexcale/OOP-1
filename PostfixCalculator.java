/**
 *
 * @author David Gonzales
 * 
 * 12.05.2018
 * 392026 Objektorientierte Programmierung in Java (V) (SoSe 2018) (ID: 2255)
 * Bewertungsaufgabe 05.4: Postfix-Taschenrechner
 * 
 * 
 */


public class PostfixCalculator {    
    
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) {
        
        System.out.print(LINE_SEPARATOR + "Willkommen zum Postfix-Notation Rechner" + LINE_SEPARATOR
                + "Es sind folgende Eingaben gültig: " + LINE_SEPARATOR
                + "-> Positive und negative Ziffern von 0-9" + LINE_SEPARATOR
                + "-> und die Operatorzeichen: + - / * : =" + LINE_SEPARATOR
                + "Hinweis! Negative Ziffern werden vor der Berechnung"
                + " in positive Ziffern umgewandelt." + LINE_SEPARATOR
                + "! Der Wertebereich dieses Programm: \"-2147483648\" bis \"2147483647\"" + LINE_SEPARATOR
                + "Berechnungen auserhalb des Wertebereich führen zu keinen konkreten Ergebnis! " + LINE_SEPARATOR
                + "Beendet wird das Programm mit dem Kommando \"exit\"" + LINE_SEPARATOR + LINE_SEPARATOR 
                + "Viel Spaß bein Testen ;)"); 
        
        do {
        System.out.print(LINE_SEPARATOR + LINE_SEPARATOR + "Eingabe: "); 
        String userInput = In.readLine().trim();
        
            if(userInput.equals("exit")) {
                System.out.println("Programm wird beendet");
                System.exit(0);
            } else if (!userInput.matches("[0-9\\*+-/:=\\s]+")) {
                System.out.print("Eingabeparameter nicht gültig!"+ LINE_SEPARATOR +
                        "Bitte nur Ziffern und die folgenden Operatorzeichen verwenden: + - / * : =");
            } else System.out.print(evaluation (userInput));
            
        } while (true);
    }
    
    static String evaluation (String userInput) {
    
        StringBuilder resultString;
        resultString = new StringBuilder();
        int resultCount = 0;
        
        String userInputSplit[] = userInput.split("\\s+");

        Stack s = new Stack(userInputSplit.length); 
        
        for (int i = 0; i<userInputSplit.length; i++) {

            try {
                if (userInputSplit[i].matches("(^\\+|-)?([0-9]+)") || userInputSplit[i].matches("[0-9]+")) {
                    if (Integer.parseInt(userInputSplit[i]) < 0) {
                        s.push(-Integer.parseInt(userInputSplit[i]));
                    } else {
                        s.push(Integer.parseInt(userInputSplit[i]));
                    }
                } 
            } catch (NumberFormatException e) {
                return (resultString.append(LINE_SEPARATOR).append("Fehler! Eingegebene Ziffer: ").append(e.getMessage().substring(18)).append(" ist auserhalb des Wertebereich.")
                        .append(LINE_SEPARATOR).append("Der Wertebereich dieses Programm: \"-2147483648\" bis \"2147483647\"")).toString();
            } 
            
            if (userInputSplit[i].matches("[\\*+-/:=]+")) {
                
                int cache;               
                
                    switch (userInputSplit[i]) {
                        case "+":
                            s.push((s.pop() + s.pop()));
                            break;
                        case "-":
                            cache = s.pop();
                            s.push(s.pop() - cache);
                            break;
                        case "*":
                            s.push(s.pop() * s.pop());
                            break;
                        case "/":
                            cache = s.pop();
                            s.push(s.pop() / cache);
                            break;
                        case ":":
                            cache = s.pop();
                            s.push(cache);
                            s.push(cache);
                            break;
                        case "=":
                            if (resultCount == 0) {
                                resultString.append("Ergebnis: ");
                                resultCount++;
                            } 
                            resultString.append(s.pop()).append(" ");
                            break;
                        default:
                            resultString.delete(0, resultString.length());
                            resultString.append(LINE_SEPARATOR).append("Fehler! ").append(LINE_SEPARATOR)
                                .append("Zwischen den Operatorzeichen immmer ein Leerzeichen verwenden!");
                    }          
            }   
        }

        resultString.append(LINE_SEPARATOR);
        if (resultCount != 0){
            return resultString.toString().trim();
        } else {
            return (resultString.append("Eingabefehler! Operatorzeichen \"=\" fehlt.")).toString().trim();
        }
    } 
}   
