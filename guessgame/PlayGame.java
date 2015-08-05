public class PlayGame {
	public static void main (String[] args) {
		GuessGame game = new GuessGame();
		game.StartGame();
	}
}

class Player{
	int number = 0; //where the guess goes
	
	public void guess() {
		number = (int) (Math.random() * 10);
		System.out.println("I am guessing " + number);
	}
}

class GuessGame{
	Player p1;
	Player p2;
	Player p3;
	// GuessGame has three instance variables for the three player objective
	public void StartGame() {
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		// Create three player objects and assigns them to the three player instance variables
	
		int guessp1 = 0;
		int guessp2 = 0;
		int guessp3 = 0;
		// declare three variables to hold the three guesses by players
	
		boolean p1isRight = false;
		boolean p2isRight = false;
		boolean p3isRight = false;
		// declare three variables to hold a true or false based on the guesses by players
	
		int targetNumber = (int) (Math.random() * 10);
		System.out.println("I am thinking a number between 0 and 9 ...");
	
		while(true){
			System.out.println("Number to guess is " + targetNumber);
			
			p1.guess();
			p2.guess();
			p3.guess();
			// call each player's guess method
			guessp1 = p1.number;
			System.out.println("Player 1 guessed " + guessp1);
			
			guessp2 = p2.number;
			System.out.println("Player 2 guessed " + guessp2);
			
			guessp3 = p3.number;
			System.out.println("Player 3 guessed " + guessp3);
			// get each player's result by accessing to the number variable of the player
			
			if (guessp1 == targetNumber){
				p1isRight = true;
			}
			
			if (guessp2 == targetNumber){
				p2isRight = true;
			}
			
			if (guessp3 == targetNumber){
				p3isRight = true;
			}
			
			if (p1isRight || p2isRight || p3isRight) {
				System.out.println("We have a winner!");
				System.out.println("Player one got it right? " + p1isRight);
				System.out.println("Player two got it right? " + p2isRight);
				System.out.println("Player three got it right? " + p3isRight);
				System.out.println("Game is over.");
				break; // game over, so break out of the loop
			} else {
				System.out.println("Players will have to try again.");
			}
			
		}
	}
}
