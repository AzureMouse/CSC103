// File: DoubleArraySeq.java 

// This is an assignment for students to complete after reading Chapter 3 of
// "Data Structures and Other Objects Using Java" by Michael Main.


/******************************************************************************
* This class is a homework assignment;
* A DoubleArraySeq is a collection of double numbers.
* The sequence can have a special "current element," which is specified and 
* accessed through four methods that are not available in the bag class 
* (start, getCurrent, advance and isCurrent).
*
* @note
*   (1) The capacity of one a sequence can change after it's created, but
*   the maximum capacity is limited by the amount of free memory on the 
*   machine. The constructor, addAfter, 
*   addBefore, clone, 
*   and concatenation will result in an
*   OutOfMemoryError when free memory is exhausted.
*   <p>
*   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
*   (Integer.MAX_VALUE). Any attempt to create a larger capacity
*   results in a failure due to an arithmetic overflow. 
*
* @note
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @see
*   <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java">
*   Java Source Code for this class
*   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
*   </A>
*
* @version
*   March 5, 2002
******************************************************************************/
public class DoubleArraySeq implements Cloneable
{
   // Invariant of the DoubleArraySeq class:
   //   1. The number of elements in the sequences is in the instance variable 
   //      manyItems.
   //   2. For an empty sequence (with no elements), we do not care what is 
   //      stored in any of data; for a non-empty sequence, the elements of the
   //      sequence are stored in data[0] through data[manyItems-1], and we
   //      don�t care what�s in the rest of data.
   //   3. If there is a current element, then it lies in data[currentIndex];
   //      if there is no current element, then currentIndex equals manyItems. 
   private double[ ] data;
   private int manyItems;
   private int currentIndex; 
   
   /**
   * Initialize an empty sequence with an initial capacity of 10.  Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * @param - none
   * @postcondition
   *   This sequence is empty and has an initial capacity of 10.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[10].
   **/   
   public DoubleArraySeq( )
   {
      // Implemented by student.
      data = new double[10];
   }
     

   /**
   * Initialize an empty sequence with a specified initial capacity. Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * @param initialCapacity
   *   the initial capacity of this sequence
   * @precondition
   *   initialCapacity is non-negative.
   * @postcondition
   *   This sequence is empty and has the given initial capacity.
   * @exception IllegalArgumentException
   *   Indicates that initialCapacity is negative.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[initialCapacity].
   **/   
   public DoubleArraySeq(int initialCapacity)
   {
      // Implemented by student.
      if(initialCapacity <= 0){
         throw new IllegalArgumentException("Negative value: " + initialCapacity);
      }

      data = new double[initialCapacity];
   }
        
 
   /**
   * Add a new element to this sequence, after the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed after the current
   *   element. If there was no current element, then the new element is placed
   *   at the end of the sequence. In all cases, the onew element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addAfter(double element)
   {
      // Implemented by student.

      // Verify that we have enough space to add a new element
      if(size() == getCapacity()){
         ensureCapacity(size() * 2 + 1); // -> Call ensureCapacity if the number of elements in the sequences matches our sequence capacity
      }

      if(!isCurrent()){
         // Sense we don't have a current element, we need to place a current element at the end of the sequence
         manyItems++;
         data[size() - 1] = element;
         currentIndex = size() - 1;

      } else{
         data[currentIndex + 1] = element; // -> Place the element after the current element
         currentIndex += 1; // -> The new element becomes the current element
         manyItems++; // -> Increment manyItems by one, this is because we added a new element
      }
   }


   /**
   * Add a new element to this sequence, before the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed before the current
   *   element. If there was no current element, then the new element is placed
   *   at the start of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addBefore(double element)
   {
      // Implemented by student.

      // Verify that we have enough space to add a new element
      if(size() == getCapacity()){
         ensureCapacity(size() * 2 + 1); // -> Call ensureCapacity if the number of elements in the sequences matches our sequence capacity
      }

      if(!isCurrent()){
         // Sense we don't have a current element, we need to place a current element at the start of the sequence
         data[0] = element;
         currentIndex = 0;
         manyItems++; 

      } else{
         for(int index = manyItems; index > 0; index-- ){
            data[index] = data[index - 1];
         }
         currentIndex = 0;
         data[currentIndex] = element;
         manyItems++;
      }
   }
   
   /**
   * Place the contents of another sequence at the end of this sequence.
   * @param addend
   *   a sequence whose contents will be placed at the end of this sequence
   * @precondition
   *   The parameter, addend, is not null. 
   * @postcondition
   *   The elements from addend have been placed at the end of 
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the addend is also unchanged.
   * @exception NullPointerException
   *   Indicates that addend is null. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this sequence.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/

   public void addAll(DoubleArraySeq addend)
   {
      // Implemented by student.

      // Verify that we have enough space to add the elements of the sequence
      if(this.size() + addend.size() > this.getCapacity()){
         ensureCapacity(size() * 2 + 1);
      }

      System.arraycopy(addend.data, 0, this.data, size(), addend.size());
   }   
   
   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true. 
   * @postcondition
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   advance may not be called.
   **/
   public void advance( )
   {
      // Implemented by student.

      // Verify that we have a current element, if not throw an IllegalArgumentException
      if(!isCurrent()){
         throw new IllegalArgumentException("Current element does not exist!");
      }

      currentIndex++;
   }
   
   /**
   * Generate a copy of this sequence.
   * @param - none
   * @return
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/
   public DoubleArraySeq clone( )
   {  // Clone a DoubleArraySeq object.
      DoubleArraySeq answer;
      
      try
      {
         answer = (DoubleArraySeq) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
      
      answer.data = data.clone( );
      
      return answer;
   }
   
   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * @param s1
   *   the first of two sequences
   * @param s2
   *   the second of two sequences
   * @precondition
   *   Neither s1 nor s2 is null.
   * @return
   *   a new sequence that has the elements of s1 followed by the
   *   elements of s2 (with no current element)
   * @exception NullPointerException.
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new sequence.
   * @note
   *   An attempt to create a sequence with a capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/  
   public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
   {
      // Implemented by student.
      if(s1 == null || s2 == null){
         throw new NullPointerException("Object is null!");
      }

      DoubleArraySeq newSeq = new DoubleArraySeq(s1.getCapacity() + s2.getCapacity()); // -> Create new sequence with the capacity of s1 + s2

      System.arraycopy(s1.data, 0, newSeq.data, 0, s1.size());
      System.arraycopy(s2.data, 0, newSeq.data, s1.size(), s2.size());

      return newSeq;

   }

   /**
   * Change the current capacity of this sequence.
   * @param minimumCapacity
   *   the new capacity for this sequence
   * @postcondition
   *   This sequence's capacity has been changed to at least minimumCapacity.
   *   If the capacity was already at or greater than minimumCapacity,
   *   then the capacity is left unchanged.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: new int[minimumCapacity].
   **/
   public void ensureCapacity(int minimumCapacity)
   {
      // Implemented by student.
      if(getCapacity() < minimumCapacity){
         double[] newArr = new double[minimumCapacity];
         System.arraycopy(data, 0, newArr, 0, size());
         data = newArr;
      }
   }

   /**
   * Accessor method to get the current capacity of this sequence. 
   * The add method works efficiently (without needing
   * more memory) until this capacity is reached.
   * @param - none
   * @return
   *   the current capacity of this sequence
   **/
   public int getCapacity( )
   {
      // Implemented by student.
      return data.length;
   }

   /**
   * Accessor method to get the current element of this sequence. 
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @return
   *   the current element of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   getCurrent may not be called.
   **/
   public double getCurrent( )
   {
      // Implemented by student.

      if(!isCurrent()){
         throw new IllegalStateException("No current element!");

      }

      return data[currentIndex];
   }

   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * getCurrent method. 
   * @param - none
   * @return
   *   true (there is a current element) or false (there is no current element at the moment)
   **/
   public boolean isCurrent( )
   {
      // Implemented by student.

      // If this boolean expression is true, we have a current element, if not we don't
      return (currentIndex < manyItems);
   }
              
   /**
   * Remove the current element from this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @postcondition
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   removeCurrent may not be called. 
   **/
   public void removeCurrent( )
   {
      // Implemented by student.

      // Verify that we have a current element
      if(!isCurrent()){
         throw new IllegalStateException("No current element!");
      }

      double[] newArr = new double[data.length - 1];
      if(currentIndex != manyItems){
         // Shift elements down
         for(int index = currentIndex; index < manyItems - 1; index++){
            data[index] = data[index + 1];
         }
   
         manyItems--;
      }
   }
                 
   /**
   * Determine the number of elements in this sequence.
   * @param - none
   * @return
   *   the number of elements in this sequence
   **/

   public int size( )
   {
      // Implemented by student.
      return manyItems;
   }
   
   /**
   * Set the current element at the front of this sequence.
   * @param - none
   * @postcondition
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/

   public void start( )
   {
      // Implemented by student.

      // Verify that the sequence is not empty
      if(manyItems == 0){
         // Do nothing

      } else{
         currentIndex = 0;
      }
   }
   
   /**
   * Reduce the current capacity of this sequence to its actual size (i.e., the
   * number of elements it contains).
   * @param - none
   * @postcondition
   *   This sequence's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity. 
   **/

   public void trimToSize( )
   {
      double[ ] trimmedArray;
      
      if (data.length != manyItems)
      {
         trimmedArray = new double[manyItems];
         System.arraycopy(data, 0, trimmedArray, 0, manyItems);
         data = trimmedArray;
      }
   }

   /**
   * public void addFront(double element)
   * Adds a new element at the front of the sequence and makes it the new current
   *
   * @param - double element
   * @postcondition
   * 	The new element has been placed at the front of the sequence and is now the current.
   **/

  public void addFront(double element){
      if(manyItems == data.length){
         ensureCapacity(manyItems * 2 + 1);
      }

      // Shift elements up
      for(int index = manyItems; index > 0; index--){
         data[index] = data[index - 1];
      }

      currentIndex  = 0;
      data[currentIndex] = element;
      manyItems++;
  }

  /**
   * public void removeFront()
   * Removes element at the front of the sequence
   * @param - none
   * @precondition
   * 	The sequence is not empty
   * @postcondition
   * 	The element at the front of the sequence has been removed
   * @exception IllegalStateException
   * 	Indicates that the sequence is empty
   **/

  public void removeFront(){
      if(size() == 0){
         throw new IllegalStateException();
      }

     // Set current to be zero and remove the new current
     currentIndex = 0;
     removeCurrent();

  }

  /**
   * public void setCurrent(int n)
   * Makes the Nth elemnt become the current element
   * @param - int n
   * @precondition
   *	The sequence is not empty
   * @postcondition
   *	Sets the Nth element to be the new current element
   * @exception IllegalStateException
   *	Indicates that the sequence is empty
   * @exception IllegalArgumentException
   *	Indicates that n does not represent a valid array location
   **/
  public void setCurrent(int n){
   if(size() == 0){
      throw new IllegalStateException("Sequence is empty");
   }

   if(n > size()){
      throw new IllegalArgumentException("N is bigger than sequence!");
   }

   currentIndex = n - 1;
  }

  /**
   * public double getElement(int n)
   * Return the Nth element of the sequence
   * @param - integer n
   * @precondition
   *	Sequence is not empty and n is greater than the sequence size as well as being greater than 0
   * @postcondition
   *	Return the Nth element of the sequence
   * @exception IllegalStateException
   * 	Indicates that the sequence is empty
   * @exception IllegalArgumentException
   *	Indicates that n is greater than the sequence size and that n is not greater than zero
   **/
  public double getElement(int n){
      if(size() == 0){
         throw new IllegalStateException();
      }

      if(n > (size() - 1) || n <= 0){
         throw new IllegalArgumentException();
      }

      return data[n-1];
  }

  /**
   * public boolean equals(Object)
   * Returns true if sequence length, data and order are the same
   * @param - Object
   * @returns
   *	return true or false statement
   **/
  public boolean equals(Object obj){
      boolean isEqual = false;
      
      if(obj instanceof DoubleArraySeq){
         DoubleArraySeq newObj = (DoubleArraySeq) obj;
         if(this.size() == newObj.size()){
            // Loop thorugh sequence and verify that they contain the same order and data set
            for(int index = 0; index < size(); index++){
               if(this.data[index] == newObj.data[index]){
                  isEqual = true;
               }
            }
         }
      }

      return isEqual;
  }

  /**
   * public String toString()
   * Output string of all elements of the sequence seperated by a space
   * @param - none
   * @precondition
   *  Sequence is not empty
   * @postcondition
   *  Output string of all elements of the sequence seperated by a space
   *@exception IllegalStateException
   *  Indicates that the sequence is empty
  **/
  @Override
  public String toString(){
   String sequence = "";
   
   // Go thorugh the sequence and return the sequence in order
   for(this.start(); this.isCurrent(); this.advance()){
      sequence += getCurrent() + " ";
   }
   
   return sequence;
  }
}