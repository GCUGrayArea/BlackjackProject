package com.skilldistillery.common.cards ;

import java.util.ArrayList ;
import java.util.InputMismatchException ;

public class Dealing {

	private Deck deck ;
	private ArrayList<Card> hand ;

	public static void main( String[] args ) {

		java.util.Scanner kb = new java.util.Scanner( System.in ) ;
		Dealing app = new Dealing() ;
		app.launch( kb ) ;

	}

	public Dealing( ) {

		this.deck = new Deck() ;
		this.deck.shuffle() ;
		this.hand = new ArrayList<Card>();

	}

	public void deal( int cards ) {

		for ( int i = 0 ; i < cards ; i++ ) {
			this.hand.add( this.deck.dealCard() ) ;
		}

	}

	public int getInput( java.util.Scanner kb ) {

		int input ;
		try {
			System.out.print( "How many cards would you like? " ) ;
			input = kb.nextInt() ;
			kb.nextLine() ;
			if ( input < 0 || input > 52 ) {
				throw new InputMismatchException() ;
			}
		} catch ( InputMismatchException e ) {
			System.out.println(
					"Invalid input. Please enter a number of cards to deal, greater than zero less than or equal to 52. " ) ;
			input = getInput( kb ) ;
		}

		return input ;

	}

	public void launch( java.util.Scanner kb ) {

		this.deal( getInput( kb ) ) ;

		int value = 0 ;
		for ( Card c : this.hand ) {
			System.out.println( c ) ;
			value += c.getValue() ;
		}
		System.out.printf( "Total hand value: %d" , value ) ;

	}

}
