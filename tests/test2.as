class test2MyBoi {
int x[7];
float pythag(float vals[])
{
	float a = vals[0];
	float b = vals[1];
	float c;

	a = a * a;
	b = b * b;
	c = sqrt(a + b);
	return c + 0.0;
}

void main()
{
	float height;
	float length;
	float triVals[3];
	read(height, length);
	triVals[0] = height;
	triVals[1] = length;
	{
		int emu43[43];
		triVals[2] = pythag(triVals);
	};
	print(triVals[2]);
	
	\\various tests
	pythag();
	pythag(x);
	pythag(x, y);
	x = pythag();
	x = pythag(x, y);
	
}

float testUnmatchedIfInAClosedMatchedIf()
{
	{
		if (true)
		{
			if (thisShouldntHaveAnElse)
				read(betterNot);
		}
		else
			justAStatement = "ye";
	};
}

int someAdditionalRandomTests()
{
	printline(   );
	read(x, y);
	read(x);
	print(x);
	print(x, y);
	x++;
	x--;
	x[0 + 8 || 7]++;
	y[8 * ~7 - tensai() + tensai(16 - 32, 4) - tensai(x[6])]--;
	\\but is comma the lowest precedence??????
	testFunc( 6 + 7, +4 - 2);

}

}