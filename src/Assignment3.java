import java.util.*;


public class Assignment3
{  
   public static void main(String[] args)
   {
 
      /* Card tests to make sure it works */
      /*----------------------------------*/
      System.out.println("");
      System.out.println("Card Class Tests:");
      
      Card cardClassCard1 = new Card('3', Card.Suit.clubs);
      Card cardClassCard2 = new Card('T', Card.Suit.clubs);
      Card cardClassCard3 = new Card('e', Card.Suit.hearts);
      
      System.out.println(cardClassCard1.toString());
      System.out.println(cardClassCard2.toString());
      System.out.println(cardClassCard3.toString());
      
      cardClassCard1.set('e', Card.Suit.clubs);
      cardClassCard3.set('A', Card.Suit.clubs);
      
      System.out.println("");
      System.out.println(cardClassCard1.toString());
      System.out.println(cardClassCard2.toString());
      System.out.println(cardClassCard3.toString());
      
      /* end Card tests
      /*----------------------------------*/
      
      /* Hand tests to make sure it works */
      /*----------------------------------*/
      System.out.println("");
      System.out.println("Hand Class Tests:");
      
      Card card1 = new Card('3', Card.Suit.clubs);
      Card card2 = new Card('T', Card.Suit.clubs);
      Card card3 = new Card('9', Card.Suit.hearts);
      Hand hand = new Hand();
      int counter = 0;
      
      // add max number of cards to hand
      for (int i = 0; i < Hand.MAX_CARDS; i++)
      {
         ++counter;
         switch(counter)
         {
            case 2:
               hand.takeCard(card2);
               break;
            case 3:
               hand.takeCard(card3);
               counter = 0;
               break;
            default:
               hand.takeCard(card1);
               break;
               
         }
      }
      
      // print out the entire hand
      System.out.println("Hand full");
      System.out.println("After deal:");
      System.out.println("Hand = " + hand.toString());
      System.out.println("");
      
      //examine card and show it
      System.out.println("Testing inspectCard():");
      Card testCard = hand.inspectCard(2);
      Card testCard2 = hand.inspectCard(101);
      System.out.println(testCard.toString());
      System.out.println(testCard2.toString());
      System.out.println("");
      
      // play every card in hand
      System.out.println("Play cards in Hand:");
      for(int i = hand.getNumCards(); i > 0; i--)
      {
         Card playedCard = hand.playCard();
         System.out.println(playedCard.toString());
      }
      
      // print out empty hand
      System.out.println("");
      System.out.println("After playing all cards:");
      System.out.println("Hand = " + hand.toString());
      
      /* end of hand test                 */
      /*----------------------------------*/
      
      
      /* Deck Tests to make sure it works
      /*----------------------------------*/
      
      System.out.println("");
      System.out.println("Deck Class Tests:");
      
      Deck deck = new Deck(2);
      int cardCount = deck.getTopCard();
      
      System.out.println(cardCount + ":");
      for(int i = 0; i < cardCount; i++)
      {
         Card card = deck.dealCard();
         System.out.println(card.toString());
      }
      
      deck.init(2);
      deck.shuffle();
      cardCount = deck.getTopCard();
      System.out.println("");
      System.out.println(cardCount + ":");
      for(int i = 0; i < cardCount; i++)
      {
         Card card = deck.dealCard();
         System.out.println(card.toString());
      }
      
      deck.init(1);
      cardCount = deck.getTopCard();
      System.out.println("");
      System.out.println(cardCount + ":");
      for(int i = 0; i < cardCount; i++)
      {
         Card card = deck.dealCard();
         System.out.println(card.toString());
      }
      
      deck.init(1);
      deck.shuffle();
      cardCount = deck.getTopCard();
      System.out.println("");
      System.out.println(cardCount + ":");
      for(int i = 0; i < cardCount; i++)
      {
         Card card = deck.dealCard();
         System.out.println(card.toString());
      }
      /* end Deck Tests
      /*----------------------------------*/
      
/**************************************************************************/      
      /*begin main 
      /*--------------------------------- */
      Scanner keyboard = new Scanner(System.in);
      int numPlayers = 0;
      Deck oneDeck = new Deck(1);
      Hand playerHand[];
      int cardCounter = 0;
      
      do
      {
         System.out.println("Please Select number of hands.  (1-10)");
         numPlayers = keyboard.nextInt();
      }
      while (numPlayers == 0 || numPlayers > 10);
      
      //initialize Hand array
      playerHand = new Hand[numPlayers];
      for (int i = 0; i < playerHand.length; i++)
         playerHand[i] = new Hand();
      
      //deal unshuffled deck
      while (cardCounter < 52)
         for (int i = 0; i < playerHand.length; i++)
         {            
            if(cardCounter != 52)
            {   
               playerHand[i].takeCard(oneDeck.dealCard());
               cardCounter++;
            }
         }
      cardCounter = 0;   
      
      System.out.println("Here are the hands from an unshuffled deck:");
      for (int i = 0; i < playerHand.length; i++)
         System.out.println(playerHand[i].toString());
      
      //shuffle and reset hands
      oneDeck.shuffle();
      
      for (int i = 0; i < playerHand.length; i++)
         playerHand[i].resetHand();
      
      //deal shuffled deck and display
      while (cardCounter < 52)
         for (int i = 0; i < playerHand.length; i++)
         {            
            if(cardCounter != 52)
            {   
               playerHand[i].takeCard(oneDeck.dealCard());
               cardCounter++;
            }
         }
      
      for (int i = 0; i < playerHand.length; i++)
         System.out.println(playerHand[i].toString());      
   }
}//end class assignment3


/*------------------------------------------------------
 * Card Class
 *---------------------------------------------------- */
class Card
{
   // mappings for valid cards
   public enum Suit{clubs, diamonds, hearts, spades};
   public static final char[] cardValue = {'A', '2', '3', '4', '5', '6', '7', 
      '8', '9', 'T', 'J', 'Q', 'K'};
   
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   //constructor with parameters takes a card value and suit
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   //default constructor sets value to "A" and suit to spades
   public Card()
   {
      set('A', Suit.spades);
   }
   
   //public method to return value and suit as a string
   public String toString()
   {
      if (errorFlag == true)
         return("[invalid]");
      return String.valueOf(value) + " of " + suit;
   }
   
   //return true if card value is valid
   private boolean isValid(char value, Suit suit)
   {

      for (int i = 0; i < cardValue.length; i++)
         if (value == cardValue[i])
            return true;
      
      return false;
   }
   
   //setter function 
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      else
      {
         errorFlag = true;
         return false;
      }
   }
   
   //accessor functions
   public char getValue()
   {
      return value;
   }
   
   public Suit getSuit()
   {
      return suit;
   }
   
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   public boolean equals(Card card)
   {
      if (card == this)
         return true;
      return false;
   }
}


/*------------------------------------------------------
 * Hand Class
 *---------------------------------------------------- */
class Hand
{
   
   // max number of cards allowed in hand
   public static final int MAX_CARDS = 100;
   
   private Card[] myCards = new Card[MAX_CARDS];
   private int numCards;
   
   // default constructor
   public Hand()
   {
      this.numCards = 0;
   }  
   
   /*
    * resetHand takes no parameters and fills the array with null
    * then sets the numCards back to 0
    * */
   public void resetHand()
   {
      
      Arrays.fill(myCards, null);
      numCards = 0;
      
   }
   
   /*
    * takeCard takes a Card and adds it to hand
    * returns true if successful. (makes new copy of card)
    * */
   public boolean takeCard(Card card)
   {
    
      if(numCards < MAX_CARDS)
      {
         Card addCard = new Card(card.getValue(), card.getSuit());
         myCards[numCards] = addCard;
         numCards++;
         return true;
      }
      
      return false;
   }
   
   /*
    * playCard plays card on top of the deck
    * and returns that card to the caller
    * */
   public Card playCard()
   {
      
      if(numCards == 0)
         return null;
      
      Card card = new Card(myCards[numCards -1].getValue(),
            myCards[numCards -1].getSuit());
      myCards[numCards -1] = null;
      numCards--;
      
      return card;
      
   }
   
   /*
    * toString Displays the hand as a string
    * */
   public String toString() 
   {
      
      String handOfCards = "(";
      
      for(int i = 0; i < numCards; i++)
      {
         handOfCards += (i == numCards - 1) ? myCards[i].toString() : 
            myCards[i].toString() + ", ";
      }
      
      handOfCards += ")";
      
      return handOfCards;
      
   }

   /*
    * getNumCards returns the number of cards in the hand
    * */
   public int getNumCards()
   {
      
      return numCards;
      
   }
   
   /*
    * inspectCard returns the card asked for,
    * if the card is out of bounds it returns a card with
    * errorFlag true
    * */
   public Card inspectCard(int k)
   {
      
      if(k >= 0 && k < numCards)
         return myCards[k];
      
      // send a bad card so error flag is set
      return new Card('e', Card.Suit.clubs);
      
   }
   
}


/*------------------------------------------------------
 * Deck Class
 *---------------------------------------------------- */
class Deck
{
   
   public static final int MAX_CARDS = 6*52;
   
   private static final int NUMBER_OF_CARDS = 52;
   private static Card[] masterPack = new Card[NUMBER_OF_CARDS];
   
   private Card cards[];//array of card object
   private int topCard;// index of next card to be dealt
   private int numPacks;// number of packs
  
   //private Random randomNumber;//random number generator;
   
   public Deck()
   {            
      this.numPacks = 1;
      
      // populate masterPack
      allocateMasterPack();
      
      // create the deck
      init(numPacks);
   }
   
   public Deck(int numPacks)
   {      
      // check it isn't over MAX_CARD limit
      if(NUMBER_OF_CARDS * numPacks > MAX_CARDS)
         numPacks = 1;
      
      // populate masterPack
      allocateMasterPack();
      
      // create the deck
      init(numPacks);
   }
   
   public void init(int numPacks) 
   {
      cards = new Card[NUMBER_OF_CARDS * numPacks];
      this.topCard = 0;
      this.numPacks = numPacks;
      int count = 0;
     
      for(int i = 0; i < cards.length; i++)
      { 
         cards[i] = new Card(masterPack[count].getValue(), 
               masterPack[count].getSuit());
         topCard++;
         count++;
         
         // count is 52 reset it back to 0 to start over
         if(count == 52)
            count = 0;     
      }
   }
   
   public void shuffle() 
   {
      for (int i = 0 ; i < cards.length; i++)
      {
         Card temp;
         Random randomGenerator = new Random();
         int randomCard = randomGenerator.nextInt(NUMBER_OF_CARDS * numPacks);
         
         temp = cards[i];
         cards[i] = cards[randomCard];
         cards[randomCard] = temp;
      }
   }
   
   public Card dealCard()
   {
      if(topCard == 0)
         return null;
      
      Card card = new Card(cards[topCard -1].getValue(),
            cards[topCard -1].getSuit());
      cards[topCard -1] = null;
      topCard--;
      
      return card;
   }
   
   public int getTopCard()
   {
      return topCard;
   }
   
   public Card inspectCard(int k)
   {
      
      if(k < cards.length)
         return cards[k];
      
      // send a bad card so error flag is set
      return new Card('e', Card.Suit.clubs);
      
   }
   
   
   /*private methods*/
   private static void allocateMasterPack() 
   {
      // if last card in masterPack isn't null,
      // it's already been initiated so return early
      if(masterPack[NUMBER_OF_CARDS -1] != null)
         return;
      
      Card.Suit suit;
      
      for(int i = 0; i < masterPack.length; i++)
      {  
         if(i < 13)
            suit = Card.Suit.spades;
         else if(i >= 13 && i < 26)
            suit = Card.Suit.clubs;
         else if(i >= 26 && i < 39)
            suit = Card.Suit.hearts;
         else
            suit = Card.Suit.diamonds;

         masterPack[i] = new Card(Card.cardValue[ i % 13 ], suit);
      }
   }
   
}//end class Deck()
