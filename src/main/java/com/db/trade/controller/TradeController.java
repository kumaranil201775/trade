package com.db.trade.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.trade.model.Portfolio;
import com.db.trade.model.PortfolioStock;
import com.db.trade.model.Trade;
import com.db.trade.model.Wallet;
import com.db.trade.service.TradeService;

@RestController
public class TradeController {
	
    private static final Logger log = LoggerFactory.getLogger(TradeController.class);

	@Autowired
	TradeService tradeService;

	@PostMapping("trade")
	public ResponseEntity<Trade> placeTrade(@RequestBody Trade trade) {
		
			PortfolioStock portfolioStock = new PortfolioStock(trade.getShareId(),trade.getShareQuantity(),
					trade.getShareName(),trade.getShareType()); 
			Portfolio portfolio = new Portfolio(trade.getTraderId(),portfolioStock);
			trade.setPortfolio(portfolio);
			log.info("calling trade service");
			
			Trade saveTrade = tradeService.saveTrade(trade);
			
			return ResponseEntity.ok(saveTrade);		
	}
	
	@PostMapping("getByTradeId/{tradeId}")
	public List<Trade> getByTradeId(@PathVariable Long tradeId){
		return tradeService.getByTradeId(tradeId);
	}
	
	@PostMapping("getPortfolio/{traderId}")
	public List<Portfolio> getPortfolio(@PathVariable String traderId){  
	  return tradeService.getPortfolio(traderId); 
	}
	
	@PostMapping("getWalletByTraderId/{traderId}")
	public List<Wallet> getWalletByTraderId(@PathVariable String traderId){  
	  return tradeService.getWalletByTraderId(traderId); 
	}
	
	@GetMapping("getTrade")
	public List<Trade> findAllTrades() {
		return tradeService.findAll();
	}
}
