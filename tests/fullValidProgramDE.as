class testValid{
	\\------------------------------------
	\\------Working Program Example-------
	\\------------------------------------
	\*
	 *this function uses Newton's method to estimate a square root value in
	 *the same method that the babylonians used. nx+1 = 0.5(nx + num/nx)
	 *@param square -the value we are trying to find the square root of
	 *@param guess -current guess as to what the square root is
	 *@return -the square root within 10^-5 accuracy
	 *\
	float sqrt(float square, float guess)
	{
        float nextGuess;
        nextGuess = 0.5 * (guess + square/guess);
        if (nextGuess - guess > -0.00001 && nextGuess - guess < 0.00001)
            return nextGuess;
        return sqrt(square, nextGuess);
	}

    \*
     *this function gets the distance between two vectors of a given size by
     *finding the square root of the sum of the square of the difference between
     *corresponding elements between vector A and B.
     *@param vecA -the first vector being used
     *@param vecB -the second vector being used
     *@param size -the number of elements in vector A and B
     *@return -the distance between A and B
     *\
	float getDistance(float vecA[], float vecB[], int size)
	{
	    int i = 0;
	    float sum = 0;
	    while (i < size)
	    {
	        sum = sum + (vecA[i] - vecB[i]) * (vecA[i] - vecB[i]);
	        i++;
	    }
	    return sqrt(sum, 2);
	}

	void main()
	{
	    float a[10];
	    float b[10];
	    final int maxSize = 10;
	    int thisSize;
	    bool validInput;
	    int i;
	    print("Input the size of your vectors: (max:" + maxSize + ")");
	    printline();
	    validInput = false;
	    while(~validInput)
	    {
	        read(thisSize);
	        if (thisSize > 0 && thisSize <= maxSize)
	            validInput = true;
	        else
	        {
	            print("Invalid size!\nInput the size of your vectors: (max:" + maxSize + ")");
                printline();
	        }

	    }
	    i = 0;
	    while (i < thisSize)
	    {
	        read(a[i], b[i]);
	        i++;
	    }
	    print("Distance between vectors is: ", getDistance(a,b,thisSize));

	}
}