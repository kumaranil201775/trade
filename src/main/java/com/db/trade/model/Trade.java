package com.db.trade.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=true)
    private Long tradeId;

    private String traderId;
    
    private String shareId;

    private String shareName;

    private String shareType;

    private int shareQuantity;

    private double sharePrice;

    private String shareStatus;
    
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "portfolio_trader_id")
    private Portfolio portfolio;
    
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wallet_trader_id")
    private Wallet wallet;
    
    public Trade() {}
    
    public Trade(String traderId,String shareId,String shareName,int shareQuantity,double sharePrice,String shareStatus) {
    	this.traderId = traderId;
    	this.shareId = shareId;
    	this.shareName = shareName;
    	this.shareQuantity  = shareQuantity;
    	this.sharePrice = sharePrice;
    	this.shareStatus = shareStatus;
    }
    
    public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public int getShareQuantity() {
		return shareQuantity;
	}

	public void setShareQuantity(int shareQuantity) {
		this.shareQuantity = shareQuantity;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

	public String getShareStatus() {
		return shareStatus;
	}

	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
	}

	@Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", traderId=" + traderId + '\'' +
                ", shareId=" + shareId + '\'' +
                ", shareName='" + shareName + '\'' +
                ", shareType='" + shareType + '\'' +
                ", shareQuantity=" + shareQuantity + '\'' +
                ", sharePrice=" + sharePrice + '\'' +
                ", shareStatus='" + shareStatus + '\'' +
                '}';
    }
}
