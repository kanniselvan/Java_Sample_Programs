package com.kanni;

public class Palindrom {

	public static void main(String[] args) {
		
		String val="12343251";
		System.out.println(isPalindrome(0, val.length()-1, val));

	}

	public static boolean isPalindrome(int left, int right, String value) {

		if (null != value && !value.isEmpty()) {

			if (left >= right)
				return true;

			if (value.charAt(left) != value.charAt(right))
				return false;

			return isPalindrome(++left, --right, value);

		}

		return false;

	}

}
