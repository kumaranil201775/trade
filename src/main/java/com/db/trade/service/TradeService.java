package com.db.trade.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.trade.model.Portfolio;
import com.db.trade.model.Trade;
import com.db.trade.model.Wallet;
import com.db.trade.repository.PortfolioRepository;
import com.db.trade.repository.TradeRepository;
import com.db.trade.repository.WalletRepository;

@Service
public class TradeService {

	private static final String SELL = "SELL";
	
	private static final String BUY = "BUY";
	
	private static final int ZERO = 0;
	
	@Autowired
    private TradeRepository tradeRepository;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    private WalletRepository walletRepository;
    
    @Transactional
    public Trade saveTrade(Trade trade) {
		AtomicReference<String> traderId = new AtomicReference<String>(trade.getTraderId());
		AtomicBoolean status = new AtomicBoolean(false);
		if (tradeRepository.findAll().stream().anyMatch(t -> t.getTraderId().equalsIgnoreCase(traderId.get()))) {
			walletRepository.findAll().stream().forEach(t -> {
				if (validateWalletAndQuantity(t, trade)) {
					if (trade.getShareType().equalsIgnoreCase(BUY)) {
						t.setBalance(t.getBalance() - trade.getSharePrice());
					}
					if (trade.getShareType().equalsIgnoreCase(SELL)) {
						t.setTotalQuantity(t.getTotalQuantity() - trade.getShareQuantity());
					}
					t.setTraderId(trade.getTraderId());
					trade.setWallet(t);
					status.set(true);
					walletRepository.save(t);
				} else {
					throw new EntityNotFoundException("Check balance or quantity");
				}
			});
		}
		else {
			Wallet wallet = new Wallet(trade.getTraderId(), trade.getSharePrice(), trade.getShareQuantity());
			trade.setWallet(wallet);
			return tradeRepository.save(trade);
		}
		if(status.get()) {
			return tradeRepository.save(trade);
		}
		else {
			throw new EntityNotFoundException("Trade aborted");
		}
		
    }
    
    public List<Trade> findAll(){
    	return  tradeRepository.findAll();
    }
    
	public List<Portfolio> getPortfolio(String traderId){ 
		return portfolioRepository.findPortfolioByTraderId(traderId);
	}
	
	public List<Wallet> getWalletByTraderId(String traderId){ 
		return walletRepository.getWalletByTraderId(traderId);
	}
	
	public List<Trade> getByTradeId(Long tradeId){
		return tradeRepository.getByTradeId(tradeId);
	}
	
    private boolean validateWalletAndQuantity(Wallet wallet,Trade trade){
    	boolean flagQuantity=false,flagBalance = false;
    	if(trade.getShareType().equalsIgnoreCase(SELL) &&
    			(wallet.getTotalQuantity()-trade.getShareQuantity())>=ZERO) {
    		flagQuantity = true;
    	}
    	if(trade.getShareType().equalsIgnoreCase(BUY) &&
    			(wallet.getBalance()-trade.getSharePrice())>=ZERO) {
    		flagBalance = true;
    	}
    	if(flagBalance || flagQuantity)
    		return true;
    	else
    		return false;
    }
}
