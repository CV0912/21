package com.blackjack._1.models;

public enum Suit 
{
    SPADES("spades"),
    CLUBS("clubs"),
    HEARTS("hearts"),
    DIAMONDS("diamonds");
    private final String suitText;

    private Suit(String suitText)
    {
        this.suitText = suitText;
    }
    public String printsuit()
    {
        return suitText;
    }
}
