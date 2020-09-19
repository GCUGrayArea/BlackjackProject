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
		int aces = 0;
		
		for ( Card c : this.getCards() ) {
			
			value += c.getValue();
			
			//we need to know how many there are to check softness
			if ( c.getValue() == 11 ) {
				aces++;
			}
			
		}
		
		/*	Stretch goal
		 * "Soft" Ace - Ace has the value 1 or 11.
		 * 
		 * A dealer must Hit on a hand with the value "soft 17" (where an Ace is present
		 * with value 11 and other cards total 6) and Stay on "hard 17" (where an Ace is
		 * present, but hitting could cause the Dealer to go over 21)
		 */
		
		if ( value > 21 ) {
			while ( aces > 0 ) {
				aces--; //can't soften more aces than we have
				value -= 10; //one ace changes from 11 to 1
				if ( value <= 21 ) {
					break; //stop softening aces once they wouldn't bust the hand
				}
			}
		}
		
		return value; //isBust will still return true if softening aces didn't help

	}
	
	public boolean isBlackjack() {
		
		return (this.getCards().size() == 2 && this.getHandValue() == 21);
		
	}
	
	public boolean isBust() {
		
		return ( this.getHandValue() > 21 );
		
	}
	
}
