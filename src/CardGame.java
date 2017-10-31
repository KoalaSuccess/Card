import java.util.*;
import java.math.*;

/**
 * Program: CardGame.java
 * Purpose: Abstract class for 21 and War
 * Coder  : Kenton Dang, 0798640
 * Date   : Feb 27, 2017
 */

abstract class CardGame 
{
	private String gameType = "";
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	//1-Arg constructor
	protected CardGame(String gameType){
		this.gameType = gameType;
		
		populateDeck();
	}
	
	/*
	 * Name:getGameType
	 * Return:String
	 * Purpose:Returns gametype
	 */
	protected String getGameType(){
		return gameType;
	}
	
	/*
	 * Name:setGameType
	 * Return:void
	 * Purpose:set the gametype
	 */
	protected void setGameType(String gameType){
		this.gameType = gameType;
	}
	
	/*
	 * Name:getDeck
	 * Return:arrayList<card>
	 * Purpose:return the deck
	 */
	protected ArrayList<Card> getDeck(){
		return deck;
	}
	
	/*
	 * Name:populateDeck
	 * Return:void
	 * Purpose:Fills the deck with card objects
	 */
	protected void populateDeck(){

		for (int i = 1; i <= 4; i++)
		{
			for (int j = 1; j <= 13; j++)
			{
				String temp = "";
				switch(i){
				case 1: temp = "Spades";
						break;
				case 2: temp = "Clubs";
						break;
				case 3: temp = "Diamonds";
						break;
				case 4: temp = "Hearts";
						break;
				}
				Card newCard = new Card(temp,j);
				deck.add(newCard);
			}
		}
		shuffle();
	}
	
	/*
	 * Name:shuffle
	 * Return:void
	 * Purpose:Randomize cards in deck
	 */
	private void shuffle(){
		Collections.shuffle(this.deck);
	}
	
	/*
	 * Name:dealCard
	 * Return:Card
	 * Purpose:Remove the top card of the deck and return it
	 */
	protected Card dealCard(){
		Card tempCard = new Card(deck.get(0).getSuit(), deck.get(0).getRank());
		deck.remove(0);
		return tempCard;
	}
	
	//Abstract methods to be implemented by subclasses.
	abstract public void startGame();
	abstract protected void printTitle(String gameType);
	abstract protected void printInstructions();
	abstract protected void playGame();
	abstract protected void printWinner();
	abstract protected void playAgain();
	
}