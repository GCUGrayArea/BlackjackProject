package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.Deck ;

public class DeckPrintTest {
	
	public static void main( String[] args ) {
		Deck deck = new Deck();

		String topLine = "┌─────────┐" ;
		String bottomLine = "└─────────┘" ;
		String topLeftCorner = "|%c%c       |" ;
		String bottomRightCorner = "|       %c%c|" ;
		String middleLine = "|    %c    |" ;
		String blankLine = "|         |" ;
		String backPatternLine = "|░░░░░░░░░|" ;
		
		for ( int i = 0; i < 52 ; i++ ) {
			char[] info = deck.dealCard().printingInfo() ;
			System.out.println( topLine ) ;
			System.out.printf( topLeftCorner , info[ 0 ] , info[ 1 ] ) ;
			System.out.println(  ) ;
			System.out.println( blankLine );
			System.out.println( blankLine);
			System.out.println( String.format( middleLine , info[ 1 ] ) ) ;
			System.out.println(  ) ;
			System.out.println( blankLine );
			System.out.println( blankLine);
			System.out.printf( bottomRightCorner , info[ 1 ] , info[ 0 ] ) ;
			System.out.println(  ) ;
			System.out.println( bottomLine );
			
		}
		
	}
	

//	String[] cardsToPrint = new String[ 9 ] ;
//	
//	for ( int i = 0; i < cardsToPrint.length ; i++ ) {
//		cardsToPrint[i] = "";
//	}



}
