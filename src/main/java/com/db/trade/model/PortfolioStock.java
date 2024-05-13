package com.db.trade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio_stock")
public class PortfolioStock {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String stockId;

	 private int quantity;
	 
	 private String shareName;

	 private String shareType;
	 
	 public PortfolioStock() {}
	 
	 public PortfolioStock(String stockId,int quantity,String shareName,String shareType) {
		 this.stockId = stockId;
		 this.quantity = quantity;
		 this.shareName = shareName;
		 this.shareType = shareType;
	 }
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	@Override
    public String toString() {
        return "PortfolioStock{" +
                "stockId='" + stockId + '\'' +
                ", quantity=" + quantity + '\'' +
                ", shareName=" + shareName + '\'' +
                ", shareType=" + shareType + '\'' +
                '}';
    }
}
