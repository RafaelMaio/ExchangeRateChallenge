package com.exchange_rate.exchangeratechallenge;

import com.exchange_rate.exchangeratechallenge.exceptions.ApiRequestException;
import com.exchange_rate.exchangeratechallenge.model.ExchangeRateLatest;
import com.exchange_rate.exchangeratechallenge.model.Motd;
import com.exchange_rate.exchangeratechallenge.service.ServiceExRate;
import com.exchange_rate.exchangeratechallenge.service.ServiceFetchData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTests {

	@Mock private ServiceFetchData serviceFetchData;
	private ServiceExRate serviceExRate;

	@BeforeEach
	void setUp(){
		serviceExRate = new ServiceExRate(serviceFetchData);
	}

	private ExchangeRateLatest createExchangeRateLatest(){
		//given
		Map<String, Double> rates = new HashMap<>();
		rates.put("EUR", 1.0);
		rates.put("USD", 1.07);
		rates.put("GBP", 0.85);
		Motd motd = new Motd();
		ExchangeRateLatest exchangeRateLatest =
				new ExchangeRateLatest(motd, true, "EUR", LocalDate.now().toString(), rates);
		return exchangeRateLatest;
	}

	//TODO: Add more unit tests
	//TODO: Separate file by function

	@Test
	void shouldReturnExchangeRatesMapWithSize2() {
		//when
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		//then
		assertThat(serviceExRate.getExchangeData().size(), is(3));
	}

	@Test
	void shouldReturnExchangeRateEurToUsd() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThat(serviceExRate.getExchangeRateCA_CB("EUR", "USD")).isEqualTo(1.07);
	}

	@Test
	void shouldThrowExceptionExchangeRateNoneToUsd() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThatThrownBy(() -> serviceExRate.getExchangeRateCA_CB("NONE", "USD"))
				.isInstanceOf(ApiRequestException.class)
				.hasMessage("Currency A does not exist");
	}

	@Test
	void shouldReturnExchangeRateEurToAll() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThat(serviceExRate.getExchangeRateCA_All("EUR"), hasEntry("USD", 1.07));
	}

	@Test
	void shouldReturnExchangeRateUSDToAll() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());
		double expected_value = 0.85/1.07;
		assertThat(serviceExRate.getExchangeRateCA_All("USD"), hasEntry("GBP", expected_value));
	}

	@Test
	void shouldThrowExceptionExchangeRateNoneToAll() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThatThrownBy(() -> serviceExRate.getExchangeRateCA_All("NONE"))
				.isInstanceOf(ApiRequestException.class)
				.hasMessage("Currency A does not exist");
	}

	@Test
	void shouldReturnConversionValueEurToUsd() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThat(serviceExRate.getValueConversionCA_CB("EUR", "USD", 3.0)).isEqualTo(3.21);
	}

	@Test
	void shouldThrowExceptionConversionValueGbpToNone() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThatThrownBy(() -> serviceExRate.getValueConversionCA_CB("GBP", "NONE", 3.0))
				.isInstanceOf(ApiRequestException.class)
				.hasMessage("Currency B does not exist");
	}

	@Test
	void shouldReturnConversionValueEurToAll() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThat(serviceExRate.getValueConversionCA_All("EUR", 2.0), hasEntry("USD", 2.14));
	}

	@Test
	void shouldReturnConversionValueUSDToAll() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());
		double expected_value = 0.85/1.07 * 2.0;
		assertThat(serviceExRate.getValueConversionCA_All("USD", 2.0), hasEntry("GBP", expected_value));
	}

	@Test
	void shouldThrowExceptionConversionValueNoneToAll() {
		Mockito.when(serviceFetchData.getExchangeData()).thenReturn(createExchangeRateLatest());

		assertThatThrownBy(() -> serviceExRate.getValueConversionCA_All("NONE",5.0))
				.isInstanceOf(ApiRequestException.class)
				.hasMessage("Currency A does not exist");
	}
}