import java.math.*;
import java.util.*;

/**
 * Program: CardGameTester.java
 * Purpose: Creates appropriate objects and gathers input from user
 * Coder  : Kenton Dang, 0798640
 * Date   : Feb 28, 2017
 */

public class CardGameTester {
	public CardGameTester() {
	}

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		boolean validInput = false;
		String c = "";
	
		System.out.println("Welcome to the CardGame Application!");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("This application asks a player to choose a card game,");
		System.out.println("and then allows the player to play the game against the computer.");
		System.out.println("-----------------------------------------------------------------");
		System.out.print("Enter your name: ");
		
		String name = input.nextLine();
		
		System.out.println(name + ", choose your card game (1 or 2)");
		System.out.println("1. Game of 21");
		System.out.println("2. War");
		
		while (!validInput)
		{
			c = input.next();
			if (c.equals("1"))
			{
				GameOf21 newGame21 = new GameOf21("GameOf21", name);
				newGame21.startGame();
				validInput = true;
			}
			else if (c.equals("2"))
			{
				GameOfWar newGameWar = new GameOfWar("GameOfWar", name);
				newGameWar.startGame();
				validInput = true;
			}
			else 
			{
				System.out.println("Invalid game. Please enter either 1 or 2:  ");
			}
		}
	}

}
