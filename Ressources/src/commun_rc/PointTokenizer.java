package commun_rc;

/**
 * <p>
 * Titre : Network Game
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Soci�t� : drmdh
 * </p>
 * 
 * @author Hamid Madani
 * @version 1.0
 */

/*
 * A class used to tokenize a string. Tokens recognized are: , a comma character
 * ( a open parenthesis character ) a close parenthesis character identifier a
 * letter followed by a sequence of letters or digits. string a double quoted
 * string (eg, "hi there") number a real number (in either decimal or scientific
 * notation) end-of-input the end of the string being tokenized white space
 * characters (space, tab and newline), aside from delimiting a token, are
 * ignored.
 */
public class PointTokenizer {
	/* The set of token types that are recognized by the tokenizer */
	public final static int T_END = 0;
	public final static int T_UNRECOG = 1;
	public final static int T_IDENT = 2;
	public final static int T_NUMBER = 3;
	public final static int T_COMMA = 4;
	public final static int T_OPAREN = 5;
	public final static int T_CPAREN = 6;
	public final static int T_STRING = 7;

	/* Create a tokenizer - buf is the string that is to be tokenized */
	public PointTokenizer(String buf) {
		/* Note the string we are tokenizing */
		input = buf;
		identVal = null;

		/* start at the start of the input */
		pos = 0;
		inputLen = input.length();

		/* Figure out what the next token is - let getNext() do the work */
		getNext();
	}

	/* Return the type of the current token */
	public int getCurrent() {
		return type;
	}

	/* Tell the tokenizer to go on to the next token */
	public void getNext() {
		/* Skip any leading spaces, tabs and newlines ... */
		while (pos < inputLen && Character.isSpaceChar(input.charAt(pos)))
			pos++;

		/* Note where this token started */
		startIndex = pos;

		/*
		 * Look at the next character to determine what the next token is
		 */

		/* End of the string? */
		if (pos == inputLen) {
			type = T_END;
			return;
		}

		/* An identifier? */
		if (Character.isUpperCase(input.charAt(pos)) || Character.isLowerCase(input.charAt(pos))) {
			while (pos < inputLen && (Character.isUpperCase(input.charAt(pos))
					|| Character.isLowerCase(input.charAt(pos)) || Character.isDigit(input.charAt(pos))))
				pos++;

			identVal = input.substring(startIndex, pos);
			type = T_IDENT;
			return;
		}

		/*
		 * A real number? (this is really ugly: need to figure out extent of the number,
		 * then convert it to a double)
		 */
		if (input.charAt(pos) == '-' || input.charAt(pos) == '+' || input.charAt(pos) == '.'
				|| Character.isDigit(input.charAt(pos))) {
			boolean haveDigit = false;

			/* Skip the sign part */
			if (input.charAt(pos) == '-' || input.charAt(pos) == '+')
				pos++;
			/* Skip any digits */
			if (pos < inputLen && Character.isDigit(input.charAt(pos))) {
				haveDigit = true;
				while (pos < inputLen && Character.isDigit(input.charAt(pos)))
					pos++;
			}
			/* Skip .digits */
			if (pos < inputLen && input.charAt(pos) == '.') {
				pos++;
				if (pos < inputLen && Character.isDigit(input.charAt(pos))) {
					haveDigit = true;
					while (pos < inputLen && Character.isDigit(input.charAt(pos)))
						pos++;
				}
			}
			/* If we have digits at this point, we have a number */
			if (haveDigit) {
				/* Skip an e or E */
				if (pos < inputLen && (input.charAt(pos) == 'e' || input.charAt(pos) == 'E')) {
					int startExp = pos;

					pos++;
					if (pos < inputLen && (input.charAt(pos) == '-' || input.charAt(pos) == '+'))
						pos++;
					/* A real exponent or a false lead? */
					if (pos < inputLen && Character.isDigit(input.charAt(pos))) {
						while (pos < inputLen && Character.isDigit(input.charAt(pos)))
							pos++;
					} else
						pos = startExp;
				}
				/*
				 * Now we know the full extent of the number - just need to convert it...
				 */
				try {
					numberVal = Double.valueOf(input.substring(startIndex, pos)).intValue();
					type = T_NUMBER;
					return;
				} catch (NumberFormatException e) {
					/* do nothing... */;
				}
			}
			pos = startIndex; /* undo... */
		}

		if (input.charAt(pos) == toc2) { /* A comma? */
			pos++;
			type = T_COMMA;
			return;
		}

		if (input.charAt(pos) == toc1) { /* An open parenthesis? */
			pos++;
			type = T_OPAREN;
			return;
		}

		if (input.charAt(pos) == toc3) { /* A close parenthesis? */
			pos++;
			type = T_CPAREN;
			return;
		}

		if (input.charAt(pos) == '"') { /* A quoted string? */
			do
				pos++;
			while (pos < inputLen && input.charAt(pos) != '"');
			if (input.charAt(pos) == '"') {
				stringVal = input.substring(startIndex + 1, pos);
				pos++;
				type = T_STRING;
				return;
			}
			pos = startIndex; /* undo what was done... */
		}

		/*
		 * Don't know what the input is. The current position isn't advanced - in other
		 * words, the bad input can't be skipped (the assignment didn't specify this,
		 * but it is a good thing to do, as it means bad input can't be ignored).
		 */
		type = T_UNRECOG;

		return;
	}

	/*
	 * Get the name of an identifier token - should only be called if getCurrent()
	 * reports a T_IDENT
	 */
	public String getIdent() {
		return type == T_IDENT ? identVal : null;
	}

	/*
	 * Get the content of a quoted string token - should only be called if
	 * getCurrent() reports a T_STRING
	 */
	public String getString() {
		return type == T_STRING ? stringVal : null;
	}

	/*
	 * Get the value of a number token - should only be called if getCurrent()
	 * reports a T_NUMBER
	 */
	public double getNumber() {
		return type == T_NUMBER ? numberVal : 0;
	}

	/*
	 * Return the index of the first character of the current token, with respect to
	 * the original string that is being tokenized
	 */
	public int getIndex() {
		return startIndex;
	}

	/*
	 * Check if the current token is a tt - if so, the token is skipped and true is
	 * returned; if not false is returned (and nothing is skipped).
	 */
	public boolean currentMatches(int tt) {
		if (type != tt)
			return false;
		getNext();
		return true;
	}

	/*
	 * Convert the current token to a string
	 */
	public String toString() {
		if (type == T_END)
			return "end-of-token-stream";
		if (type == T_UNRECOG)
			return "unrecognized token: " + input.charAt(pos);
		if (type == T_IDENT)
			return "identifier: " + identVal;
		if (type == T_NUMBER)
			return "number: " + numberVal;
		if (type == T_STRING)
			return "quoted-string: " + stringVal;
		if (type == T_COMMA)
			return "comma";
		if (type == T_OPAREN)
			return "open-parenthesis";
		if (type == T_CPAREN)
			return "close-parenthesis";
		return "bad-token-type: " + type;
	}

	/* Things used internally by the Tokenizer */
	private String input; /* the input that is being tokenized */
	private int inputLen; /* the length of the input string */
	private int pos; /* where we are in the input */
	private int type; /* what the current token is */
	private String identVal; /* if token is T_IDENT, this is the name */
	private String stringVal; /* if token is T_IDENT, this is the name */
	// private double numberVal; /* if token is T_NUMBER, this is the value */
	private double numberVal; /* if token is T_NUMBER, this is the value */
	private int startIndex; /* index of start of current token in input */
	private char toc1 = new Pasers().getToc1();
	private char toc2 = new Pasers().getToc2();
	private char toc3 = new Pasers().getToc3();

	/**
	 * Returns true if there are more tokens left
	 * 
	 * @return boolean
	 */
	public boolean hasMoreTokens() {
		return pos != inputLen;
	}

	public void getVecteur(int NbrCase) {

		for (int i = 0; i < NbrCase; i++) {
			getNext();
			System.out.println(getNumber());
			getNext();
			getNext();
			System.out.println("" + getNumber());
			getNext();
			getNext();
		}
	}

	public TPoint getPoint() {

		double xd, yd;
		getNext();
		xd = getNumber();
		getNext();
		getNext();
		yd = getNumber();
		getNext();
		getNext();
		return new TPoint((int) xd, (int) yd);
	}

	public TPoint[] getVPoint(int NbrCase) {
		TPoint[] vP = new TPoint[NbrCase];
		for (int i = 0; i < NbrCase; i++) {
			vP[i] = getPoint();
		}
		return vP;
	}

	public TPoint[][] getMPoint(int NbrCase, int lignes) {
		TPoint[][] mP = new TPoint[NbrCase][lignes];
		for (int j = 0; j < lignes; j++)
			for (int i = 0; i < NbrCase; i++) {
				mP[i][j] = getPoint();
			}
		return mP;
	}

	public static void main(String[] args) {

		String str = "(343,546)(1,3)(2,3)";

		System.out.println(str.length());

		new PointTokenizer(str).getVecteur(3);
		/*
		 * System.out.println( str.length()); Tokenizer tok = new Tokenizer(str); //
		 * while(tok.hasMoreTokens()) for(int i=0;i<=str.length();i++) { tok.getNext();
		 * System.out.println( tok.getNumber()); tok.getNext(); tok.getNext();
		 * System.out.println( ""+ tok.getNumber()); tok.getNext(); tok.getNext(); }
		 */

	}

}
