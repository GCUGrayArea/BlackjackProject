package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.Card ;

public class BlackjackPlayer {
	
	protected BlackjackHand hand;
	
	public BlackjackPlayer() {
		
		this.hand = new BlackjackHand();
		
	}
	
	public void play() {
		
	}
	
	public void takeCard( Card c ) {
		
		this.hand.addCard( c );
		
	}
	
	public BlackjackHand getHand() {
		
		return this.hand;
		
	}
	
	
}
