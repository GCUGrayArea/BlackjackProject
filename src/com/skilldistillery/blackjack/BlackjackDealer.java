package com.skilldistillery.blackjack ;

import com.skilldistillery.common.cards.Card ;
import com.skilldistillery.common.cards.Deck ;

public class BlackjackDealer extends BlackjackPlayer {

	private Deck deck ;
	private int shoeSize ;
	public boolean played = false;

//	public BlackjackDealer () {
//		
//		new BlackjackDealer( 1 );
//		
//	}

	public BlackjackDealer( int shoeSize ) {

		super() ;
		this.shoeSize = shoeSize ; // storing preserves size of shoe between shuffles
		( this.deck = new Deck( shoeSize ) ).shuffle() ;

	}

	@Override
	public void play() {

		while ( this.hand.getHandValue() < 17 ) {

			this.dealCard( this ) ; // will this work?

		}

	}

	public void dealCard( BlackjackPlayer p ) {

		p.takeCard( this.deck.dealCard() ) ;

	}

	public Card getUpcard() {

		return this.hand.getCards().get( 0 ) ;

	}

	public void nextShoeCheck() {

		if ( this.deck.checkDeckSize() < 26 ) {

			System.out.println( "Less than half a deck remaining - shuffling a new shoe" ) ;
			this.deck = new Deck( shoeSize ) ;

		}

	}

	public void printHand(boolean played) {

		if ( this.hand.getCards().size() == 2 && !played ) {
			String topLine = "┌─────────┐" ;
			String bottomLine = "└─────────┘" ;
			String topLeftCorner = "|%c%c       |" ;
			String bottomRightCorner = "|       %c%c|" ;
			String middleLine = "|    %c    |" ;
			String blankLine = "|         |" ;
			String backPatternLine = "|░░░░░░░░░|" ;

			String[] cardsToPrint = new String[ 9 ] ;
			
			for ( int i = 0; i < cardsToPrint.length ; i++ ) {
				cardsToPrint[i] = "";
			}

			char[] info = this.getUpcard().printingInfo() ;

			cardsToPrint[ 0 ] += topLine ;
			cardsToPrint[ 1 ] += String.format( topLeftCorner , info[ 0 ] , info[ 1 ] ) ;
			cardsToPrint[ 2 ] += blankLine ;
			cardsToPrint[ 3 ] += blankLine ;
			cardsToPrint[ 4 ] += String.format( middleLine , info[ 1 ] ) ;
			cardsToPrint[ 5 ] += blankLine ;
			cardsToPrint[ 6 ] += blankLine ;
			cardsToPrint[ 7 ] += String.format( bottomRightCorner , info[ 1 ] , info[ 0 ] ) ;
			cardsToPrint[ 8 ] += bottomLine ;

			cardsToPrint[ 0 ] += topLine ;
			cardsToPrint[ 1 ] += backPatternLine ;
			cardsToPrint[ 2 ] += backPatternLine ;
			cardsToPrint[ 3 ] += backPatternLine ;
			cardsToPrint[ 4 ] += backPatternLine ;
			cardsToPrint[ 5 ] += backPatternLine ;
			cardsToPrint[ 6 ] += backPatternLine ;
			cardsToPrint[ 7 ] += backPatternLine ;
			cardsToPrint[ 8 ] += bottomLine ;
			
			for ( String row : cardsToPrint ) {
				System.out.println( row ) ;
			}

		} else {
			super.printHand() ;
		}

	}

}
