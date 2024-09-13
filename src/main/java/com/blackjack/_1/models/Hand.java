package com.blackjack._1.models;

import java.util.ArrayList;
import java.util.stream.Collectors;
public class Hand
{
    public ArrayList<Card> cards;
    public Hand()
    {
        cards = new ArrayList<Card>();
    }
    public void clearhand()
    {
        cards.clear();
    }
    public void add(Card card)
    {
        cards.add(card);
    }
     public String showHand() {
        return cards.stream()
            .map(Card::toString)  // Replace this with card.toString() or getCardImageURL() based on context
            .collect(Collectors.joining(", "));
    }
    public void flipCards()
    {
        for(Card c: cards)
        {
            c.flipCard();
        }
    }
    public boolean give(Card card, Hand otherHand)
    {
        if(!cards.contains(card))
        {
            return false;
        }
        else
        {
            cards.remove(card);
            otherHand.add(card);
            return true;
        }
    }
    public int getTotal() {
        int totalPoints = 0;
        boolean hasAce = false;
    
        // Loop through cards and calculate total points
        for (Card card : cards) {
            totalPoints += card.getRank(); // Assuming getRank() returns the correct point value
    
            // Check if there's an Ace
            if (card.PrintRank().equals("Ace")) {
                hasAce = true;
            }
        }
    
        // Adjust for Ace: if the total points are <= 11 and there's an Ace, add 10 to make Ace 11
        if (hasAce && totalPoints <= 11) {
            totalPoints += 10;
        }
    
        return totalPoints;
    }
    
}   
