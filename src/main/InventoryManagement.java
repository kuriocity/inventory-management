package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static utility.Utility.*;

public class InventoryManagement {

	static int masksRequired;
	static int glovesRequired;
	static String purchaseCountry;
	static String passportNumber;
	static String passportCountry;
	static int discountPercent;
	final String GERMANY = "Germany";
	final String UK = "UK";
	static Product ukMask = new Product("mask", 100, 65, "uk");
	static Product germanyMask = new Product("mask", 100, 100, "germany");

	static Product ukGlove = new Product("glove", 100, 100, "uk");
	static Product germanyGlove = new Product("glove", 50, 150, "germany");

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		println("INPUT :");
		String input = scan.nextLine().trim().replaceAll(" ", "");

		boolean validInput = InputParser.parseInput(input);

		if (validInput == false)
			return;

		int totalSalePrice = 0;
		int maskTakenFromUk = 0;
		int maskTakenFromGermany = 0;
		int glovesTakenFromUk = 0;
		int glovesTakenFromGermany = 0;

		if (masksRequired > (ukMask.quantity + germanyMask.quantity)
				|| glovesRequired > (ukGlove.quantity + germanyGlove.quantity)) {
			println("OUT_OF_STOCK:" + ukMask.quantity + ":" + germanyMask.quantity + ":" + ukGlove.quantity + ":"
					+ germanyGlove.quantity);
			return;
		}
		int masksRemainder = masksRequired % 10;
		int glovesRemainder = glovesRequired % 10;

		masksRequired = (masksRequired / 10) * 10;
		glovesRequired = (glovesRequired / 10) * 10;

		int costOf10GlovesUk = ProductCostCalculate.calculateCostOfKGlovesUk(10);
		int costOf10GlovesGermany = ProductCostCalculate.calculateCostOfKGlovesGermany(10);

		int costOf10MasksUk = ProductCostCalculate.calculateCostOfKMasksUk(10);
		int costOf10MasksGermany = ProductCostCalculate.calculateCostOfKMasksGermany(10);

		// GLOVES Cost

		if (costOf10GlovesUk < costOf10GlovesGermany) {
			if (glovesRequired > ukGlove.quantity) {
				glovesTakenFromUk = ukGlove.quantity;
				glovesRequired -= glovesTakenFromUk;
				glovesTakenFromGermany = glovesRequired;

			} else {
				glovesTakenFromUk = glovesRequired;
			}

			totalSalePrice += costOf10GlovesUk * (glovesTakenFromUk / 10);
			totalSalePrice += costOf10GlovesGermany * (glovesTakenFromGermany / 10);
			glovesRequired = 0;

		} else {
			if (glovesRequired > germanyGlove.quantity) {
				glovesTakenFromGermany = germanyGlove.quantity;
				glovesRequired -= glovesTakenFromGermany;
				glovesTakenFromUk = glovesRequired;

			} else {
				glovesTakenFromGermany = glovesRequired;
			}

			totalSalePrice += costOf10GlovesUk * (glovesTakenFromUk / 10);
			totalSalePrice += costOf10GlovesGermany * (glovesTakenFromGermany / 10);
			glovesRequired = 0;
		}

		// MASKS Cost

		if (costOf10MasksUk < costOf10MasksGermany) {
			if (masksRequired > ukMask.quantity) {
				maskTakenFromUk = ukMask.quantity;
				masksRequired -= maskTakenFromUk;
				maskTakenFromGermany = masksRequired;

			} else {
				maskTakenFromUk = masksRequired;
			}

			totalSalePrice += costOf10MasksUk * (maskTakenFromUk / 10);
			totalSalePrice += costOf10MasksGermany * (maskTakenFromGermany / 10);
			masksRequired = 0;

		} else {
			if (masksRequired > germanyMask.quantity) {
				maskTakenFromGermany = germanyMask.quantity;
				masksRequired -= maskTakenFromGermany;
				maskTakenFromUk = masksRequired;

			} else {
				maskTakenFromGermany = masksRequired;
			}

			totalSalePrice += costOf10MasksUk * (maskTakenFromUk / 10);
			totalSalePrice += costOf10MasksGermany * (maskTakenFromGermany / 10);
			masksRequired = 0;
		}

		// Remainder Calculation
		if (masksRemainder != 0) {
			int costOfKMasksUk = ProductCostCalculate.calculateCostOfKMasksUk(masksRemainder);
			int costOfKMasksGermany = ProductCostCalculate.calculateCostOfKMasksGermany(masksRemainder);

			if (maskTakenFromUk == ukMask.quantity) {
				maskTakenFromGermany += masksRemainder;
				totalSalePrice += costOfKMasksGermany;

			} else if (maskTakenFromGermany == germanyMask.quantity) {
				maskTakenFromUk += masksRemainder;
				totalSalePrice += costOfKMasksUk;

			} else {
				if (costOfKMasksUk < costOfKMasksGermany)
					maskTakenFromUk += masksRemainder;
				else
					maskTakenFromGermany += masksRemainder;

				totalSalePrice += Math.min(costOfKMasksUk, costOfKMasksGermany);

			}
		}
		if (glovesRemainder != 0) {
			int costOfKGlovesUk = ProductCostCalculate.calculateCostOfKGlovesUk(glovesRemainder);
			int costOfKGlovesGermany = ProductCostCalculate.calculateCostOfKGlovesGermany(glovesRemainder);

			if (glovesTakenFromUk == ukGlove.quantity) {
				glovesTakenFromGermany += glovesRemainder;
				totalSalePrice += costOfKGlovesGermany;
			} else if (glovesTakenFromGermany == germanyGlove.quantity) {
				glovesTakenFromUk += glovesRemainder;
				totalSalePrice += costOfKGlovesUk;
			} else {
				if (costOfKGlovesUk < costOfKGlovesGermany)
					glovesTakenFromUk += glovesRemainder;
				else
					glovesTakenFromGermany += glovesRemainder;

				totalSalePrice += Math.min(costOfKGlovesUk, costOfKGlovesGermany);

			}
		}
		// Final Output
		
		println("OUTPUT :");
		println(totalSalePrice + ":" + (ukMask.quantity - maskTakenFromUk) + ":"
				+ (germanyMask.quantity - maskTakenFromGermany) + ":" + (ukGlove.quantity - glovesTakenFromUk) + ":"
				+ (germanyGlove.quantity - glovesTakenFromGermany));

	}

}
