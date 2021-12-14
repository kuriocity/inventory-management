package main;

public class ProductCostCalculate extends InventoryManagement {
	
	// Calculate Cost of K Item

	static int calculateCostOfKGlovesGermany(int glovesCount) {
		int transportCost = 0;
		if (!purchaseCountry.equalsIgnoreCase("germany")) {
			if (passportCountry.equalsIgnoreCase("germany"))
				transportCost = 400 * (100 - discountPercent) / 100;
			else
				transportCost = 400;
		}

		return germanyGlove.cost * glovesCount + transportCost * (glovesCount / 10 + (glovesCount % 10 != 0 ? 1 : 0));
	}

	static int calculateCostOfKGlovesUk(int glovesCount) {
		int transportCost = 0;
		if (!purchaseCountry.equalsIgnoreCase("uk")) {
			if (passportCountry.equalsIgnoreCase("uk"))
				transportCost = 400 * (100 - discountPercent) / 100;
			else
				transportCost = 400;
		}

		return ukGlove.cost * glovesCount + transportCost * (glovesCount / 10 + (glovesCount % 10 != 0 ? 1 : 0));
	}

	static int calculateCostOfKMasksGermany(int masksCount) {
		int transportCost = 0;
		if (!purchaseCountry.equalsIgnoreCase("germany")) {
			if (passportCountry.equalsIgnoreCase("germany"))
				transportCost = 400 * (100 - discountPercent) / 100;
			else
				transportCost = 400;
		}

		return germanyMask.cost * masksCount + transportCost * (masksCount / 10 + (masksCount % 10 != 0 ? 1 : 0));
	}

	static int calculateCostOfKMasksUk(int masksCount) {
		int transportCost = 0;
		if (!purchaseCountry.equalsIgnoreCase("uk")) {
			if (passportCountry.equalsIgnoreCase("uk"))
				transportCost = 400 * (100 - discountPercent) / 100;
			else
				transportCost = 400;
		}

		return ukMask.cost * masksCount + transportCost * (masksCount / 10 + (masksCount % 10 != 0 ? 1 : 0));
	}

}
