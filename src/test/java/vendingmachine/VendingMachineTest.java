package vendingmachine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VendingMachineTest {

	VendingMachine sut;

	@Test
	void shouldDisplayInsertCoinOnInitialState() {
		givenEmptyVendingMachine();
		thenDisplayedTextIs("INSERT COIN");
	}

	@Test
	void shouldDisplayFiveCentsWhenNickelIsInserted() {
		givenEmptyVendingMachine();
		whenCoinIsInserted(5);
		thenDisplayedTextIs("$0.05");
	}

	@Test
	void shouldDisplayTenCentsWhenDimeIsInserted() {
		givenEmptyVendingMachine();
		whenCoinIsInserted(10);
		thenDisplayedTextIs("$0.10");
	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 10, 25 })
	void onAnyValidCoinTheCoinReturnIsEmpty(int coin) {
		givenEmptyVendingMachine();
		whenCoinIsInserted(coin);
		assertThat(sut.getCoinReturn()).isEqualTo(0);
	}

	@Test
	void shouldDisplay35CentsWhenDimeAndQuarterAreInserted() {
		givenEmptyVendingMachine();
		whenCoinIsInserted(10);
		whenCoinIsInserted(25);
		thenDisplayedTextIs("$0.35");
	}

	@Test
	void shouldRejectPennyAsInvalidCoin() {
		givenEmptyVendingMachine();
		whenCoinIsInserted(1);
		assertSoftly(s -> {
			s.assertThat(sut.getText()).isEqualTo("INSERT COIN");
			s.assertThat(sut.getCoinReturn()).isEqualTo(1);
		});
	}

	// TODO mehrere invalide
	// TODO valide, dann invalide

	void thenDisplayedTextIs(String expectedText) {
		assertThat(sut.getText()).isEqualTo(expectedText);
	}

	void whenCoinIsInserted(int i) {
		sut.insertCoin(i);
	}

	void givenEmptyVendingMachine() {
		sut = new VendingMachine();
	}

}
