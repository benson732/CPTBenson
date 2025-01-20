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
			// MCU questions array
    public static String[][] loadMCUQuestions() {
        return new String[][] {
            {"Who is the new Sorcerer Supreme after Doctor Strange?", "Wong", "Mordo", "Strange", "Clea", "Wong"},
            {"What material is Captain America’s shield made of?", "Adamantium", "Vibranium", "Titanium", "Steel", "Vibranium"},
            {"Who created Ultron in the Marvel Cinematic Universe?", "Bruce Banner", "Tony Stark", "Hank Pym", "Steve Rogers", "Tony Stark"},
            {"What is the name of Thor’s enchanted hammer?", "Mjolnir", "Stormbreaker", "Excalibur", "Aegis", "Mjolnir"},
            {"Which Infinity Stone does Vision have embedded in his forehead?", "Mind Stone", "Power Stone", "Time Stone", "Reality Stone", "Mind Stone"},
            {"What is Black Widow’s real name?", "Natasha Romanoff", "Wanda Maximoff", "Yelena Belova", "Melina Vostokoff", "Natasha Romanoff"},
            {"Who is the main villain in the first Avengers movie?", "Loki", "Thanos", "Ultron", "Red Skull", "Loki"},
            {"What is the name of T’Challa’s home country in Black Panther?", "Wakanda", "Sokovia", "Genosha", "Latveria", "Wakanda"},
            {"What is the name of the AI assistant that replaces JARVIS in Tony Stark’s suit?", "FRIDAY", "EDITH", "KAREN", "VISION", "FRIDAY"},
            {"Who rescues Tony Stark and Nebula from space in Avengers: Endgame?", "Captain Marvel", "Thor", "Rocket Raccoon", "Doctor Strange", "Captain Marvel"}
        };
    }

			// DC questions array
    public static String[][] loadDCQuestions() {
        return new String[][] {
            {"What is Superman’s birth name?", "Clark Kent", "Kal-El", "Jor-El", "Zor-El", "Kal-El"},
            {"What is the name of Batman’s butler?", "Alfred Pennyworth", "Lucius Fox", "James Gordon", "Harvey Dent", "Alfred Pennyworth"},
            {"Who is Wonder Woman’s father in the DC Universe?", "Zeus", "Ares", "Hades", "Poseidon", "Zeus"},
            {"What city does The Flash protect?", "Central City", "Gotham City", "Star City", "Metropolis", "Central City"},
            {"What is the name of Aquaman’s underwater kingdom?", "Atlantis", "Lemuria", "Asgard", "Themyscira", "Atlantis"},
            {"What superpower does Green Lantern’s ring grant?", "Energy Constructs", "Flight", "Super Strength", "Invisibility", "Energy Constructs"},
            {"What is the Joker’s real name in the film Joker (2019)?", "Arthur Fleck", "Jack Napier", "Jerome Valeska", "Joseph Kerr", "Arthur Fleck"},
            {"What is the name of Batman’s secret headquarters?", "Batcave", "Watchtower", "Fortress of Solitude", "Hall of Justice", "Batcave"},
            {"Who plays Harley Quinn in the DCEU films?", "Margot Robbie", "Gal Gadot", "Amber Heard", "Amy Adams", "Margot Robbie"},
            {"Who is the main villain in the Justice League movie (2017)?", "Steppenwolf", "Darkseid", "Lex Luthor", "Brainiac", "Steppenwolf"}
        };
    }

			// Hip-Hop questions array
    public static String[][] loadHipHopQuestions() {
        return new String[][] {
            {"Who is known as the Godfather of Rap?", "DJ Kool Herc", "Grandmaster Flash", "Tupac Shakur", "Rakim", "DJ Kool Herc"},
            {"Which artist released the album “To Pimp a Butterfly”?", "Kendrick Lamar", "J. Cole", "Drake", "Kanye West", "Kendrick Lamar"},
            {"What is the name of Jay-Z’s record label?", "Roc-A-Fella Records", "Death Row Records", "Bad Boy Records", "No Limit Records", "Roc-A-Fella Records"},
            {"Which group is known for the hit song “Straight Outta Compton”?", "N.W.A", "Public Enemy", "Run DMC", "Beastie Boys", "N.W.A"},
            {"Who is the first rapper to win a Pulitzer Prize?", "Kendrick Lamar", "Eminem", "Nas", "Jay-Z", "Kendrick Lamar"},
            {"Which rapper is also known as Slim Shady?", "Eminem", "Snoop Dogg", "50 Cent", "Dr. Dre", "Eminem"},
            {"What was Tupac Shakur’s stage name?", "2Pac", "Biggie", "Ice-T", "Nas", "2Pac"},
            {"Who founded the record label Death Row Records?", "Dr. Dre", "Suge Knight", "Tupac", "Ice Cube", "Suge Knight"},
            {"Which rapper is known as the 6 God?", "Drake", "Lil Wayne", "Travis Scott", "Post Malone", "Drake"},
            {"What city is commonly referred to as the birthplace of hip-hop?", "New York", "Los Angeles", "Chicago", "Atlanta", "New York"}
        };
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
    
	
