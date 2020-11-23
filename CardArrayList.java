import java.util.Arrays;
import java.util.Random;

/**
 * This is the CardArrayList and what it does is impliment the CardList.java interface and fills out all of the methods
 * from there in order to create an arraylist, and it also adds some other methods such as the expand() method. this fills
 * out all of the necessary methods to create an arraylist and uses the merge sort in order to sort the array efficiently.
 * @author Jonathan Kipper
 * @version 5/18/2020
 */
public class CardArrayList implements CardList
{
    //fields
    private static Card[] cardArray;
    private static int sizeCounter;

    /**
     * This is the default constructor for the CardArrayList and it sets the default size to 10
     */
    public CardArrayList()
    {
        cardArray = new Card[10];
        sizeCounter = 0;
    }

    /**
     * this is the second constructor for the list which allows a value to be passed along which determines the size of the initial array
     * @param x this is an integer which represents the value of the newely created array
     */
    public CardArrayList(int x)
    {
        if (x < 1)
        {
            throw new IllegalArgumentException("Array Size Cannot Be Less Than 1!");
        }
        else
        {
            cardArray = new Card[x];
            sizeCounter = 0;
        }
    }

    @Override
    public String toString()
    {
        if (sizeCounter == 0)
        {
            return "[0: :0]";
        }
        else
        {
            String val = "[0: ";

            for (int i = 0; i < sizeCounter; i++)
            {
                if (i == sizeCounter - 1)
                {
                    val += cardArray[i];
                }
                else
                {
                    val += cardArray[i] + ",";
                }
            }
            val += " :" + sizeCounter + "]";
            return val;
        }
    }

    //determines if there is enough room in the array for another object to be placed
    private boolean isRoom()
    {
        return sizeCounter != cardArray.length;
    }

    //expands the arraySize if there is not enough room to add the next element
    private void expand()
    {
        int newArraySize = cardArray.length * 2;
        cardArray = Arrays.copyOf(cardArray, newArraySize);
    }

    @Override
    public void clear()
    {
        cardArray = new Card[10];
        sizeCounter=0;
    }

    @Override
    public int size()
    {
        return sizeCounter;
    }

    @Override
    public void add(Card c)
    {
        if (!isRoom())
        {
            expand();
        }
        cardArray[sizeCounter++] = c;
    }

    @Override
    public void add(int loc, Card c)
    {
        if (loc > cardArray.length || loc < 0)
        {
            throw new IndexOutOfBoundsException("Specified Index out of bounds");
        }

        if (!isRoom())
        {
            expand();
        }

        for (int i = sizeCounter; i > 0; i--)
        {
            if (i == loc + 1)
            {
                cardArray[i] = cardArray[i - 1];
                cardArray[loc] = c;
                i--;
            }
            else
            {
                cardArray[i] = cardArray[i - 1];
            }
        }

        sizeCounter++;
    }

    @Override
    public Card remove()
    {
        Card tmp = cardArray[sizeCounter - 1];
        sizeCounter--;
        return tmp;
    }

    @Override
    public Card remove(int loc)
    {
        if (loc < 0 || loc > sizeCounter)
        {
            throw new IndexOutOfBoundsException("Element Out of array");
        }
        else
        {
            Card tmp = cardArray[loc];
            for (int i = loc; i < sizeCounter; i++)
            {
                if (i == sizeCounter - 1)
                {
                    cardArray[i] = cardArray[i + 1];
                    remove();
                }
                else
                {
                    cardArray[i] = cardArray[i + 1];
                }
            }
            return tmp;
        }
    }

    @Override
    public Card get(int i)
    {
        if (i < 0 || i > sizeCounter)
        {
            throw new IndexOutOfBoundsException("Element Out of array");
        }
        else
        {
            return cardArray[i];
        }
    }

    @Override
    public int indexOf(Card c)
    {
        for (int i = 0; i < sizeCounter; i++)
        {
            if (c.compareTo(cardArray[i]) == 0)
            {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void sort()
    {
        Card[] tmp = new Card[sizeCounter];
        tmp = Arrays.copyOfRange(cardArray, 0, sizeCounter);

        //runs the mergeSort
        mergeSort(tmp);
    }

    //uses recursion to split the main array into two different arrays
    private static void mergeSort(Card[] a)
    {
        if (a.length>=2)
        {
            Card[] left = Arrays.copyOfRange(a, 0, a.length/2);
            Card[] right = Arrays.copyOfRange(a, a.length/2, a.length);

            //recursively splits the main array into two parts
            mergeSort(left);
            mergeSort(right);

            //merges the two arrays together
            merge(a, left, right);
        }
    }

    //This is the part of the merge sort which merges the two halves of the code back together into one array
    private static void merge(Card[] result, Card[] left, Card[] right)
    {
        //index of left/right arrays
        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < result.length; i++)
        {
            if (i2 >= right.length || (i1 < left.length && (left[i1].compareTo(right[i2]) >= 0)))
            {
                result[i] = left[i1];
                i1++; //increments leftArray counter
            }
            else
            {
                result[i] = right[i2];
                i2++; //increments rightArray counter
            }
        }
        cardArray = result;
    }

    @Override
    public void shuffle()
    {
        Random myRandom = new Random();

        for (int i = 0; i < sizeCounter * 5; i++)
        {
            int lower = myRandom.nextInt(sizeCounter);
            int upper = myRandom.nextInt(sizeCounter);

            //swaps them
            Card tmp1 = cardArray[lower];
            Card tmp2 = cardArray[upper];
            cardArray[lower] = tmp2;
            cardArray[upper] = tmp1;
        }
    }

    //this method swaps two values in the ArrayList
    private void swap(int A, int B)
    {
        Card tmp1 = cardArray[A];
        Card tmp2 = cardArray[B];
        cardArray[A] = tmp2;
        cardArray[B] = tmp1;
    }
}