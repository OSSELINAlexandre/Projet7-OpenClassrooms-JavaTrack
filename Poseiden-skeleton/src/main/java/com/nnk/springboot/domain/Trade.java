package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
@Table(name = "trade")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Tradeid")
	private int TradeId;

	@NotBlank( message="Account is mandatory")
	@Column(name = "account")
	private String account;

	@NotBlank( message="Type is mandatory")
	@Column(name = "type")
	private String type;

	@Column(name = "buyquantity")
	private Double buyQuantity;

	@Column(name = "sellquantity")
	private Double sellQuantity;

	@Column(name = "buyprice")
	private Double buyPrice;

	@Column(name = "sellprice")
	private Double sellPrice;

	@Column(name = "tradedate")
	private Timestamp tradeDate;

	@Column(name = "security")
	private String security;

	@Column(name = "status")
	private String status;

	@Column(name = "trader")
	private String trader;

	@Column(name = "benchmark")
	private String benchmark;

	@Column(name = "book")
	private String book;

	@Column(name = "creationname")
	private String creationName;

	@Column(name = "creationdate")
	private Timestamp creationDate;

	@Column(name = "revisionname")
	private String revisionName;

	@Column(name = "revisiondate")
	private Timestamp revisionDate;

	@Column(name = "dealname")
	private String dealName;

	@Column(name = "dealtype")
	private String dealType;

	@Column(name = "sourcelistid")
	private String sourceListId;

	@Column(name = "side")
	private String side;
	
	private Boolean buyIsTrueSellIsFalse;
	
	private Double priceFromForm;
	
	private Double quantityFromForm;

	public Trade() {
		super();
	}

	
	public Trade(String Tradeaccount, String Type) {
		
		this.account = Tradeaccount;
		this.type = Type;
	}

	public int getTradeId() {
		return TradeId;
	}

	public void setTradeId(int tradeId) {
		TradeId = tradeId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Timestamp getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Timestamp tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCreationName() {
		return creationName;
	}

	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public Timestamp getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Timestamp revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getSourceListId() {
		return sourceListId;
	}

	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Boolean getBuyIsTrueSellIsFalse() {
		return buyIsTrueSellIsFalse;
	}

	public void setBuyIsTrueSellIsFalse(Boolean buyIsTrueSellIsFalse) {
		this.buyIsTrueSellIsFalse = buyIsTrueSellIsFalse;
	}

	public Double getPriceFromForm() {
		return priceFromForm;
	}

	public void setPriceFromForm(Double priceFromForm) {
		this.priceFromForm = priceFromForm;
	}

	public Double getQuantityFromForm() {
		return quantityFromForm;
	}

	public void setQuantityFromForm(Double quantityFromForm) {
		this.quantityFromForm = quantityFromForm;
	}
	
	
	


}
