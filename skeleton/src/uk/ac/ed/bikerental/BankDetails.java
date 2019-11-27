package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Objects;
import java.time.LocalDate;
public class BankDetails{
	private String name;
	private BigDecimal credit_card_nr;
	private LocalDate expiry_date;
	private BigDecimal security_code;
	public BankDetails(BigDecimal credit_nr,String Name, LocalDate expiry,BigDecimal code){
		this.name = Name;
		this.credit_card_nr = credit_nr;
		this.expiry_date = expiry;
		this.security_code = code;
	}
	public String getName(){
		return this.name;
	}
	public BigDecimal getCreditCardNr(){
		return this.credit_card_nr;
	}
	public LocalDate getExpiryDate(){
		return this.expiry_date;
	}
	public BigDecimal getSecurityCode(){
		return this.security_code;
	}

}
