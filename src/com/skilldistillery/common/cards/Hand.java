package com.skilldistillery.common.cards ;

import java.util.ArrayList ;

public abstract class Hand {

	private ArrayList < Card > cards ;

	public Hand( ) {

		clear() ; //no reason to have the new ArrayList<> call twice

	}

	public void addCard( Card c ) {

		this.cards.add( c ) ;

	}

	public abstract int getHandValue() ;

	@Override
	public String toString() {

		String strOut = "" ;
		for ( Card c : this.cards ) {
			strOut += String.format(
					"%s%n" ,
					c.toString() ) ;
		}

		return strOut ;

	}

	public void clear() {

		this.cards = new ArrayList < Card >() ;

	}
	
	public ArrayList<Card> getCards() {
		
		return this.cards;
	
	}

}
