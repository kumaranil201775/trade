package com.db.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.trade.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long>{
	List<Wallet> getWalletByTraderId(String traderId);
}
