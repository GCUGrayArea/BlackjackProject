package com.skilldistillery.blackjack ;

import com.skilldistillery.common.cards.Card ;

public class BlackjackPlayer {

	/*
	 * Not ideal that hand isn't private, but it seemed like the least awful fix for
	 * a problem. If I make it private, the BlackjackDealer class that extends this
	 * one (and thus inherits the hand field) has an important field that its own
	 * methods can't access. At least it's not public.
	 */

	protected BlackjackHand hand ;

	public BlackjackPlayer( ) {

		this.hand = new BlackjackHand() ;

	}

	public void play() {

	}

	public void takeCard( Card c ) {

		this.hand.addCard( c ) ;

	}

//	public BlackjackHand getHand() {
//
//		return this.hand ;
//
//	}

	public void printHand() {

		String topLine = "┌─────────┐" ;
		String bottomLine = "└─────────┘" ;
		String topLeftCorner = "|%c%c       |" ;
		String bottomRightCorner = "|       %c%c|" ;
		String middleLine = "|    %c    |" ;
		String blankLine = "|         |" ;

		String[] cardsToPrint = new String[ 9 ] ;
		
		for ( int i = 0; i < cardsToPrint.length ; i++ ) {
			cardsToPrint[i] = "";
		}

		for ( Card c : this.hand.getCards() ) {

			char[] info = c.printingInfo() ;

			cardsToPrint[ 0 ] += topLine ;
			cardsToPrint[ 1 ] += String.format( topLeftCorner , info[ 0 ] , info[ 1 ] ) ;
			cardsToPrint[ 2 ] += blankLine ;
			cardsToPrint[ 3 ] += blankLine ;
			cardsToPrint[ 4 ] += String.format( middleLine , info[ 1 ] ) ;
			cardsToPrint[ 5 ] += blankLine ;
			cardsToPrint[ 6 ] += blankLine ;
			cardsToPrint[ 7 ] += String.format( bottomRightCorner , info[ 1 ] , info[ 0 ] ) ;
			cardsToPrint[ 8 ] += bottomLine ;

		}
		
		for ( String row : cardsToPrint ) {
			System.out.println( row ) ;
		}

	}

}
