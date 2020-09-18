package com.skilldistillery.common.cards;


public enum Rank {
	
	TWO(2) ,
	THREE(3) ,
	FOUR(4) ,
	FIVE(5) ,
	SIX(6) ,
	SEVEN(7) ,
	EIGHT(8) ,
	NINE(9) ,
	TEN(10) ,
	JACK(10) ,
	QUEEN(10) ,
	KING(10) ,
	ACE(11);
	
	Rank(int value) {
		this.value = value;
	}
	
	private int value;
	
	public int getValue() {
		return value;
	}

	public String toString() {
		StringBuilder capName = new StringBuilder();
		
		capName.append( this.name().toLowerCase() );
		capName.replace( 0 , 1 , "" + this.name().charAt( 0 ) );
		
		return capName.toString();
		
	}
}
