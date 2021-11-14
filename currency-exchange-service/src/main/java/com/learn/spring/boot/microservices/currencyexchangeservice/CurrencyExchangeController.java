package com.learn.spring.boot.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

		if(currencyExchange == null) {
			throw new RuntimeException("Unable to find data from " +from+" to "+to);
		}
		log.info("retrieveExchangeValue get called with  {} to {} ",from,to);
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}

}
