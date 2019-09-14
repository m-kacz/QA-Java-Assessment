package com.qa.javaAssessment;

public class Assessment {

	// Given a string, return a string where
	// for every char in the original string,
	// there are three chars.

	// multChar("The") ==> "TTThhheee"
	// multChar("AAbb") ==> "AAAAAAbbbbbb"
	// multChar("Hi-There") ==> "HHHiii---TTThhheeerrreee"

	public String multChar(String input) {
		String word = "";
		for (int i = 0; i< input.length(); i++) {
			word += input.substring(i, i+1);
			word += input.substring(i, i+1);
			word += input.substring(i, i+1);
		}
		return word;
	}
	
	// Return the string (backwards) that is between the first and last appearance
	// of "bert"
	// in the given string, or return the empty string "" if there is not 2
	// occurances of "bert" - Ignore Case

	// getBert("bertclivebert") ==> "evilc"
	// getBert("xxbertfridgebertyy") ==> "egdirf"
	// getBert("xxBertfridgebERtyy") ==> "egdirf"
	// getBert("xxbertyy") ==> ""
	// getBert("xxbeRTyy") ==> ""

	public String getBert(String input) {
		char[] inputTest = input.toCharArray(); //convert the input string into a char array
		int bertCount = 0; //initialise a variable to store a "bert" count
		boolean bTest = false; //initialise a boolean variable for "b" characters
		boolean eTest = false; //initialise a boolean variable for "e" characters
		boolean rTest = false; //initialise a boolean variable for "r" characters
		int initialVal = 0; //initialise a variable to store the start location for processed string
		int finalVal = 0; //initialise a variable to store the end location for processed string
		int count = 0; ////initialise a loop counter
		for(char currentVal:inputTest) { //advance through the array
			switch(currentVal) {
			case 'b': //if "b" detected
				bTest = true; //set b flag
				break;
			case 'B': //same as above for capital "B"
				bTest = true;
				break;
			case 'e': //if "e" detected
				if(bTest == true) { //if b flag set
					eTest = true; //set e flag
				}
				break;
			case 'E': //same as above for capital "E"
				if(bTest == true) {
					eTest = true;
				}
				break;
			case 'r': //if "r" detected
				if(bTest == true && eTest == true) { //if b and e flag set
					rTest = true; //set r flag
				}
				break;
			case 'R': //same as above for capital "R"
				if(bTest == true && eTest == true) {
					rTest = true;
				}
				break;
			case 't': //if "t" detected
				if(bTest == true && eTest == true && rTest == true) { //if b, e and r flag set
					//this ensures the previous characters spell "bert"
					if(bertCount == 0) { 
						//if the bert count is zero, this is the first bert and the start point
						//should be set for the next character in the array
						initialVal = (count); //set the start point
					}
					if(bertCount > 0) { 
						//if the bert count is not zero then this is not the first bert
						//set the final value to before this bert. If a previous bert has set the end point
						//then it will be overwritten
						finalVal = (count-4);
					}
					bTest = false; //resets the b flag
					eTest = false; //resets the e flag
					rTest = false; //resets the r flag
					bertCount++; 
				}
				break;
			case 'T': //same as above for capital "T"
				if(bTest == true && eTest == true && rTest == true) {
					if(bertCount == 0) {
						initialVal = (count);
					}
					if(bertCount > 0) {
						finalVal = (count-4);
					}
					bTest = false;
					eTest = false;
					rTest = false;
					bertCount++;
				}
				break;
			default: //ignore if any other character
				bTest = false; //reset to prevent spread out berts
				eTest = false; //reset to prevent spread out berts
				rTest = false; //reset to prevent spread out berts
				break;
			}
			count++;
		}
		if(bertCount < 2) {//if there was less than 2 berts we cannot proceed
			return "";
		}
		int length = finalVal-initialVal; //sets a new variable to the length of characters between the first and last bert
		char[] outputVals = new char[length]; //creates a new array to store the final output
		int i = 0; //initialises a new variable to increment through output array
		int start = finalVal; //initialises a new variable to decrement through input array
		for(int iVal = initialVal; iVal <= finalVal-1; iVal++) { //loop through selected array segment
			//this loop will reverse the string
			outputVals[i] = inputTest[start]; //set output array value to input array value
			i++; //increment output array position
			start--; //decrement input array position
			
		}
		String output = new String(outputVals); //convert output array to a new string
		return output; //return the processed string
	}
		
	// Given three ints, a b c, one of them is small, one is medium and one is
	// large. Return true if the three values are evenly spaced, so the
	// difference between small and medium is the same as the difference between
	// medium and large. Do not assume the ints will come to you in a reasonable
	// order.

	// evenlySpaced(2, 4, 6) ==> true
	// evenlySpaced(4, 6, 2) ==> true
	// evenlySpaced(4, 6, 3) ==> false
	// evenlySpaced(4, 60, 9) ==> false

	public boolean evenlySpaced(int a, int b, int c) {
		int smallVal = 0; //initialises a variable to store the smallest value
		int medVal = 0; //initialises a variable to store the medium value
		int largeVal = 0; //initialises a variable to store the largest value
		if((a < b) && (a < c)) { //if a is smaller than b and c then it is the smallest value
			smallVal = a; //set smallest variable to a
			if(b < c) { //if a is smallest and b is smaller than c then b is medium and c is largest
				medVal = b; //set medium variable to b
				largeVal = c; //set largest variable to c
			}
			else { //if a is smallest and c is smaller than b then c is medium and b is largest
				medVal = c; //set medium variable to c
				largeVal = b; //set largest variable to b
			}
		}
		else if((b < a) && (b < c)) { //if b is smaller than a and c then it is the smallest value
			smallVal = b; //set smallest variable to b
			if(a < c) { //if b is smallest and a is smaller than c then a is medium and c is largest
				medVal = a; //set medium variable to a
				largeVal = c; //set largest variable to c
			}
			else { //if b is smallest and c is smaller than a then c is medium and a is largest
				medVal = c; //set medium variable to c
				largeVal = a; //set largest variable to a
			}
		}
		else {
			smallVal = c; //if c is smaller than a and b then it is the smallest value
			if(a < b) { //if c is smallest and a is smaller than b then a is medium and b is largest
				medVal = a; //set medium variable to a
				largeVal = b; //set largest variable to b
			}
			else { //if c is smallest and b is smaller than a then c is medium and a is largest
				medVal = b; //set medium variable to b
				largeVal = a; //set largest variable to a
			}
		}
		if((medVal-smallVal) == (largeVal-medVal)) {
			//if the difference between medium and small and large and medium is the same then return true
			return true;
		}
	return false; //if the differences are not the same then return false
	}

	// Given a string and an int n, return a string that removes n letters from the 'middle' of the string.
	// The string length will be at least n, and be odd when the length of the input is odd.

	// nMid("Hello", 3) ==> "Ho"
	// nMid("Chocolate", 3) ==> "Choate"
	// nMid("Chocolate", 1) ==> "Choclate"

	public String nMid(String input, int a) {
		char[] inputVals = input.toCharArray(); //convert the input string into a char array
		int stringLength = input.length(); //sets stringlength to length of input string
		int length = stringLength-a; //calculates length of string after characters have been removed
		char[] outputVals = new char[length]; //initialises a new array to store the new shorter string
		if(stringLength % 2 == 0) { //checks if even number of characters
			int remove = (a/2); //initialises a new variable that is half of the required removal amount
			int midPoint = (stringLength/2); //initialises a new variable that is half the initial string length
			int beforeCutoff = midPoint - remove; //initialises a new variable that is the location of the end of the first section
			int afterCutoff = midPoint + remove; //initialises a new variable that is the location of the start of the last section
			for(int i = 0; i < beforeCutoff; i++) { //loops through input string array until end of first section
				outputVals[i] = inputVals[i]; //sets output array values to input array values
			}
			for(int i = afterCutoff; i < stringLength; i++) { 
				//loops through input string array from start of last section until end of input string
				outputVals[i-a] = inputVals[i]; //sets output array values to input array values
			}			
		}
		else { //for odd number of characters
			int remove = ((a-1)/2); //initialises a new variable that is half of the required removal amount (-1 due to odd length)
			int midPoint = (((stringLength-1)/2)+1); //initialises a new variable that is half the initial string length (-1 due to odd length)
			int beforeCutoff = midPoint - remove; //initialises a new variable that is the location of the end of the first section
			int afterCutoff = midPoint + remove; //initialises a new variable that is the location of the start of the last section
			for(int i = 0; i < beforeCutoff; i++) { //loops through input string array until end of first section
				outputVals[i] = inputVals[i]; //sets output array values to input array values
			}
			for(int i = afterCutoff; i < stringLength; i++) {
				//loops through input string array from start of last section until end of input string
				outputVals[i-a] = inputVals[i]; //sets output array values to input array values
			}
		}
		String output = new String(outputVals); //converts final array result to a new string
		return output; //returns the final string
	}


	// Given a string, return the length of the largest "block" in the string.
	// A block is a run of adjacent chars that are the same.
	//
	// superBlock("hoopplla") ==> 2
	// superBlock("abbCCCddDDDeeEEE") ==> 3
	// superBlock("") ==> 0

	public int superBlock(String input) {
		char[] inputTest = input.toCharArray(); //convert the input string into a char array
		char previousVal = '\u0000'; //initialise a variable to store the previous character
		int testCount = 1; //initialise a variable to store the current counter
		int finalCount = 0; //initialise a variable to store the maximum count
		for(char currentVal:inputTest) { //advance through the array
			if(currentVal == previousVal) { //if the current character is the same as the previous character
				testCount++; //increment the current counter
			}
			else {
				if(testCount > finalCount) { //if the current character is not the same as the previous character
					//if it's larger than the current maximum then set the maximum count to the current count
					finalCount = testCount; 
				}
				testCount = 1; //reset the current counter for the next loop
			}
			previousVal = currentVal; //sets the previous character variable to the current array value for next loop
		}
		return finalCount; //return the maximum count	

	}
	
	//given a string - return the number of times "am" appears in the String ignoring case -
	// BUT ONLY WHEN the word "am" appears without being followed or proceeded by other letters
	//
	//amISearch("Am I in Amsterdam") ==> 1
	//amISearch("I am in Amsterdam am I?") ==> 2
	//amISearch("I have been in Amsterdam") ==> 0

	public int amISearch(String arg1) {
		char[] inputTest = arg1.toCharArray(); //convert the input string into a char array
		int amCount = 0; //initialise variable to store the "am" count
		boolean aTest = false; //initialise a boolean variable for location "a"s
		int count = 0; //initialise a loop counter
		char test = ' ';
		for(char currentVal:inputTest) {
			switch(currentVal) {
			case 'a': //sets "a" variable then breaks for the next character
				aTest = true;
				break;
			case 'A': //repeat above for capital "A"
				aTest = true;
				break;
			case 'm': //when "m" is detected
				if(aTest == true) { //checks that "a" variable is true
					if(count > 1) { //checks if current array position is more than the second position
						if(inputTest[count-2] == test  && inputTest[count+1] == test) { //checks no letters before or after "am"
							amCount++; //increments the "am" counter
							aTest = false; //resets the "a" variable
						}
						else { //if there are letters either side of the "am" then ignore
							aTest = false; //resets the "a" variable
						}
					}
					else if(inputTest[count+1] == test) { //special case for if the "am" appears at the start of the array
						amCount++; //if for no letters after the "am" then increments the "am" counter 
						aTest = false; //resets the "a" variable
					}
				}
				break;
			case 'M': //repeat above for capital "M"
				if(aTest == true) {
					if(count > 1) {
						if(inputTest[count-2] == test  && inputTest[count+1] == test) {
							amCount++;
							aTest = false;
						}
						else {
							aTest = false;
						}
					}
					else if(inputTest[count+1] == test) {
						amCount++;
						aTest = false;
					}
				}
				break;
			default:
				break;
			
			}
			count++; //increment the counter each loop so that current array position is known	
		}
		return amCount; //return the "am" count
	}
	
	//given a number
	//if this number is divisible by 3 return "fizz"
	//if this number is divisible by 5 return "buzz"
	//if this number is divisible by both 3  and 5return "fizzbuzz"
	//
	//fizzBuzz(3) ==> "fizz"
	//fizzBuzz(10) ==> "buzz"
	//fizzBuzz(15) ==> "fizzbuzz"
	
	public String fizzBuzz(int arg1) {
		if((arg1 % 3 == 0) && (arg1 % 5 != 0)) {
			return "fizz";
		}
		else if((arg1 % 5 == 0) && (arg1 % 3 != 0)) {
			return "buzz";
		}
		else if((arg1 % 3 == 0) && (arg1 % 5 == 0)) {
			return "fizzbuzz";
		}
		return null;
		
	}
	
	//Given a string split the string into the individual numbers present
	//then add each digit of each number to get a final value for each number
	// String example = "55 72 86"
	//
	// "55" will = the integer 10
	// "72" will = the integer 9
	// "86" will = the integer 14
	//
	// You then need to return the highest vale
	//
	//largest("55 72 86") ==> 14
	//largest("15 72 80 164") ==> 11
	//largest("555 72 86 45 10") ==> 15
	
	public int largest(String arg1) {
		char[] inputTest = arg1.toCharArray();	//convert the input string into a char array
		//this allows easy manipulation of the data 
		int testValue = 0; //initialise the variable to store addition results
		int maxValue = 0; //initialise the variable to store the maximum addition result
		int i = 0; //initialise a loop counter
		int stringLength = arg1.length(); // set the string length variable to the length of the input string
		for(i = 0; i < stringLength; i++) { //advance through the input characters until the end is reached
			if(inputTest[i] != ' ') { //if the character is not a space it will be a number
				int num = Character.getNumericValue(inputTest[i]); //sets a num variable to current array value
				testValue += num; //adds the current array variable to the current addition result
			}
			else if(inputTest[i] == ' ') { //if the character is a space then this is the end of the addition calculation
				if(testValue > maxValue) { //check the current addition result is larger than the current maximum
					maxValue = testValue; //sets the maximum to the current addition result
					testValue = 0; // resets the addition result test variable for the next numbers
				}
				else if(testValue <= maxValue) { //if the addition result is smaller than the current maximum
					testValue = 0; //reset the addition result test variable and discard the result
				}
			}
		}
		if(testValue > maxValue) { //perform addition result check for final number section of string
			maxValue = testValue; //set the result if larger
			}
	return maxValue; //return the maximum value	
	}
}
