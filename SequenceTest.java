/*
*
*
*
*
*
*
*/
import java.util.Scanner;
public class SequenceTest{
	public static int inputValidation(Scanner obj){
		boolean isRunning = true;
		int stdin = 0;

		while(isRunning){
			try{
				stdin = obj.nextInt();
				isRunning = false;
			} catch(Exception e){
				stdin = obj.nextInt();
			}
		}

		return stdin;
	}
	public static void inputHandler(DoubleArraySeq seq){
		boolean isRunning = true;
		while(isRunning){
			System.out.println("\n" + "Please select a valid operation");
			int fetchData;

			try{
				Scanner menuChoice = new Scanner(System.in);
				Scanner input = new Scanner(System.in);

				switch(inputValidation(menuChoice)){
					case 1:
						System.out.println("Outputting sequence...");
						System.out.println(seq.toString());
						break;
					case 2:
						System.out.println("Fetching capacity");
						System.out.println(seq.getCapacity());
						break;
					case 3:
						System.out.println("Enter the value you wish the current to be");
						fetchData = inputValidation(input);
						seq.setCurrent(fetchData);
						break;
						
					case 4:
						System.out.println("Please enter the number you wish to be added to the front");
						fetchData = inputValidation(input);
						seq.addFront(fetchData);
						System.out.println("Number has been added");
						break;
					case 5:
						seq.setCurrent(seq.size());
						System.out.println("Please enter a number to add at the end of the sequence");
						fetchData = inputValidation(input);
						seq.addAfter(fetchData);

						System.out.println("Added!");
						break;
					case 6:
						System.out.println("Please enter a number to add before the current");
						fetchData = inputValidation(input);
						seq.addBefore(fetchData);
						System.out.println("Added!");
						break;
					case 7:
						//seq.setCurrent(seq.size());
						System.out.println("Please enter a number to add after the current");
						fetchData = inputValidation(input);
						seq.addAfter(fetchData);
						break;
					case 8:
						System.out.println("Removing element at front...");
						seq.removeFront();
						break;
					case 9:
						System.out.println("Please enter a location that an element resides at to remove");
						fetchData = inputValidation(input);
						seq.setCurrent(fetchData);
						seq.removeCurrent();
						break;
					case 10:
						System.out.println("Please enter a valid loation to fetch");
						fetchData = inputValidation(input);
						System.out.println(seq.getElement(fetchData));
						break;
					case 11:
						// Display last element in sequence
						seq.setCurrent(seq.size());
						System.out.println("Getting the last element...");
						System.out.println(seq.getCurrent());
						break;
					case 12:
						// exit
						isRunning = false;
						System.out.println("Exiting!");
						break;
				}
			} catch(Exception e){
				
			}
		}
	}

	public static void printMenu(){
		System.out.println("Welcome to sequencer");
		System.out.println("1. Print the sequence");
		System.out.println("2. Print the capacity of the sequence");
		System.out.println("3. Set current element");
		System.out.println("4. Add number to the front of the sequence");
		System.out.println("5. Add a number to the end of the sequence");
		System.out.println("6. Add a number before the current element");
		System.out.println("7. Add a number after the current element");
		System.out.println("8. Delete the first number from the sequence");
		System.out.println("9. Delete a number at a location");
		System.out.println("10. Display the value at a certain location");
		System.out.println("11. Display the last element in the sequence");
		System.out.println("12. Quit");
	}
	public static void main(String[] args){
		Scanner stdin = new Scanner(System.in);
		DoubleArraySeq d = new DoubleArraySeq();
		printMenu();
		inputHandler(d);
	}
}