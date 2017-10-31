/**
 * Program: Card.java
 * Purpose: Create card objects with a suit and rank.
 * Coder  : Kenton Dang, 0798640
 * Date   : Feb 27, 2017
 */

public class Card {
	
	//Constructor fields
	//Each card has a suit and rank, suit is either spades, clubs, diamonds or hearts
	//Rank is a number between 1 and 13. 11, 12, 13 being jack, queen, king, respectively.
	private String suit;
	private int rank;
	
	//0-Args Constructor
	public Card() {
		this.suit = "";
		this.rank = 0;
	}
	
	//1-Args Constructor
	public Card(String suit){
		this.suit = suit;
		this.rank = 0;
	}
	
	//2-Args Constructor
	public Card(String suit, int rank){
		this.suit = suit;
		this.rank = rank;
	}

	/*
	 * Name: getSuit
	 * Return:	String
	 * Purpose:	To return the suit of the current card
	 */
	public String getSuit() {
		return suit;
	}

	/*
	 * Name: getRank
	 * Return:	int
	 * Purpose:	To return the rank of current card
	 */
	public int getRank() {
		return rank;
	}
	
	/*
	 * Name: setSuit
	 * Return:	void
	 * Purpose:	Assign new suit to current card
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

	/*
	 * Name: setRank
	 * Return:	Void
	 * Purpose:	Assign new rank to current card
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/*
	 * Name: isSameSuit
	 * Return:	boolean
	 * Purpose:	Checks if current card is the same suit as another card
	 */
	public boolean isSameSuit(Card c){
		if (this.suit == c.suit)
			return true;
		else
			return false;
	}
	
	/*
	 * Name: findFaceValue
	 * Return:	String
	 * Purpose:	Converts the rank of the card to a face value
	 */
	public String findFaceValue(){
		String temp = "";
		
		switch(this.rank) {
		case 1: temp = "Ace";
				break;
		case 2: temp = "Two";
				break;
		case 3: temp = "Three";
				break;
		case 4: temp = "Four";
				break;
		case 5: temp = "Five";
				break;
		case 6: temp = "Six";
				break;
		case 7: temp = "Seven";
				break;
		case 8: temp = "Eight";
				break;
		case 9: temp = "Nine";
				break;
		case 10: temp = "Ten";
				break;
		case 11: temp = "Jack";
				break;
		case 12: temp = "Queen";
				break;
		case 13: temp = "King";
				break;
		case 14: temp = "Ace";
				break;
		}
		
		return temp;
	}
	
	/*
	 * Name: findCardValue
	 * Return:	int
	 * Purpose:	Converts the numbers card value appropriately. In 21, jacks queens and kings count as 10.
	 */
	public int findCardValue(){
		int temp = 0;
		
		switch (this.rank){
		case 1: temp = 1;
				break;
		case 2: temp = 2;
				break;
		case 3: temp = 3;
				break;
		case 4: temp = 4;
				break;
		case 5: temp = 5;
				break;
		case 6: temp = 6;
				break;
		case 7: temp = 7;
				break;
		case 8: temp = 8;
				break;
		case 9: temp = 9;
				break;
		case 10: temp = 10;
				break;
		case 11: temp = 10;
				break;
		case 12: temp = 10;
				break;
		case 13: temp = 10;
				break;
		case 14: temp = 11;
				break;
		}
		
		return temp;
	}

	/*
	 * Name:
	 * Return:
	 * Purpose:
	 */
	public boolean isGreaterThan(Card c){
		if (this.rank > c.rank)
			return true;
		else
			return false;
	}
	
	/*
	 * Name: equals
	 * Return:	boolean
	 * Purpose:	Compares the rank of current card to another card
	 */
	public boolean equals(Card c){
		if (this.rank == c.rank)
			return true;
		else
			return false;
	}
	
	/*
	 * Name: toString
	 * Return:	String
	 * Purpose:	Format the objects into an easy to read output to dislay all info
	 */
	public String toString(){
		String temp = "";
		
		temp = this.findFaceValue() + " of " + this.suit;
		
		return temp;
	}
	
}
