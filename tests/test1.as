class testClass 
{   
	int testInt;
	bool testBool;
	char testChar;
	float testFloat;

	void testNestedIfs()
	{
		if (true)
			if (~true)
				if (false)
					read(x);
				else
					if (yes == false)
						print(2.0);
					else
						x = 23;
			else 
				y = 37;

		return ;

	}

	void testNestedIfsInWhiles (int x[], int y)
	{
		int newTestVal;
		bool thisWorks = true;
		final char theMan = '\'';
		float testDec = 3.14;

		newTestVal = 4;
		testInt = 5;
		if (thisWorks)
			while(true)
				while(false)
					if(newTestVal > 3)
						if (y > 3)
							if (x < 2)
								read(x);
							else
								read(thisName);
						else
							if (34 == 2)
								read(x2);
							else
								read(x3);
					else
						print(x);
		print(theMan);
		{

		};
		return ;
	}
	\\optionalSemi
	;

	float testWhileInNestedElse( char salmon)
	{
		if (true)
			while (theMan)
				if (superSalmon == upstream)
					read(x);
				else
					while (theWoman)
						if (streetFighter2 == good)
							virtuaFighter = bad;
						else 
							read(salmon);
		return 3.14159;		
	}

	char testMatchedIfInUnmatchedIfWhile(bool confusingName)
	{
		\\but it makes sense in all honesty
		if (true)
		while (false)
		if (superMan)
		while (batMan)
		if (spiderMan + 3)
		read(x);
		else
		print(thisShouldBeTotallyNested);
		\*
		block comment party
		*\
		if (true)
			if (false)
				while (theman)
					if (theman2)
						read(x);
			else
				print(x);
		if (       true)
			while ( themanPart2)
				if (bacon)
					print(enjoyTheSmell);
				else 
					print(KevinBacon);
		else 
			wellhowabout = that;
	}

	void letsTestPrecedences()
	{
		\\is assignment less than ternary?
		a = ( a + 5 < 6 ? a + 2 : a - 2 );
		\\is ternary less than or?
		a = ( a || b ? c || d : a || b);
		\\is or less than and?
		a = b || c && d || e;
		\\is and less than equality?
		a = b && c == d && e;
		\\is and less than not equals?
		a = b && c <> d && e;
		\\is equality and not equals the same precedence?
		a  = b <> c == d <> e == f;
		\\is equality less than relational?
		a = b < c == d > e == f <= g == h >= i && b < c <> d > e <> f <= g <> h >= i;
		\\are all relational operators the same precedence?
		a = b > c < d >= e <= f < g <= h > i >= j;
		\\is addative actions greater than relational operators?
		a = b + c > d + e < f + g >= h + i <= j + k == b - c > d - e < f - g >= h - i <= j - k;
		\\are + and - on the same precedence level?
		a = a + b - c + d - e;
		\\is + and - below * and /?
		a = b + c * d + e / f + g >= b - c * d - e / f - g;
		\\is / and * on the same level?
		a = b * c / d * e / f;
		\\are unary operators a higher precedence than * and / ?
		a = +b * +c / +d + -b * -c / -d + ~b * ~c / ~d;
		\\are unary operators on the same precedence level???!?!?!?
		a = + - ~ - + ~ - - + ~ c; \\note, these need to be spaced out to avoid finding a ++ or -- token
		\\last but not least, is type casting the lowest precedence?
		a = (float)b || (char)c;
		\\test parens
		a = 5 * (6 + 2) / 7;

		\*check postfix statements
		note, postfix statements do not have semicolons after them... 
		which is strange
		*\
		a++;
		a--;

		a = a[~b * 6] || true && false; 

		a = "testing\"thisString" + '\'' + '\n' + "and these chars \n";

	}
}