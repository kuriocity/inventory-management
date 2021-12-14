package main;

public class Passport {
	final static String UK_PASSPORT_REGEX = "B[0-9]{3}[A-Z]{2}[a-zA-Z0-9]{7}";
	final static String GERMANY_PASSPORT_REGEX = "A[A-Z]{2}[a-zA-Z0-9]{9}";

	static boolean isPassport(String passportNumber) {

		return isGermanyPassport(passportNumber) || isUKPassport(passportNumber);
	}

	static boolean isUKPassport(String passportNumber) {

		return passportNumber.matches(UK_PASSPORT_REGEX);
	}

	static boolean isGermanyPassport(String passportNumber) {

		return passportNumber.matches(GERMANY_PASSPORT_REGEX);
	}

}
