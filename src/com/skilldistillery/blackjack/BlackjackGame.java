package com.skilldistillery.blackjack ;

import java.util.InputMismatchException ;

public class BlackjackGame {

	protected BlackjackPlayer player ;

	protected BlackjackDealer dealer ;

	private boolean first;

	private Outcome o ;

	public BlackjackGame( ) {

		this.first = true ;
		this.player = new BlackjackPlayer() ;

	}

	public static void main( String[] args ) {

		java.util.Scanner kb = new java.util.Scanner( System.in ) ;
		BlackjackGame game = new BlackjackGame() ;
		boolean stillPlaying ;

		do {
			stillPlaying = game.play( kb ) ;
		} while ( stillPlaying ) ;

		game = null ;
		kb.close() ;

	}

	public boolean play( java.util.Scanner kb ) {
		
		if ( this.first ) {
			this.getShoeSize( kb );
			this.first = false;
		}

		this.o = Outcome.DEALERWINS ;

		//TODO Implement a monetary system so players can place bets on their hands.
		//TODO Allow multiple players in the game
		//TODO provide the running HiLo count before the player is prompted to make betting decisions
		
		dealInitialHands() ;
		playerPlays( kb ) ;
		if ( this.o == Outcome.DEALERWINS ) {
			dealerPlays() ;
		}

		printResults() ;
		
		this.dealer.nextShoeCheck() ;

		return playAgain( kb );
		
	}

	private void getShoeSize( java.util.Scanner kb ) {

		int shoeSize;
		System.out.println( "How many decks would you like to play with?" );
		System.out.print( "(Common shoe sizes are 1, 2, 6 and 8 decks) " );
		try {
		shoeSize = kb.nextInt();
		kb.nextLine();
		if ( shoeSize < 1 ) { throw new InputMismatchException(); }
		this.dealer = new BlackjackDealer( shoeSize ) ;
		} catch ( InputMismatchException e ) {
			System.out.println( "Invalid input. Please enter a positive number of decks." ) ;
			getShoeSize( kb );
		}

	}

	private void dealInitialHands() {

		this.player.hand.clear() ;
		this.dealer.hand.clear() ;

		for ( int i = 0 ; i < 2 ; i++ ) {

			this.dealer.dealCard( player ) ;
			this.dealer.dealCard( dealer ) ;

		}

	}

	private void playerPlays( java.util.Scanner kb ) {

		boolean stillPlaying = true ;

		/* Stretch goal:
		 * "Blackjack" hand - if the user is initially dealt an Ace and a card with the
		 * value 10, this is a blackjack. The user immediately wins unless the dealer
		 * also has a blackjack (two-card hand with an Ace and a 10-value card).
		 */
		
		if ( dealer.hand.isBlackjack() && !player.hand.isBlackjack() ) {
			
			// default outcome value is DEALERWINS so need to set it
			return ;

		}

		if ( player.hand.isBlackjack() ) {

			if ( dealer.hand.isBlackjack() ) {

				this.o = Outcome.PUSH ;
				return ;

			} else {

				this.o = Outcome.PLAYERWINS ;
				return ;

			}

		}
		while ( stillPlaying ) {

			stillPlaying = playerChoice( kb ) ;

			if ( player.hand.isBust() ) {

				// default outcome value is DEALERWINS so need to set it
				return ;

			}

		}

	}

	private boolean playerChoice( java.util.Scanner kb ) {
		//TODO add another menu option to double down
		//TODO figure out how to implement splitting
		boolean keepGoing ;
		int input ;
		System.out.println( "You have: " ) ;
		this.player.printHand() ;
		System.out.println( "\n" ) ; // yes, two lines
		System.out.println( "The dealer is showing: " ) ;
		this.dealer.printHand(false);
		System.out.println( "* 1. Hit (take another card)    *" ) ;
		System.out.println( "* 2. Stand (take no more cards) *" ) ;
		System.out.printf( "What would you like to do? " ) ;
		try {
			input = kb.nextInt() ;
			kb.nextLine() ;
			if ( input < 1 || input > 2 ) {

				throw new InputMismatchException() ;

			}

			if ( input == 1 ) {

				this.dealer.dealCard( player ) ;

				if ( this.player.hand.isBust() || this.player.hand.getHandValue() == 21 ) {
					keepGoing = false ;
				} else {
					keepGoing = true ;
				}

			} else {
				keepGoing = false ;
			}

		} catch ( InputMismatchException e ) {

			System.out.println( "Invalid input. Please select one of the listed options." ) ;
			keepGoing = playerChoice( kb ) ;
			// better than letting a typo hang the program

		}

		return keepGoing ;

	}

	private void dealerPlays() {

		if ( this.player.hand.isBust() ) {
			this.o = Outcome.DEALERWINS ;
		} else {
			this.dealer.play() ;
			if ( this.dealer.hand.isBust() || ( this.player.hand.getHandValue() > this.dealer.hand.getHandValue() ) ) {
				this.o = Outcome.PLAYERWINS ;
			} else if ( this.player.hand.getHandValue() == this.dealer.hand.getHandValue() ) {

				this.o = Outcome.PUSH ;

			}
		}

		return ;

	}

	private void printResults() {
		System.out.println( "Player:" ) ;
		this.player.printHand();
		System.out.println( "Dealer:" ) ;
		this.dealer.printHand();

		String modifier = "" ;

		switch ( this.o ) {
			case PLAYERWINS :
				if ( this.player.hand.isBlackjack() ) {
					modifier = " with a blackjack" ;
				} else if ( this.dealer.hand.isBust() ) {
					modifier = String.format( " with %d: dealer broke" , this.player.hand.getHandValue() ) ;
				} else {
					modifier = String.format( ": %d beats %d" , this.player.hand.getHandValue() ,
							this.dealer.hand.getHandValue() ) ;
				}

				System.out.printf( "Player wins%s!%n" , modifier ) ;
				break ;
			case PUSH :
				if ( this.player.hand.isBlackjack() ) {
					modifier = " with two blackjacks" ;
				} else {
					modifier = String.format( ": %d to %d" , this.player.hand.getHandValue() , this.dealer.hand.getHandValue() ) ;
				}
				System.out.printf( "This hand pushed%s.%n" , modifier ) ;
				break ;
			case DEALERWINS :
				if ( this.dealer.hand.isBlackjack() ) {
					modifier = " with blackjack" ;
				} else if ( this.player.hand.isBust() ) {
					modifier = ": player busted" ;
				} else {
					modifier = String.format( ": %d to %d" , this.dealer.hand.getHandValue() , this.player.hand.getHandValue() ) ;
				}
				System.out.printf( "Dealer wins%s.%n" , modifier ) ;
				break ;
		}

	}
	
	private boolean playAgain ( java.util.Scanner kb ) {
		System.out.print( "Would you like to play again? Y / N: " );
		char yesNo = kb.nextLine().charAt(0);
		switch (yesNo) {
			case 'Y':
			case 'y':
				System.out.println( "Dealing next hand... (press enter)" ) ;
				kb.nextLine();
				return true;
			case 'N':
			case 'n':
				System.out.println( "Come back soon!" ) ;
				return false;
			default:
				return playAgain( kb );
		}
	}

}
