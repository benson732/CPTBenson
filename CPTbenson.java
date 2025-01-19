import arc.*;

public class CPTbenson{
	 public static void main(String[] args) {
        Console con = new Console();
        boolean blnPlayAgain = true; // While loop to keep the game running

        // Ask for the player's name
        con.print("Enter your name: ");
        String strPlayerName = con.readLine();
        
        // Main program loop
        while (blnPlayAgain) {
            con.clear(); // Clear the screen for a fresh display
            con.println("Welcome to the Quiz Game, " + strPlayerName + "!");
            con.println("1. Play Game");
            con.println("2. View Scores");
            con.println("3. Leaderboard");
            con.println("4. Help");
            con.println("5. Quit");
            int intMenuChoice = getValidatedInt(con, "Enter your choice: ");

            if (intMenuChoice == 1) {
                con.clear(); // Clear the screen before showing categories
                con.println("Choose a category:");
                con.println("1. MCU");
                con.println("2. DC");
                con.println("3. Hip-Hop");
                int intCategory = getValidatedInt(con, "Enter your category: ");

                String[][] strSelectedQuestions = null;

                if (intCategory == 1) {
                    strSelectedQuestions = loadMCUQuestions();
                } else if (intCategory == 2) {
                    strSelectedQuestions = loadDCQuestions();
                } else if (intCategory == 3) {
                    strSelectedQuestions = loadHipHopQuestions();
                } else {
                    con.println("Invalid category.");
                    continue;
                }

                if (strSelectedQuestions != null) {
                    playGame(con, strSelectedQuestions, strPlayerName); // Pass questions to game
                }
            } else if (intMenuChoice == 2) {
                con.clear(); // Clear the screen before displaying scores
                displayScores(con);
                con.println("Press Enter to return to the main menu.");
                con.readLine();
            } else if (intMenuChoice == 3) {
                con.clear(); // Clear the screen before displaying leaderboard
                displayLeaderboard(con);
                con.println("Press Enter to return to the main menu.");
                con.readLine();
            } else if (intMenuChoice == 4) {
                con.clear(); // Clear the screen before displaying help
                displayHelp(con);
                con.println("Press Enter to return to the main menu.");
                con.readLine();
            } else if (intMenuChoice == 5) {
                con.clear(); // Clear the screen before exiting
                con.println("Thanks for playing!");
                blnPlayAgain = false; // Exit the game
            } else {
                con.println("Invalid option. Try again.");
            }
        }
	}
    
	
