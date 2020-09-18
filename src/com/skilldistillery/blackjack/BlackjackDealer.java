package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.Card ;
import com.skilldistillery.common.cards.Deck ;

public class BlackjackDealer extends BlackjackPlayer {
	
	private Deck deck;
	
	public BlackjackDealer() {
		
		super();
		this.deck = new Deck();
		
	}
	
	@Override
	public void play() {
		
		while ( this.hand.getHandValue() < 17 ) {
			
			this.dealCard( this ); //will this work?
		
		}

	}
	
	public void dealCard( BlackjackPlayer p ) {
		
		p.takeCard( this.deck.dealCard() );
		
	}
	
	public Card getUpcard() {
		
		return this.hand.getCards().get( 0 );
		
	}

}
