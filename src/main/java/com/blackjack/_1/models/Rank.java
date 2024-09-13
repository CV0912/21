package com.blackjack._1.models;

public enum Rank 
{
    ACE(1,"Ace"),TWO(2,"2"), THREE(3,"3"), FOUR(4,"4"), FIVE(5,"5"), SIX(6,"6"), SEVEN(7,"7"), EIGHT(8,"8"), NINE(9,"9"), TEN(10,"10"),
    JACK(10,"Jack"), QUEEN(10,"Queen"), KING(10,"King");
    private final int value;
    private final String rank;
    private Rank(int value,String rank)
    {
        this.value = value;
        this.rank = rank;
    }
    public int getRank()
    {
        return value;
    }
    public String PrintRank()
    {
        return rank;
    }
}
