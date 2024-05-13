package com.db.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.trade.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
	List<Trade> getByTradeId(Long tradeId);
}
