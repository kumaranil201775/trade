package com.db.trade.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "portfolio")
public class Portfolio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String traderId;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "portfolio_stock_id")
	private PortfolioStock portfolioStock;
	
	public Portfolio() {}
	
	public Portfolio(String traderId,PortfolioStock portfolioStock) {
		this.traderId = traderId;
		this.portfolioStock = portfolioStock;
	}
	
	public PortfolioStock getPortfolioStock() {
		return portfolioStock;
	}

	public void setPortfolioStock(PortfolioStock portfolioStock) {
		this.portfolioStock = portfolioStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	@Override
    public String toString() {
        return "Portfolio{" +
                "id='" + id + '\'' +
                ", traderId=" + traderId + '\'' +
                ", portfolioStock=" + portfolioStock + '\'' +
                '}';
    }
}
