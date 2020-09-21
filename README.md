# SD27 Homework, Week 4: Blackjack

## Blackjack project

### Overview

This program simulates a game of blackjack, with player, dealer and finite(s) deck of cards. The player can hit as many times as they please (before breaking) and the dealer will then play his hand according to common predefined rules (basically, hit up to a hand value of 17 or higher). Subsequent hands can be played using the same set of cards; the deck/s is/are only shuffled when less than half a deck of cards remains at the end of a hand.

### Topics

* Generics and `ArrayList`
  - `Collections.shuffle` is used to simulate the shuffling of an actual deck of cards. In order to simulate dealing from a set of cards whose order is set by the shuffle, an `ArrayList` is used to provide a fixed order of cards, and cards are dealt from index zero.
* Enumerated types
  - The standard deck of cards used is constructed by taking the Cartesian product of two distinct `enum`s defining the `rank` and `suit` of each `Card`.
* Collection Tuning
  - Since the number of cards for a shoe size is known in advance, the capacity of the list that stores the shoe of cards is set to `52 * (number of decks)` so no re-allocation of memory is required while the program runs.
* `Map` Interface
  - A `HashMap` from the enumerated type `Suit` to the Unicode characters representing the suits of common playing cards is used when printing the contents of each participant's hand to the console.

### How to Run

1. Compile all `.java` files in the `com.skilldistillery.common.cards` and `com.skilldistillery.blackjack` packages with `javac` or load the package in Eclipse or another IDE.
2. Run the file from the command line with `java BlackjackGame`, or with your IDE's run tools.
3. Enter the inputs requested by the program.
4. ~~PROFIT!~~ Look at console output for the answers you seek.
