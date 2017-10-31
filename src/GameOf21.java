import java.util.*;

/**
 * Program: GameOf21.java
 * Purpose: Class for game of 21. Deals each player 2 cards, asks if they want a third one. If not, they stand.
 * Afterwords the hand totals are calulated and the closest to 21 without bust wins. 
 * Coder  : Kenton Dang, 0798640
 * Date   : Feb 27, 2017
 */


public class GameOf21 extends CardGame{

	// Constructor fields
	private Player user = new Player("User");
	private Player computer = new Player();
	
	/*
	 * Name: GameOf21
	 * Return: N/A
	 * Purpose: 2-Arg constructor
	 */
	public GameOf21(String gameType, String userName) {
		super(gameType);
		this.user.setName(userName);
	}

	/*
	 * Name: getUser
	 * Return: Player
	 * Purpose: Returns current player object
	 */
	public Player getUser(){
		return this.user;
	}
	
	/*
	 * Name: getComputer
	 * Return: Player
	 * Purpose: Returns the computer player object
	 */
	public Player getComputer(){
		return this.computer;
	}
	
	/*
	 * Name: setUser
	 * Return: void
	 * Purpose: Set user's current name to given string
	 */
	public void setUser(String newName){
		this.user.setName(newName);
	}
	
	/*
	 * Name: setComputer
	 * Return: void
	 * Purpose: Set computer's name to given string
	 */
	public void setComputer(String newName){
		this.computer.setName(newName);
	}
	
	/*
	 * Name: userDraws
	 * Return: void
	 * Purpose: Adds a card to the user's hand
	 */
	private void userDraws() {
		this.user.getHand().add(super.dealCard());
	}
	
	/*
	 * Name: computerDraws
	 * Return: void
	 * Purpose: Adds a card to the computer's hand
	 */
	private void computerDraws() {
		this.computer.getHand().add(super.dealCard());
	}
	
	/*
	 * Name: printTitle
	 * Return: void
	 * Purpose: Prints the introduction to 21
	 */
	protected void printTitle(String gameType) {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Welcome to the Game of 21!");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("HOW TO PLAY:");
		System.out.println("-----------------------------------------------------------------");
	}

	/*
	 * Name: printInstructions
	 * Return: void
	 * Purpose: Prints a formatted set of instructions for 21
	 */
	protected void printInstructions() {
		System.out.println("21 (Blackjack) is a game played with a deck of 52 cards.");
		System.out.println("The goal of the game is to get as close to 21 as possible.");
		System.out.println("Each player is dealt 2 cards to begin. You look at your hand");
		System.out.println("and choose to draw another card or stand. You are allowed");
		System.out.println("to get up to 2 more cards, but in this version only 1 is allowed.");
		System.out.println("At the end everyone reveals their cards and whoever has their");
		System.out.println("hand total closest to 21 is the winner.");
		System.out.println("-----------------------------------------------------------------");
	}

	/*
	 * Name: playGame
	 * Return: void
	 * Purpose: Sets up the entire game. Starts by empting hand from previous games. Re-populate deck. Deals 2 cards 
	 * to each player. From there it will ask if you want a new card, and use input validation to determine.
	 */
	protected void playGame() {
		
			//Set up hands and deck
			this.user.emptyHand();
			this.computer.emptyHand();
			super.populateDeck();
			this.user.draw(super.dealCard());
			this.computer.draw(super.dealCard());
			this.user.draw(super.dealCard());
			this.computer.draw(super.dealCard());

			//Print initial hand
			System.out.println("");
			System.out.println(user.toString());
			System.out.println(computer.toString());
	
			//Get input from user
			Scanner input = new Scanner(System.in);
			String c = "";
			
			boolean validInput = false;
		
			System.out.print("Do you want another card (Y/N)? ");
			
			//If the boolean is false, keep asking for valid input
			while (!validInput)
			{
				c = input.next();
				System.out.println("");
				
				//If the user gets a new card, add it to hand and print hand tostring.
				if (c.equalsIgnoreCase("Y"))
				{
					this.user.draw(super.dealCard());
					System.out.println(this.user.toString());
					validInput = true;
				}
				else if (c.equalsIgnoreCase("N"))
				{
					validInput = true;
				}
				else 
				{
					System.out.println("Invalid input. Please enter either Y or N:  ");
				}
			}
			
			//If the user's hand total is less than the computer's or the computer's hand is less than 21
			//then the computer will take another card
			if (user.totalHand() <= computer.totalHand() || computer.totalHand() < 21)
			{
				this.computer.draw(super.dealCard());
				System.out.println(this.computer.toString());
			}
			
			//Print the winner and then call playAgain to see if user wants to play again.
			printWinner();
			playAgain();
	}

	/*
	 * Name: printWinner
	 * Return: void
	 * Purpose: Check to see who wins without bust and outputs it. 
	 */
	protected void printWinner() {
		System.out.println("------------------------------");
		System.out.println("Game of 21 Final Score");
		System.out.println("------------------------------");
		System.out.printf(this.user.getName() + " points: %15d\n" , this.user.totalHand());
		System.out.printf("Computer points: %13d\n" , this.computer.totalHand());
		System.out.println("-------------------------------");
		
		boolean playerBust = false;
		boolean computerBust = false;
		
		//If statement checks if either user, computer or both bust.
		if (this.user.totalHand() > 21 && this.computer.totalHand() <= 21){
			playerBust = true;
		}
		else if (this.computer.totalHand() > 21 && this.user.totalHand() <= 21){
			computerBust = true;
		}
		else if (this.user.totalHand() > 21 && this.computer.totalHand() > 21)
		{
			playerBust = true;
			computerBust = true;
		}
		
		//Giant if that checks the outcome possibilities. Long because it must check if the user hasn't busted 
		//and if their hand is larger than the computers'.
		if (computerBust && playerBust == false)
		{
			System.out.println("Computer bust. " + this.user.getName() + " wins!");
		}
		else if (playerBust == false && computerBust == false && this.user.totalHand() <= 21 
				&& this.user.totalHand() > this.computer.totalHand())
		{
			System.out.println(this.user.getName() + " wins!");
		}
		else if (computerBust == false && playerBust == false && this.computer.totalHand() <= 21 
				&& this.computer.totalHand() > this.user.totalHand())
		{
			System.out.println("Computer wins!");
		}
		else if (playerBust && computerBust == false)
		{
			System.out.println(this.user.getName() + " bust. Computer wins!");
		}
		else if (playerBust == true && computerBust == true)
		{
			System.out.println("Both players bust. Computer wins by default!");
		}
		else if (this.user.totalHand() == this.computer.totalHand())
			System.out.println("Game is a draw.");
		
		System.out.println("-------------------------------");
	}

	/*
	 * Name: playAgain
	 * Return: void
	 * Purpose: Prompts the user if they want to play again, uses input validation to ensure correct input
	 */
	protected void playAgain() {
		System.out.print("Play another game (Y/N)? ");

		Scanner input = new Scanner(System.in);
		String c = "";
		
		boolean validInput = false;
		
		while (!validInput)
		{
			c = input.next();
			if (c.equalsIgnoreCase("Y"))
			{
				playGame();
				validInput = true;
			}
			else if (c.equalsIgnoreCase("N"))
			{
				System.out.println("Thanks for playing, bye!");
				break;
			}
			else 
			{
				System.out.println("Invalid input. Please enter either Y or N:  ");
			}
		}
		
		input.close();
	}
	
	//Calls the other methods in the proper order to start the game
	public void startGame() {
		printTitle("21");
		printInstructions();
		playGame();
	}
}
