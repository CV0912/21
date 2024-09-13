package com.blackjack._1.models;

import java.util.Random;
public class Deck extends Hand
{
    Random rand = new Random();
    public void populate(int n)
    {
        while(n>0)
        {
            for(Suit suit: Suit.values())
            {
            for(Rank rank: Rank.values())
            {
                             
                Card card = new Card(rank,suit);
                this.add(card);

            }
            }
            n--;
        }
    }   
    public void shuffle()
    {
        for(int i=cards.size()-1;i>0;i--)
        {
            //swap a random card between beg and end with the card at current index
            int pick = rand.nextInt(i);
            Card randCard = cards.get(pick);
            Card lastCard = cards.set(i,randCard);
            cards.set(pick,lastCard);
            
        }
    }
    public void deal(Hand[] hands,int perhand)
    {
        for(int i = 0;i<perhand;i++)
        {
            for(int j = 0;j< hands.length; j++)
            {
                this.give(cards.get(0),hands[j]);
            }
        }
    }
    public void deal(Hand hand,int perhand)
    {
        for(int i=0;i<perhand;i++)
        {
            this.give(cards.get(0),hand);
        }
    }
    
    public void flipCard(Card c)
    {
        c.flipCard();
    }
    public void dealFaceUP(Hand[] hands,int perhand)
    {
        for(int i=0;i<perhand;i++)
        {
            for(int j=0;j<hands.length;j++)
            {
                flipCard(cards.get(0));
                this.give(cards.get(0),hands[j]);
            }
        }
    }
    public void dealFaceUP(Hand hand,int perhand)
    {
        for(int i=0;i<perhand;i++)
        {
            flipCard(cards.get(0));
            this.give(cards.get(0),hand);
        }
    }
}
