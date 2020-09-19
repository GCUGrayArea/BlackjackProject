package com.skilldistillery.common.cards ;

import java.util.ArrayList ;
import java.util.Collections ;

public class Deck {

	private ArrayList < Card > cards ;

	public Deck( ) {

		this(1);

	}
	
	public Deck( int shoeSize ) {
		//Stretch goal: Deal from a multi-deck shoe.
		this.cards = new ArrayList < Card >( 52 * shoeSize ) ;
		
		for ( int i = 0 ; i < shoeSize ; i++ ) {
			for ( Suit s : Suit.values() ) {
				for ( Rank r : Rank.values() ) {
					this.cards.add( new Card( r.name() , s.name() ) ) ;
				}
			}
		}
		
	}

	public int checkDeckSize() {

		return this.cards.size() ;

	}

	public Card dealCard() {

		return this.cards.remove( 0 ) ;

	}

	public void shuffle() {

		Collections.shuffle( this.cards ) ;

	}
	
	/*
	 * Used this to test the shuffle method as suggested in User Story #2. Commented
	 * out because being able to just *read* the deck would be silly in any card
	 * game.
	 * 
	 * public String toString() {
	 * 
	 * String strOut = "";
	 * 
	 * for (Card c : this.cards ) {
	 * 
	 * strOut += String.format( "%s%n" , c.toString() );
	 * 
	 * }
	 * 
	 * return strOut;
	 * 
	 * }
	 */

}
