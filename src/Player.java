import java.util.*;
import java.math.*;

/**
 * Program: Player.java
 * Purpose: 
 * Coder  : Kenton Dang, 0798640
 * Date   : Feb 27, 2017
 */

public class Player {

	//Constructor fields
	private String name;
	private ArrayList<Card> hand;
	
	/*
	 * Name: Player
	 * Return: N/A
	 * Purpose: 0-Args constructor. Use this to create the "computer" player
	 */
	public Player() {
		this.name = "Computer";
		this.hand = new ArrayList<Card>();
	}
	
	/*
	 * Name: Player
	 * Return: N/A
	 * Purpose: 1-Arg constructor. Pass it a string to set the name of the user.
	 */
	public Player(String name){
		this.name = name;
		this.hand = new ArrayList<Card>();
	}

	/*
	 * Name: getName
	 * Return: String
	 * Purpose: Return the name of the current Player object
	 */
	public String getName(){
		return this.name;
	}
	
	/*
	 * Name: getHand
	 * Return: ArrayList<Card> 
	 * Purpose: Returns an arraylist of the current player's hand. 
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}
	/*
	 * Name: setName
	 * Return: void
	 * Purpose: Set the name of the current player to the given string.
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/*
	 * Name: setHand
	 * Return: void
	 * Purpose: Sets the user's hand to the given arraylist.
	 */
	public void setHand(ArrayList<Card> hand){
		this.hand = hand;
	}
	
	/*
	 * Name: draw
	 * Return: void
	 * Purpose: Add a card to the player's hand.
	 */
	public void draw (Card c){
		this.hand.add(c);
	}
	
	/*
	 * Name: discard
	 * Return: void
	 * Purpose: Discards the card at the given index.
	 */
	public void discard (int index){
		this.hand.remove(index);
	}
	
	/*
	 * Name: discard
	 * Return: void
	 * Purpose: Discards the cards given the range of an index. Inclusive of first index but does not include second.
	 */
	public void discard (int begIndex, int endIndex){
		for (int i = 0; i <= 3; i++)
		{
			this.hand.remove(i);
		}
	}
	
	/*
	 * Name: totalHand
	 * Return: int
	 * Purpose: Returns the total rank of the hand as an int.
	 */
	public int totalHand(){
		int tempTotal = 0;
		
		for (int i = 0; i <= hand.size() - 1; i++)
		{
			tempTotal += hand.get(i).findCardValue();
		}
		
		for (int j = 0; j <= hand.size() - 1; j++)
		{
			if (hand.get(j).findCardValue() == 1)
			{
				hand.get(j).setRank(findAceValue(tempTotal));
			}
		}
		
		return tempTotal;
	}
	
	/*
	 * Name: findAceValue
	 * Return: int
	 * Purpose: Takes the total rank of the hand and determines if the ace's rank should be 11 or 1.
	 */
	private int findAceValue(int total){
		int temp = 0;
		
		if ((total + 10) < 21){
			temp = 14;
		}else{
			temp = 1;
		}
		
		return temp;
	}
	
	/*
	 * Name: emptyHand
	 * Return: void
	 * Purpose: Removes all cards from the hand.
	 */
	public void emptyHand(){
		this.hand.removeAll(hand);
	}
	
	/*
	 * Name: toString
	 * Return:
	 * Purpose:
	 */
	public String toString(){
		String temp = "";
		temp = this.getName() + " has " + this.hand.size() + " cards:\n";
		
		for (int i = 0; i <= this.hand.size() - 1; i++)
			temp += (i+1) + ") " + this.hand.get(i).toString() + "\n";

		return temp;
	}
	
}
