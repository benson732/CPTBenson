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
                con.println("3. HipHop");
                int intCategory = getValidatedInt(con, "Enter your category: ");

                String[][] strSelectedQuestions = null;

                if (intCategory == 1) {
                    strSelectedQuestions = loadQuestionsFromFile("MCU.txt");
                } else if (intCategory == 2) {
                    strSelectedQuestions = loadQuestionsFromFile("DC.txt");
                } else if (intCategory == 3) {
                    strSelectedQuestions = loadQuestionsFromFile("HipHop.txt");
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
	
		// Load questions from a file
    public static String[][] loadQuestionsFromFile(String fileName) {
        String[][] questions = new String[10][6];
        TextInputFile file = new TextInputFile(fileName);
        int index = 0;
        while (!file.eof() && index < 10) {
            String line = file.readLine();
            String[] parts = line.split("\\|"); // Split by "|" delimiter
            if (parts.length == 6) {
                questions[index] = parts;
                index++;
            }
        }
        file.close();
        return questions;
    }


		// Method to play the game
    public static void playGame(Console con, String[][] strQuestions, String strPlayerName) {
        if (strQuestions == null || strQuestions.length == 0) {
            con.println("No questions available for this category.");
            return;
        }

        con.clear(); // Clear the screen before starting the game
        int intScore = 0; // Track score

        for (int intCount = 0; intCount < strQuestions.length; intCount++) {
            con.clear(); // Clear the screen before showing the next question
            con.println(strQuestions[intCount][0]); // Display question
            con.println("A. " + strQuestions[intCount][1]);
            con.println("B. " + strQuestions[intCount][2]);
            con.println("C. " + strQuestions[intCount][3]);
            con.println("D. " + strQuestions[intCount][4]);
            con.print("Enter your answer (A/B/C/D): ");
            String strAnswer = con.readLine().trim().toUpperCase(); // Normalize input to uppercase

            // Map the input to the corresponding column
            String strSelectedAnswer = "";
            if (strAnswer.equals("A")) {
                strSelectedAnswer = strQuestions[intCount][1];
            } else if (strAnswer.equals("B")) {
                strSelectedAnswer = strQuestions[intCount][2];
            } else if (strAnswer.equals("C")) {
                strSelectedAnswer = strQuestions[intCount][3];
            } else if (strAnswer.equals("D")) {
                strSelectedAnswer = strQuestions[intCount][4];
            } else {
                con.println("Invalid choice. Please enter A, B, C, or D.");
                continue; // Skip to the next iteration
            }

            // Compare selected answer with the correct answer
            if (strSelectedAnswer.equals(strQuestions[intCount][5])) {
                con.println("Correct!");
                intScore++; // Increment score only if the answer is correct
            } else {
                con.println("Wrong! Correct answer: " + strQuestions[intCount][5]);
            }

            con.println("Press Enter to continue.");
            con.readLine(); // Pause before the next question
        }

        con.clear(); // Clear the screen before showing the final score
        con.println("Game Over! Your score: " + intScore);
        saveScore(con, intScore, strPlayerName); // Save the score
    }
        // Save the score to a file, with a cheat for "statitan"
    public static void saveScore(Console con, int intScore, String strPlayerName) {
        TextOutputFile file = new TextOutputFile("scores.txt", true);

        // Check for cheat code
        if (strPlayerName.equalsIgnoreCase("statitan")) {
            con.println("Cheat activated! Bonus +5 points added to your score.");
            intScore += 5; // Add bonus points
        }

        file.println(strPlayerName + ": " + intScore);
        file.close();
        con.println("Score saved!");
    }

    // Display scores from a file
    public static void displayScores(Console con) {
        TextInputFile file;
        try {
            file = new TextInputFile("scores.txt");
        } catch (Exception e) {
            con.println("No scores available yet.");
            return;
        }

        con.println("Scoreboard:");
        while (!file.eof()) {
            String strLine = file.readLine();
            con.println(strLine);
        }
        file.close();
    }

    // Display leaderboard sorted by scores
    public static void displayLeaderboard(Console con) {
        TextInputFile file;
        try {
            file = new TextInputFile("scores.txt");
        } catch (Exception e) {
            con.println("No scores available yet.");
            return;
        }

        // Read scores into an array
        java.util.ArrayList<String> players = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> scores = new java.util.ArrayList<>();

        while (!file.eof()) {
            String strLine = file.readLine();
            String[] parts = strLine.split(": ");
            if (parts.length == 2) {
                players.add(parts[0].trim());
                try {
                    scores.add(Integer.parseInt(parts[1].trim()));
                } catch (NumberFormatException e) {
                    con.println("Error parsing score: " + strLine);
                }
            }
        }
        file.close();

        // Sort scores and players
        for (int i = 0; i < scores.size(); i++) {
            for (int j = i + 1; j < scores.size(); j++) {
                if (scores.get(j) > scores.get(i)) {
                    int tempScore = scores.get(i);
                    scores.set(i, scores.get(j));
                    scores.set(j, tempScore);

                    String tempPlayer = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, tempPlayer);
                }
            }
        }

        // Display leaderboard
        con.println("Leaderboard:");
        for (int i = 0; i < players.size(); i++) {
            con.println((i + 1) + ". " + players.get(i) + " - " + scores.get(i));
        }
    }

    // Display help information
    public static void displayHelp(Console con) {
        con.println("How to Play:");
        con.println("1. Choose a category.");
        con.println("2. Answer questions by typing A, B, C, or D.");
        con.println("3. View your score at the end.");
        con.println("4. Check the leaderboard to see top players.");
        con.println("Cheat Code: Enter 'statitan' as your name for a bonus!");
    }

    // Validate integer input
    public static int getValidatedInt(Console con, String prompt) {
        int intValue = -1;
        boolean isValid = false;

        while (!isValid) {
            try {
                con.print(prompt);
                intValue = con.readInt();
                isValid = true;
            } catch (Exception e) {
                con.println("Invalid input. Please enter a number.");
                con.readLine(); // Clear invalid input
            }
        }
        return intValue;
    }
}

	
