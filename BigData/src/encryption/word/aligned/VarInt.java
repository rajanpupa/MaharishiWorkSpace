package encryption.word.aligned;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VarInt {

	public static int ByteRequiredForInt(int n) {
		int size = 1;
		if ((n >>> 28) != 0)
			size = 5;
		else if ((n >>> 21) != 0)
			size = 4;
		else if ((n >>> 14) != 0)
			size = 3;
		else if ((n >>> 7) != 0)
			size = 2;

		return size;
	}

	public static Byte[] encodeInt(int n) {
		int size = ByteRequiredForInt(n);

		Byte[] code = new Byte[size];

		for (int i = 0; i < size; i++) {
			code[i] = new Byte((byte) (n & 0x0000007F));
			n = n >>> 7;
		}
		Byte mask = (byte) 0x80;
		code[0] = (byte) (code[0] | mask);

		return code;
	}

	public static int decodeInt(Byte[] code) {
		Byte mask = (byte) 0x7F;
		code[0] = (byte) (code[0] & mask);

		int n = 0;
		for (int i = code.length - 1; i >= 0; i--) {
			n = n << 7;
			n = (n | code[i]);
		}
		return n;
	}

	public static Byte[] encode(Integer[] a) {

		int size = 0;

		for (int i : a) {
			size += ByteRequiredForInt(i);
		}

		Byte[] Bytes = new Byte[size];
		int count = 0;

		for (int in : a) {
			Byte[] intBytes = encodeInt(in);
			for (int i = intBytes.length - 1; i >= 0; i--) {// Byte b:
															// encodeInt(in)
				Bytes[count++] = intBytes[i];
			}
		}

		return Bytes;
		// you can try this
	}

	public static Integer[] decode(Byte[] code) {

		Integer[] intArr = new Integer[countBytesStartOne(code)];
		int count = 0;

		List<Byte> b;
		for (int i = 0; i < intArr.length; i++, count++) {
			b = new ArrayList<Byte>();
			int j = 0;
			for (; (code[count] & 0b10000000) <= 0; count++) {// starting with 0
				b.add(code[count]);
			}
			b.add(code[count]);
			Byte[] barray;
			Collections.reverse(b);
			intArr[i] = decodeInt(b.toArray(new Byte[b.size()]));
		}

		return intArr;
	}

	public static int countBytesStartOne(Byte[] code) {
		int count = 0;

		for (Byte b : code) {
			if ((b & 0b10000000) > 0) {
				count++;
			}
		}

		return count;
	}

	public static String toStringByte(Byte b) {
		String str = "";
		for (int i = 0; i < 8; i++) {
			str += (b < 0) ? "1" : "0";
			b = (byte) (b << 1);
		}
		return str;
	}

	public static void main(String[] args) {
		Integer[] plist = { 100, 8, 150, 7, 300, 24, 500, 36 };
		
		printArray("Original Array", plist);

		Integer[] dgapList = dgap(plist);
		printArray("after d-gap", dgapList);

		printBytesRequired("Bytes required ", dgapList);

		Byte[] ByteList = encode(dgapList);
		System.out.printf("%1$20s", "Bytes => ");
		for (Byte b : ByteList) {
			printByte(b);
		}

		System.out.println();

		Integer[] decodedInts = decode(ByteList);
		printArray("Decoded array", decodedInts);

		Integer [] dgapReversed = dgapReverse(decodedInts);
		printArray("Dcode Reversed", dgapReversed);
	}

	public static void printByte(Byte b) {
		Byte b2 = b;
//		String s2 = String.format("%10s", Integer.toBinaryString(b2 & 0xFF))
//				.subs.replace(' ', '0');
		String s2 = toStringByte(b2);
		System.out.printf("%10s",s2); // 00000010
	}

	public static Integer[] dgap(Integer[] arr) {

		Integer[] dgapArray = new Integer[arr.length];

		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				// dgapArray[i] = arr[i];
				if (i == 0) {
					dgapArray[i] = arr[i];
				} else {
					dgapArray[i] = arr[i] - arr[i - 2];
				}
			} else {

				dgapArray[i] = arr[i];
			}
		}

		return dgapArray;
	}

	public static Integer[] dgapReverse(Integer[] arr) {

		Integer[] dgapReverseArray = new Integer[arr.length];

		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				// dgapArray[i] = arr[i];
				if (i == 0) {
					dgapReverseArray[i] = arr[i];
				} else {
					dgapReverseArray[i] = arr[i] + dgapReverseArray[i - 2];
				}
			} else {

				dgapReverseArray[i] = arr[i];
			}
		}

		return dgapReverseArray;
	}

	public static void printArray(String message, Integer[] arr) {
		System.out.printf("%1$20s", message + " => ");
		for (Integer i : arr) {
			System.out.printf("%10s", i);
		}
		System.out.println();
	}

	public static void printBytesRequired(String message, Integer[] arr) {
		System.out.printf("%1$20s", message + " => ");
		for (int i : arr) {
			System.out.printf("%10d", ByteRequiredForInt(i));
		}
		System.out.println();
	}

}