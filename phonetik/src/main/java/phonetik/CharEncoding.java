/*
 * CharEncoding.java
 *
 * Copyright (c) 2012, Thomas Jung. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package phonetik;

import static java.lang.String.format;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The <a href=
 * "http://en.wikipedia.org/w/index.php?title=Windows-1252&oldid=516505638#Code_page_layout">
 * CP1252 character encoding</a>.
 */
class CharEncoding {
	    
    private static final char[] DECIMAL_2_CHAR = {
			0x0000, // Null
			0x0001, // Start Of Heading
			0x0002, // Start Of Text
			0x0003, // End Of Text
			0x0004, // End Of Transmission
			0x0005, // Enquiry
			0x0006, // Acknowledge
			0x0007, // Bell
			0x0008, // Backspace
			0x0009, // Horizontal Tabulation
			0x000a, // Line Feed
			0x000b, // Vertical Tabulation
			0x000c, // Form Feed
			0x000d, // Carriage Return
			0x000e, // Shift Out
			0x000f, // Shift In
			0x0010, // Data Link Escape
			0x0011, // Device Control One
			0x0012, // Device Control Two
			0x0013, // Device Control Three
			0x0014, // Device Control Four
			0x0015, // Negative Acknowledge
			0x0016, // Synchronous Idle
			0x0017, // End Of Transmission Block
			0x0018, // Cancel
			0x0019, // End Of Medium
			0x001a, // Substitute
			0x001b, // Escape
			0x001c, // File Separator
			0x001d, // Group Separator
			0x001e, // Record Separator
			0x001f, // Unit Separator
			0x0020, // Space
			0x0021, // Exclamation Mark
			0x0022, // Quotation Mark
			0x0023, // Number Sign
			0x0024, // Dollar Sign
			0x0025, // Percent Sign
			0x0026, // Ampersand
			0x0027, // Apostrophe
			0x0028, // Left Parenthesis
			0x0029, // Right Parenthesis
			0x002a, // Asterisk
			0x002b, // Plus Sign
			0x002c, // Comma
			0x002d, // Hyphen-Minus
			0x002e, // Full Stop
			0x002f, // Solidus
			0x0030, // Digit Zero
			0x0031, // Digit One
			0x0032, // Digit Two
			0x0033, // Digit Three
			0x0034, // Digit Four
			0x0035, // Digit Five
			0x0036, // Digit Six
			0x0037, // Digit Seven
			0x0038, // Digit Eight
			0x0039, // Digit Nine
			0x003a, // Colon
			0x003b, // Semicolon
			0x003c, // Less-Than Sign
			0x003d, // Equals Sign
			0x003e, // Greater-Than Sign
			0x003f, // Question Mark
			0x0040, // Commercial At
			0x0041, // Latin Capital Letter A
			0x0042, // Latin Capital Letter B
			0x0043, // Latin Capital Letter C
			0x0044, // Latin Capital Letter D
			0x0045, // Latin Capital Letter E
			0x0046, // Latin Capital Letter F
			0x0047, // Latin Capital Letter G
			0x0048, // Latin Capital Letter H
			0x0049, // Latin Capital Letter I
			0x004a, // Latin Capital Letter J
			0x004b, // Latin Capital Letter K
			0x004c, // Latin Capital Letter L
			0x004d, // Latin Capital Letter M
			0x004e, // Latin Capital Letter N
			0x004f, // Latin Capital Letter O
			0x0050, // Latin Capital Letter P
			0x0051, // Latin Capital Letter Q
			0x0052, // Latin Capital Letter R
			0x0053, // Latin Capital Letter S
			0x0054, // Latin Capital Letter T
			0x0055, // Latin Capital Letter U
			0x0056, // Latin Capital Letter V
			0x0057, // Latin Capital Letter W
			0x0058, // Latin Capital Letter X
			0x0059, // Latin Capital Letter Y
			0x005a, // Latin Capital Letter Z
			0x005b, // Left Square Bracket
			0x005c, // Reverse Solidus
			0x005d, // Right Square Bracket
			0x005e, // Circumflex Accent
			0x005f, // Low Line
			0x0060, // Grave Accent
			0x0061, // Latin Small Letter A
			0x0062, // Latin Small Letter B
			0x0063, // Latin Small Letter C
			0x0064, // Latin Small Letter D
			0x0065, // Latin Small Letter E
			0x0066, // Latin Small Letter F
			0x0067, // Latin Small Letter G
			0x0068, // Latin Small Letter H
			0x0069, // Latin Small Letter I
			0x006a, // Latin Small Letter J
			0x006b, // Latin Small Letter K
			0x006c, // Latin Small Letter L
			0x006d, // Latin Small Letter M
			0x006e, // Latin Small Letter N
			0x006f, // Latin Small Letter O
			0x0070, // Latin Small Letter P
			0x0071, // Latin Small Letter Q
			0x0072, // Latin Small Letter R
			0x0073, // Latin Small Letter S
			0x0074, // Latin Small Letter T
			0x0075, // Latin Small Letter U
			0x0076, // Latin Small Letter V
			0x0077, // Latin Small Letter W
			0x0078, // Latin Small Letter X
			0x0079, // Latin Small Letter Y
			0x007a, // Latin Small Letter Z
			0x007b, // Left Curly Bracket
			0x007c, // Vertical Line
			0x007d, // Right Curly Bracket
			0x007e, // Tilde
			0x007f, // Delete
			0x20ac, // Euro Sign
			0x0000, //
			0x201a, // Single Low-9 Quotation Mark
			0x0192, // Latin Small Letter F With Hook
			0x201e, // Double Low-9 Quotation Mark
			0x2026, // Horizontal Ellipsis
			0x2020, // Dagger
			0x2021, // Double Dagger
			0x02c6, // Modifier Letter Circumflex Accent
			0x2030, // Per Mille Sign
			0x0160, // Latin Capital Letter S With Caron
			0x2039, // Single Left-Pointing Angle Quotation Mark
			0x0152, // Latin Capital Ligature Oe
			0x0000, //
			0x017d, // Latin Capital Letter Z With Caron
			0x0000, //
			0x0000, //
			0x2018, // Left Single Quotation Mark
			0x2019, // Right Single Quotation Mark
			0x201c, // Left Double Quotation Mark
			0x201d, // Right Double Quotation Mark
			0x2022, // Bullet
			0x2013, // En Dash
			0x2014, // Em Dash
			0x02dc, // Small Tilde
			0x2122, // Trade Mark Sign
			0x0161, // Latin Small Letter S With Caron
			0x203a, // Single Right-Pointing Angle Quotation Mark
			0x0153, // Latin Small Ligature Oe
			0x0000, //
			0x017e, // Latin Small Letter Z With Caron
			0x0178, // Latin Capital Letter Y With Diaeresis
			0x00a0, // No-Break Space
			0x00a1, // Inverted Exclamation Mark
			0x00a2, // Cent Sign
			0x00a3, // Pound Sign
			0x00a4, // Currency Sign
			0x00a5, // Yen Sign
			0x00a6, // Broken Bar
			0x00a7, // Section Sign
			0x00a8, // Diaeresis
			0x00a9, // Copyright Sign
			0x00aa, // Feminine Ordinal Indicator
			0x00ab, // Left-Pointing Double Angle Quotation Mark
			0x00ac, // Not Sign
			0x00ad, // Soft Hyphen
			0x00ae, // Registered Sign
			0x00af, // Macron
			0x00b0, // Degree Sign
			0x00b1, // Plus-Minus Sign
			0x00b2, // Superscript Two
			0x00b3, // Superscript Three
			0x00b4, // Acute Accent
			0x00b5, // Micro Sign
			0x00b6, // Pilcrow Sign
			0x00b7, // Middle Dot
			0x00b8, // Cedilla
			0x00b9, // Superscript One
			0x00ba, // Masculine Ordinal Indicator
			0x00bb, // Right-Pointing Double Angle Quotation Mark
			0x00bc, // Vulgar Fraction One Quarter
			0x00bd, // Vulgar Fraction One Half
			0x00be, // Vulgar Fraction Three Quarters
			0x00bf, // Inverted Question Mark
			0x00c0, // Latin Capital Letter A With Grave
			0x00c1, // Latin Capital Letter A With Acute
			0x00c2, // Latin Capital Letter A With Circumflex
			0x00c3, // Latin Capital Letter A With Tilde
			0x00c4, // Latin Capital Letter A With Diaeresis
			0x00c5, // Latin Capital Letter A With Ring Above
			0x00c6, // Latin Capital Ligature Ae
			0x00c7, // Latin Capital Letter C With Cedilla
			0x00c8, // Latin Capital Letter E With Grave
			0x00c9, // Latin Capital Letter E With Acute
			0x00ca, // Latin Capital Letter E With Circumflex
			0x00cb, // Latin Capital Letter E With Diaeresis
			0x00cc, // Latin Capital Letter I With Grave
			0x00cd, // Latin Capital Letter I With Acute
			0x00ce, // Latin Capital Letter I With Circumflex
			0x00cf, // Latin Capital Letter I With Diaeresis
			0x00d0, // Latin Capital Letter Eth
			0x00d1, // Latin Capital Letter N With Tilde
			0x00d2, // Latin Capital Letter O With Grave
			0x00d3, // Latin Capital Letter O With Acute
			0x00d4, // Latin Capital Letter O With Circumflex
			0x00d5, // Latin Capital Letter O With Tilde
			0x00d6, // Latin Capital Letter O With Diaeresis
			0x00d7, // Multiplication Sign
			0x00d8, // Latin Capital Letter O With Stroke
			0x00d9, // Latin Capital Letter U With Grave
			0x00da, // Latin Capital Letter U With Acute
			0x00db, // Latin Capital Letter U With Circumflex
			0x00dc, // Latin Capital Letter U With Diaeresis
			0x00dd, // Latin Capital Letter Y With Acute
			0x00de, // Latin Capital Letter Thorn
			0x00df, // Latin Small Letter Sharp S
			0x00e0, // Latin Small Letter A With Grave
			0x00e1, // Latin Small Letter A With Acute
			0x00e2, // Latin Small Letter A With Circumflex
			0x00e3, // Latin Small Letter A With Tilde
			0x00e4, // Latin Small Letter A With Diaeresis
			0x00e5, // Latin Small Letter A With Ring Above
			0x00e6, // Latin Small Ligature Ae
			0x00e7, // Latin Small Letter C With Cedilla
			0x00e8, // Latin Small Letter E With Grave
			0x00e9, // Latin Small Letter E With Acute
			0x00ea, // Latin Small Letter E With Circumflex
			0x00eb, // Latin Small Letter E With Diaeresis
			0x00ec, // Latin Small Letter I With Grave
			0x00ed, // Latin Small Letter I With Acute
			0x00ee, // Latin Small Letter I With Circumflex
			0x00ef, // Latin Small Letter I With Diaeresis
			0x00f0, // Latin Small Letter Eth
			0x00f1, // Latin Small Letter N With Tilde
			0x00f2, // Latin Small Letter O With Grave
			0x00f3, // Latin Small Letter O With Acute
			0x00f4, // Latin Small Letter O With Circumflex
			0x00f5, // Latin Small Letter O With Tilde
			0x00f6, // Latin Small Letter O With Diaeresis
			0x00f7, // Division Sign
			0x00f8, // Latin Small Letter O With Stroke
			0x00f9, // Latin Small Letter U With Grave
			0x00fa, // Latin Small Letter U With Acute
			0x00fb, // Latin Small Letter U With Circumflex
			0x00fc, // Latin Small Letter U With Diaeresis
			0x00fd, // Latin Small Letter Y With Acute
			0x00fe, // Latin Small Letter Thorn
			0x00ff, // Latin Small Letter Y With Diaeresis	
	};
    
    static final int NCHARS = DECIMAL_2_CHAR.length;
    private static final int[] CHAR_2_DECIMAL = new int[1<<14];
    
	static {
		assert NCHARS == 1<<8;
		for (int i = 1; i < NCHARS; i++) {
			if (isDefined(i)) CHAR_2_DECIMAL[DECIMAL_2_CHAR[i]] = i;
    	}
    }

	static boolean isDefined(int i) {
		return DECIMAL_2_CHAR[i] != '\0';
	}

	public static int toDecimalOrZero(char c) {
    	return c < CHAR_2_DECIMAL.length ? CHAR_2_DECIMAL[c] :  0;
	}
    	
	/**
	 * Convert the character the position in the encoding (n-th byte).
	 */
	public static int toDecimal(char c) {
		int d = toDecimalOrZero(c);
		if (d == 0) {
			String msg = format("Character %d undefined.", (int) c);
			throw new IllegalArgumentException(msg);
		}
		return d;
    }
    
	/**
	 * Convert the position in the encoding (n-th byte) to a character.
	 */
	public static char toChar(int d) {
		if (d > 0 && d < NCHARS && isDefined(d))
			return DECIMAL_2_CHAR[d];
		throw new IllegalArgumentException(format("Character %d undefined.", d));
	}
	
	static Set<Character> allChars() {
		Set<Character> cs = new HashSet<Character>();
		for(char c : DECIMAL_2_CHAR) cs.add(c);
		while(cs.remove((char) 0));
		return Collections.unmodifiableSet(cs);
	}
}