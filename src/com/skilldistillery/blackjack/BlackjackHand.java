package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.Card ;
import com.skilldistillery.common.cards.Hand ;

public class BlackjackHand extends Hand {
	
	public BlackjackHand() {
		
		super();
		
	}

	@Override
	public int getHandValue() {
		
		int value = 0;
		
		for ( Card c : this.getCards() ) {
			
			value += c.getValue();
			
		}
		
		return value;

	}
	
	public boolean isBlackjack() {
		
		return (this.getCards().size() == 2 && this.getHandValue() == 21);
		
	}
	
	public boolean isBust() {
		
		return ( this.getHandValue() > 21 );
		
	}
	
}
