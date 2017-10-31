import java.util.*;

/**
 * Program: GameOfWar.java
 * Purpose: Game of war, completely random. If a tie then place down 3 cards and reveal a 4th 
 * to see who takes all.
 * Coder  : Kenton Dang, 0798640
 * Date   : Feb 28, 2017
 */

public class GameOfWar extends CardGame{

	private Player user = new Player("User");
	private Player computer = new Player();
	private int userCount = 0;
	private int computerCount = 0;
	
	//2-Arg Constructor
	public GameOfWar(String gameType, String user) {
		super(gameType);
		this.user.setName(user);
	}

	/*
	 * Name: getUser
	 * Return: Player
	 * Purpose: Return player
	 */
	public Player getUser(){
		return this.user;
	}
	
	/*
	 * Name:getComputer
	 * Return: Player
	 * Purpose: Return player
	 */
	public Player getComputer(){
		return this.computer;
	}
	
	/*
	 * Name: setUser
	 * Return: void
	 * Purpose: set username according to string
	 */
	public void setUser(String newName){
		this.user.setName(newName);
	}
	
	/*
	 * Name: setComputer
	 * Return:void
	 * Purpose: Set computer name according to string
	 */
	public void setComputer(String newName){
		this.computer.setName(newName);
	}
	
	/*
	 * Name:startGame
	 * Return:Void
	 * Purpose:Calls other methods to start game
	 */
	public void startGame(){
		printTitle("War");
		printInstructions();
		playGame();
	}
	
	/*
	 * Name:printTitle
	 * Return:void
	 * Purpose:Print title of game
	 */
	protected void printTitle(String gameType) {
		System.out.println("------------------------------------------------------------");
		System.out.println("Welcome to the Game of War!");
		System.out.println("------------------------------------------------------------");
		System.out.println("HOW TO PLAY:");
		System.out.println("------------------------------------------------------------");
	}

	/*
	 * Name:printInstructions
	 * Return:void
	 * Purpose:print instructions of game
	 */
	protected void printInstructions() {
		System.out.println("War is a game played between two people.");
		System.out.println("The goal is to win over all the cards in the deck.");
		System.out.println("Each player is given half the deck and then each");
		System.out.println("player flips their top card at the same time. Whoever");
		System.out.println("has the higher card wins and takes both cards and places");
		System.out.println("both cards in the bottom of their hand/deck. If it is a ");
		System.out.println("draw then both players place an additional 2 cards into");
		System.out.println("the potential pool and draw the 3rd card. Whoever wins takes");
		System.out.println("all cards in the pool. If another draw, repeat until win.");
		System.out.println("------------------------------------------------------------");
		System.out.println("");
	}
	
	/*
	 * Name:playAgain
	 * Return:void
	 * Purpose:Ask user if they want to play again
	 */
	protected void playAgain(){
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
				super.populateDeck();
				validInput = true;
			}
			else if (c.equalsIgnoreCase("N"))
			{
				System.out.println("");
				System.out.println("Thanks for playing. Bye!");
				break;
			}
			else 
			{
				System.out.println("Invalid input. Please enter either Y or N:  ");
			}
		}
		
		input.close();
	}
	
	/*
	 * Name:printWinner
	 * Return:void
	 * Purpose:Prints out winner at the end
	 */
	protected void printWinner()
	{
		
		System.out.println("--------------------------");
		System.out.println("Game of War Final Score");
		System.out.println("--------------------------");
		System.out.println(this.user.getName() + " points: \t\t" + userCount);
		System.out.println("Computer points: \t" + computerCount);
		System.out.println("---------------------------");
		
		if (userCount > computerCount)
			System.out.println(this.user.getName() + " wins!");
		else
			System.out.println("Computer wins!");

		System.out.println("---------------------------");
		System.out.println("");
		
	}
	
	/*
	 * Name:playGame
	 * Return:void
	 * Purpose:Deals out deck and calls the game
	 */
	protected void playGame(){
		for (int i = 1; i <= 52; i++)
		{
			if (i == 0 || i % 2 != 0)
				this.user.draw(super.dealCard());
			else
				this.computer.draw(super.dealCard());
		}
		
		if (!this.user.getHand().isEmpty() || !this.computer.getHand().isEmpty())
		{
			playBattle();
			printWinner();
		}		

		playAgain();
	}
	
	/*
	 * Name:playBattle
	 * Return:void
	 * Purpose:Check to see who wins and also calls war if needed
	 */
	private void playBattle()
	{
		ArrayList<Card> tempPool = new ArrayList<Card>();
		
		while (this.user.getHand().size() > 0 && this.computer.getHand().size() > 0)
		{
			
			System.out.println(this.user.getName() + " card: \t" + this.user.getHand().get(0));
			System.out.println("Computer card: \t" + this.computer.getHand().get(0));
			
			if (this.user.getHand().get(0).isGreaterThan(this.computer.getHand().get(0)))
			{
				tempPool.removeAll(tempPool);
				tempPool.add(this.user.getHand().get(0));
				tempPool.add(this.computer.getHand().get(0));
				this.user.discard(0);
				this.computer.discard(0);
				
				for (int o = 0; o <= tempPool.size() - 1; o++)
				{
					this.user.getHand().add(tempPool.get(o));
				}
				userCount++;
				System.out.println(this.user.getName() + " wins this battle. " 
				+ this.user.getName() + ": " + this.userCount + " Computer: " + this.computerCount);
				System.out.println("");
			}
			else if (this.computer.getHand().get(0).isGreaterThan(this.user.getHand().get(0)))
			{
				tempPool.removeAll(tempPool);
				tempPool.add(this.user.getHand().get(0));
				tempPool.add(this.computer.getHand().get(0));
				this.user.discard(0);
				this.computer.discard(0);
	
				for (int o = 0; o <= tempPool.size() - 1; o++)
				{
					this.computer.getHand().add(tempPool.get(o));
				}
				computerCount++;
				System.out.println("Computer wins this battle." + this.user.getName() 
				+ ": " + this.userCount + " Computer: " + this.computerCount);
				System.out.println("");

				
			}
			else if (this.user.getHand().get(0).equals(this.computer.getHand().get(0)))
			{
				if (this.user.getHand().size() < 4 && this.computer.getHand().size() < 4)
				{
					System.out.println("");
					System.out.println("Not enough cards in either players hands' to play a war.");
					System.out.println("Both cards are discarded.");
					System.out.println("");
					this.user.discard(0);
					this.computer.discard(0);
				}
				else if (this.user.getHand().size() < 4 )
				{
					System.out.println("");
					System.out.println("Not enough cards in " + this.user.getName() + "'s hand to play a war.");
					System.out.println(this.user.getName() + "'s cards are discarded.");
					System.out.println("");
					this.user.discard(0);
				}
				else if (this.computer.getHand().size() < 4)
				{
					System.out.println("");
					System.out.println("Not enough cards in computer's hand to play a war.");
					System.out.println("Computer's cards are discarded.");
					System.out.println("");
					this.computer.discard(0);
				}
				else
					playWar(2);
			}
		}
		
	}// end of method
		
	
	/*
	 * Name:playWar
	 * Return:void
	 * Purpose: if 2 cards are equal then call war
	 */
	private void playWar(int count)
	{
		ArrayList<Card> tempPool = new ArrayList<Card>();
		
		System.out.println("");
		System.out.println("Cards are equal. Begin a war . . .");
		System.out.println("");
		
		if (this.user.getHand().get(3).isGreaterThan(this.computer.getHand().get(3)))
		{
			for (int i = 0; i <= (count + 8)/2; i++)
			{
				tempPool.removeAll(tempPool);
				tempPool.add(this.user.getHand().get(i));
				tempPool.add(this.computer.getHand().get(i));
			}
			
			this.computer.discard(0);
			this.computer.discard(1);
			this.computer.discard(2);
			this.computer.discard(3);
			this.user.discard(0);
			this.user.discard(1);
			this.user.discard(2);
			this.user.discard(3);
			
			this.userCount += count + 8;
			
			for (int j = 0; j <= tempPool.size() - 1; j++)
			{
				this.user.getHand().add(tempPool.get(j));
			}
		}
		else if (this.computer.getHand().get(3).isGreaterThan(this.user.getHand().get(3)))
		{
			for (int i = 0; i <= (count + 8)/2; i++)
			{
				tempPool.removeAll(tempPool);
				tempPool.add(this.computer.getHand().get(i));
				tempPool.add(this.user.getHand().get(i));
			}
			this.computer.discard(0);
			this.computer.discard(1);
			this.computer.discard(2);
			this.computer.discard(3);
			this.user.discard(0);
			this.user.discard(1);
			this.user.discard(2);
			this.user.discard(3);

			this.computerCount += count + 8;
			
			for (int j = 0; j <= tempPool.size() - 1; j++)
			{
				this.user.getHand().add(tempPool.get(j));
			}
		}
		else if (this.user.getHand().get(3).equals(this.computer.getHand().get(3)))
		{
			if (this.user.getHand().size() < 4 && this.computer.getHand().size() < 4)
			{
				System.out.println("");
				System.out.println("Not enough cards in either players hands' to play a war.");
				System.out.println("Both cards are discarded.");
				System.out.println("");
				this.user.discard(0);
				this.computer.discard(0);
			}
			else if (this.user.getHand().size() < 4 )
			{
				System.out.println("");
				System.out.println("Not enough cards in " + this.user.getName() + "'s hand to play a war.");
				System.out.println(this.user.getName() + "'s cards are discarded.");
				System.out.println("");
				this.user.discard(0);
			}
			else if (this.computer.getHand().size() < 4)
			{
				System.out.println("");
				System.out.println("Not enough cards in computer's hand to play a war.");
				System.out.println("Computer's cards are discarded.");
				System.out.println("");
				this.computer.discard(0);
			}
			else
				playWar(8);
		}
		
		
		
	}// end of method
	
}
