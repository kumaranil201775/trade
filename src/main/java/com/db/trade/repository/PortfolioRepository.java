package com.db.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.trade.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long>{
	List<Portfolio> findPortfolioByTraderId(String traderId);
}
