package com.blackjack._1;

import com.blackjack._1.models.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BlackJackService {
    private Deck deck;
    private ArrayList<Hand> hands;
    private Hand dealer;
    private int bal;
    private int currentbet;

    public BlackJackService() {
        startNewGame();
    }

    public void startNewGame() {
        deck = new Deck();
        deck.populate(1);
        deck.shuffle();
        hands = new ArrayList<>();
        dealer = new Hand();
        bal=10000;
    }
    public void NewGame()
{
    deck = new Deck();
    deck.populate(1);
    deck.shuffle();
    hands = new ArrayList<>();
    dealer = new Hand();
}


    public void addPlayer() {
        hands.add(new Hand());
    }

    public void dealInitialCards() {
        deck.dealFaceUP(hands.toArray(new Hand[0]), 2);
        deck.deal(dealer, 2);
    }
    // Generate the card image URL based on rank and suit
    private String getCardImageURL(Card card) {
        if (!card.isFaceUp()) {  // Check if the card is face down
            return "/cards/face_down.png";  // Return face-down image URL
        }
        String rank = card.PrintRank();  // Convert rank to lowercase
        String suit = card.getSuit();  // Convert suit to lowercase
        return "/cards/" + rank + "_of_" + suit + ".png";  // E.g., "/images/cards/ace_of_spades.png"
    }

    public String getPlayerHand(int playerIndex) {
        return hands.get(playerIndex).cards.stream()    // Use the cards field of the Hand object
            .map(this::getCardImageURL)                 // Convert each Card to its image URL
            .collect(Collectors.joining(" "));          // Return space-separated card image URLs
    }

    public String getDealerHand(boolean revealAll) {
        if (!revealAll) {
            dealer.cards.get(0).flipCard();  // Flip the first card down (hide it)
        }
        return dealer.cards.stream()         // Use the cards field of the dealer's hand
            .map(this::getCardImageURL)      // Convert each Card to its image URL
            .collect(Collectors.joining(" "));  // Return space-separated card image URLs
    }

    

    
    public boolean hit(int playerIndex) {
        // Deal a new card to the player
        int playerTotal = hands.get(playerIndex).getTotal();
        if(playerTotal<=21)
        {
            deck.dealFaceUP(hands.get(playerIndex), 1);
        }
        // Get the player's total hand value
        
    
        // Return true if the player busts (exceeds 21), false otherwise
        return playerTotal > 21;
    } 
    public void dealerTurn() {
    dealer.cards.get(1).flipCard(); // Reveal the dealer's second card
    while (dealer.getTotal() < 17) { // Use dealer.getTotal() to calculate the dealer's total
        deck.dealFaceUP(dealer, 1);  // Deal a new card to the dealer if total is less than 17
    }
}
public int getDealerTotal() {
    return dealer.getTotal();  // Use the Hand class method getTotal() to return dealer's total
}

public int getPlayerTotal(int playerIndex) {
    return hands.get(playerIndex).getTotal();  // Use the Hand class method getTotal() to return player's total
}
public void placeBet(int bet) {
    if (bet>0) {
        currentbet = bet;
        bal -= bet; // Deduct bet from balance
    } else {
        throw new IllegalArgumentException("Invalid bet amount.");
    }
}
public void resetBet() {
    currentbet = 0;
}
public int getBalance() {
    return bal;
}

// Method to set a new balance (e.g., reset or adjust)
public void setBalance(int amount) {
    this.bal = amount;
}

// Method to update balance based on a bet
public void updateBalance(boolean win) {
    if (win) {
        bal += currentbet * 2;  // If the player wins, they receive twice their bet
    } 
    // If the player loses, nothing is added (bet already deducted in placeBet)
    resetBet();  // Reset current bet after updating balance
}

public String determineWinner() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < hands.size(); i++) {
        int playerTotal = hands.get(i).getTotal();  // Use getTotal() to get player's total
        int dealerTotal = dealer.getTotal();        // Use getTotal() to get dealer's total

        if (dealerTotal > 21 && playerTotal<21) {
            result.append("Dealer busts! Player ").append(i + 1).append(" wins.\n");
            updateBalance(true);
        } else if (playerTotal > 21) {
            result.append("Player ").append(i + 1).append(" busts and loses.\n");
            updateBalance(false);
        } else if (playerTotal > dealerTotal) {
            result.append("Player ").append(i + 1).append(" wins.\n");
            updateBalance(true);
        } else if (playerTotal < dealerTotal && dealerTotal<=21) {
            result.append("Dealer wins against Player ").append(i + 1).append(".\n");
            updateBalance(false);
        } else {
            result.append("Player ").append(i + 1).append(" ties with the Dealer.\n");
            bal += currentbet;  // In a tie, the player's bet is returned
            resetBet();
        }
    }
    return result.toString();
}

    
}