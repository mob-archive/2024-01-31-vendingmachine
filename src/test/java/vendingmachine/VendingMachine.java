package vendingmachine;

import java.text.NumberFormat;
import java.util.Locale;

public class VendingMachine {

	private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
	private double cents;
	private int coinReturn;

	public String getText() {
		if (cents == 0) {
			return "INSERT COIN";
		}
		return numberFormat.format(cents / 100);
	}

	public void insertCoin(int cents) {
		// TODO Migrieren auf allowlist
		if (cents == 1) {
			coinReturn = cents;
			return;
		}
		this.cents += cents;
	}

	public double getCoinReturn() {
		return coinReturn;
	}

}
