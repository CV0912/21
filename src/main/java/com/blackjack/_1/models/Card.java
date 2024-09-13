package com.blackjack._1.models;

public class Card 
{
    private Suit suit;
    private Rank rank;
    public boolean isFaceUp;

    public Card(Rank rank,Suit suit)
    {
        this.rank=rank;
        this.suit=suit;
        isFaceUp = false;
    }
    public String getSuit()
    {
        return suit.printsuit();
    }
    public String PrintRank()
    {
        return rank.PrintRank();
    }
    public int getRank()
    {
        return rank.getRank();
    }
     public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flipCard() {
        isFaceUp = !isFaceUp;
    }
    public String toString()
    {
        String str="";
        if(isFaceUp)
        {
            str+=rank.PrintRank()+" of "+suit.printsuit();
        }
        else
        {
            str = "Face down";
        }
        return str;
    }
}
