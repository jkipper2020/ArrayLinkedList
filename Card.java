import java.util.Random;

/**
 * This is the card object class which creates the object of a Card that is used for the array part of teh program
 * @author Jonathan Kipper
 * @version 5/18/2020
 */
public class Card implements Comparable<Card>
{
    //fields
    private int power, toughness;

    /**
     * This is the default constructor for the card object.
     */
    public Card()
    {
        Random myRandom = new Random();

        power = myRandom.nextInt(1000) + 1;
        toughness = myRandom.nextInt(1000) + 1;
    }

    /**
     * This ia a constructor for the card that inputs 1 integer parameter and sets toughness/power equal to it
     * @param x this is an integer input which becomes the power and toughness for the card
     */
    public Card(int x)
    {
        if (x < 1 || x > 1000)
        {
            throw new IndexOutOfBoundsException("value not between 1 and 1000");
        }
        else
        {
            power = x;
            toughness = x;
        }
    }

    /**
     * This is the last constructor for the card class which allows 2 different integer values to be passed to it which
     * define the toughness and power of the car
     * @param pow this is the power for the card
     * @param tough this is the toughness for the card
     */
    public Card(int pow, int tough)
    {
        //checks if inputs are in acceptable value range
        if (pow < 1 || pow > 1000)
        {
            throw new IndexOutOfBoundsException("value not between 1 and 1000");
        }
        else if (tough < 1 || tough > 1000)
        {
            throw new IndexOutOfBoundsException("value not between 1 and 1000");
        }

        power = pow;
        toughness = tough;
    }

    /**
     * THis method returns the card's power value
     * @return an interger value representing the card's pwoer
     */
    public int getPower()
    {
        return power;
    }

    /**
     * This method will get and return the card's toughness value
     * @return an integer value representing the card's toughness
     */
    public int getToughness()
    {
        return toughness;
    }

    /**
     * This method calculates the cost value and returns it by rounding upwards, whose value is depending on the card's
     * power and toughness values
     * @return an integer value which is equal to the card's cost
     */
    public int getCost()
    {
        return (int) Math.ceil(Math.pow((1.2 * power + (0.75*toughness)), 0.75));
    }

    @Override
    public String toString()
    {
        return "[" + power + "/" + toughness + "::" + getCost() + "]";
    }

    @Override
    public int compareTo(Card x)
    {
        if (getCost() != x.getCost()) //if the two cards don't have the same cost
        {
            if (getCost() < x.getCost()) //if second card is bigger
            {
                return 1;
            }
            else //if first card is bigger
            {
                return -1;
            }
        }
        else if ((getCost() == x.getCost()) && (power != x.getPower()))
        {
            if (power < x.getPower()) //if second card is bigger
            {
                return 1;
            }
            else //if first card is bigger
            {
                return -1;
            }
        }
        else if ((getCost() == x.getCost()) && (power == x.getPower()) && (toughness != x.getToughness()))
        {
            if (toughness < x.getToughness()) //if second card is bigger
            {
                return 1;
            }
            else //if first card is bigger
            {
                return -1;
            }
        }
        else
        {
            return 0; //if they're equal
        }
    }

    /**
     * This method takes the card and permanetly reduces its power and toughness by 10% and rounds it upwards
     */
    public void weaken()
    {
        power = (int) Math.ceil(power * 0.9);
        toughness = (int) Math.ceil(power * 0.9);
    }

    /**
     * this methos takes the card and permanetly increases its power and toughness by 10% and rounds it upwards
     */
    public void boost()
    {
        power = (int) Math.ceil(power * 1.1);
        toughness = (int) Math.ceil(power * 1.1);
    }
}