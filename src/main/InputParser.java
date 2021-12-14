package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static utility.Utility.*;

public class InputParser extends InventoryManagement {
	// Seggregate Input Data

	static boolean parseInput(String input) {
		List<String> inputSplitted = new ArrayList<>(Arrays.asList(input.split(":")));

		if (inputSplitted.size() < 3) {
			println("Invalid Input!");
			return false;
		}

		purchaseCountry = inputSplitted.get(0).toLowerCase().trim();

		if (Passport.isPassport(inputSplitted.get(1)) == false) {
			inputSplitted.add(1, "DUMMY_PASSPORT");
			passportCountry = purchaseCountry;
		}

		passportNumber = inputSplitted.get(1);

		if (inputSplitted.get(2).equals("Gloves")) {
			glovesRequired = Integer.parseInt(inputSplitted.get(3));
			masksRequired = Integer.parseInt(inputSplitted.get(5));

		} else if (inputSplitted.get(2).equals("Mask")) {
			masksRequired = Integer.parseInt(inputSplitted.get(3));
			glovesRequired = Integer.parseInt(inputSplitted.get(5));
		}

		if (passportNumber.equals("DUMMY_PASSPORT") == false) {
			if (Passport.isUKPassport(passportNumber))
				passportCountry = "uk";
			else if (Passport.isGermanyPassport(passportNumber))
				passportCountry = "germany";
			else {
				println("Invalid Passport Country");
				return false;
			}
		}
		discountPercent = 20;

		return true;

	}
}
