package com.skilldistillery.blackjack;

import java.util.InputMismatchException ;

import com.skilldistillery.common.cards.Card ;

public class BlackjackGame {
	
	protected BlackjackPlayer player;
	
	protected BlackjackDealer dealer;
	
	private Outcome o;
	
	public BlackjackGame() {
		
		this.o = Outcome.DEALERWINS;
		/* Defaulting to this will simplify some  of the control flow in
		 * play(), and in any case everyone knows the house always wins.
		 * Fields are constructed inside play a */
		
	}
	
	public static void main( String[] args ) {
		java.util.Scanner kb = new java.util.Scanner( System.in );
		BlackjackGame game = new BlackjackGame();
		game.play( kb );
		
		game = null;
		kb.close();
	}
	
	public void play ( java.util.Scanner kb ) {
		
		this.player = new BlackjackPlayer();
		this.dealer = new BlackjackDealer();
		
		dealInitialHands();
		playerPlays( kb );
		if ( this.o == Outcome.DEALERWINS ) {
			dealerPlays();
		}
		
		printResults();
		
	}


	private void dealInitialHands() {
		
		for (int i = 0; i < 2; i++) {
			
			dealer.dealCard( player );
			dealer.dealCard( dealer );
			
		}
		
	}
	
	private void playerPlays( java.util.Scanner kb ) {
		
		boolean stillPlaying = true;
		
		if ( dealer.getHand().isBlackjack() && !player.getHand().isBlackjack() ) {
			
			//default outcome value is DEALERWINS so need to set it
			return;
			
		}
		
		if ( player.getHand().isBlackjack() ) {
			
			if ( dealer.getHand().isBlackjack() ) {
				
				this.o = Outcome.PUSH;
				return;
				
			} else {
				
				this.o = Outcome.PLAYERWINS;
				return;
				
			}
			
		}
		while ( stillPlaying ) {
			
			stillPlaying = playerChoice( kb );
			
			if ( player.getHand().isBust() ) {
				
			//default outcome value is DEALERWINS so need to set it
				return;
				
			}
			
		}
		
	}
	
	private boolean playerChoice( java.util.Scanner kb ) {
		boolean keepGoing;
		int input;
		
		printPlayerHand();
		System.out.println( "\n" ) ; //yes, two lines
		System.out.printf(
				"The dealer is showing %s%n." ,
				this.dealer.getUpcard() ) ;
		System.out.println( "* 1. Hit (take another card) *" ) ;
		System.out.println( "* 2. Stand (take no more cards) " ) ;
		System.out.printf( "What would you like to do? " ) ;
		try {
			input = kb.nextInt();
			kb.nextLine();
			if ( input < 1 || input > 2 ) {
				
				throw new InputMismatchException();
				
			}
			
			if ( input == 1 ) {
				
				this.dealer.dealCard( player );
				
				if (this.player.getHand().isBust() ) {
					keepGoing = false;
				} else {
					keepGoing = true;
				}
				
			} else {
				keepGoing = false;
			}
			
		} catch ( InputMismatchException e ) {
			
			System.out.println( "Invalid input. Please select one of the listed options." ) ;
			keepGoing = playerChoice( kb );
			//better than letting a typo hang the program
			
		}
		
		return keepGoing;
	
	}

	private void printPlayerHand() {
		
		for ( Card c : this.player.getHand().getCards() ) {
			System.out.println( c ) ;
		}

	}
	
	private void dealerPlays() {
		
		
		this.dealer.play();
		if ( this.dealer.getHand().isBust() ||
					( this.player.getHand().getHandValue() > this.dealer.getHand().getHandValue() ) ) {
			this.o = Outcome.PLAYERWINS;
		} else if ( this.player.getHand().getHandValue() ==
					this.dealer.getHand().getHandValue() ) {
				
			this.o = Outcome.PUSH;
		
		}
		
		return;
		
	}
	
	private void printResults() {
		String modifier = "";
		
		switch ( this.o ) {
			case PLAYERWINS:
				if ( this.player.getHand().isBlackjack() ) {
					modifier = " with a blackjack";
				} else if ( this.dealer.getHand().isBust() ) {
					modifier = String.format(
							" with %d: dealer broke" , 
							this.player.getHand().getHandValue() );
				} else {
					modifier = String.format(
							": %d beats %d" ,
							this.player.getHand().getHandValue() ,
							this.dealer.getHand().getHandValue() );
				}
				
				System.out.printf(
						"Player wins%s!" ,
						modifier) ;
				break;
			case PUSH:
				if ( this.player.getHand().isBlackjack() ) {
					modifier = " with two blackjacks";
				} else {
					modifier = String.format(
							": %d to %d", 
							this.player.getHand().getHandValue() ,
							this.dealer.getHand().getHandValue() );
				}
				System.out.printf(
					"This hand pushed%s." ,
					modifier );
				break;
			case DEALERWINS:
				if ( this.dealer.getHand().isBlackjack() ) {
					modifier = " with blackjack";
				} else if (this.player.getHand().isBust() ) {
					modifier = ": player busted";
				} else {
					modifier = String.format(
							": %d to %d" ,
							this.dealer.getHand().getHandValue() ,
							this.player.getHand().getHandValue() );
				}
				System.out.printf( 
						"Dealer wins%s." );
				break;
		}
		
	}

}
