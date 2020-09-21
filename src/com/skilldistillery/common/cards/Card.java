package com.skilldistillery.common.cards;

import java.util.HashMap ;

public class Card {
	//DONE: Create a class called Card.
	//DONE:	A card has a Suit and Rank. Set these in the constructor.
	private Rank rank;
	private Suit suit;
	private HashMap<Suit, Character> suitchars;
	
	
	
	public Card(String rank, String suit) {
		
		this.rank = Rank.valueOf( rank );
		this.suit = Suit.valueOf( suit );
		this.suitchars = new HashMap<Suit, Character>();
		this.suitchars.put( Suit.SPADES , '\u2660' );
		this.suitchars.put( Suit.HEARTS , '\u2665' );
		this.suitchars.put( Suit.DIAMONDS , '\u2666' );
		this.suitchars.put( Suit.CLUBS , '\u2663' );
				
	}
	
	//DONE: Generate the methods hashCode and equals
	
	@Override
	public int hashCode() {

		final int prime = 31 ;
		int result = 1 ;
		result = prime * result + ( ( rank == null ) ? 0 : rank.hashCode() ) ;
		result = prime * result + ( ( suit == null ) ? 0 : suit.hashCode() ) ;
		return result ;

	}
	
	@Override
	public boolean equals( Object obj ) {

		if ( this == obj )
			return true ;
		if ( !( obj instanceof Card ) )
			return false ;
		Card other = ( Card ) obj ;
		if ( rank != other.rank )
			return false ;
		if ( suit != other.suit )
			return false ;
		return true ;

	}
	//DONE: Add a toString which says rank + " of " + suit.
	public String toString() {
		return this.rank.toString() + " of " + this.suit.toString();
	}
	
	//DONE: Add a method getValue to return the card's numeric value.
	
	public int getValue() {
		return this.rank.getValue();
	}
	
	public char[] printingInfo() {
		char[] outArr = new char[2];
		
		if (this.rank.getValue() < 10 ) {
			outArr[0] = ("" + this.rank.getValue()).charAt( 0 );
		} else {
			outArr[0] = this.rank.name().charAt( 0 );
		}
		
		outArr[1] = this.suitchars.get(this.suit);

//		switch ( this.suit ) {
//			case SPADES:
//				outArr[1] = '\u2660';
//				break;
//			case HEARTS:
//				outArr[1] = '\u2665';
//				break;
//			case DIAMONDS:
//				outArr[1] = '\u2666';
//				break;
//			case CLUBS:
//				outArr[1] = '\u2663';
//				break;
//		}
//		
		return outArr;
		
	}
}
