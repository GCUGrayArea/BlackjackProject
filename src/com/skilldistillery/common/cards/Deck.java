package com.skilldistillery.common.cards ;

import java.util.ArrayList ;
import java.util.Collections ;

public class Deck {

	private ArrayList < Card > cards ;

	public Deck( ) {

		this.cards = new ArrayList < Card >( 52 ) ;
		for ( Suit s : Suit.values() ) {
			for ( Rank r : Rank.values() ) {
				this.cards.add( new Card( r.name() , s.name() ) ) ;
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

}
