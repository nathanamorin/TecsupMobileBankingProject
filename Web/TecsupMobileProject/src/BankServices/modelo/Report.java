package BankServices.modelo;

public class Report {
private String date;
private String description;
private String amount;
private String status;
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Report(String date, String description, String amount, String status) {
	super();
	this.date = date;
	this.description = description;
	this.amount = amount;
	this.status = status;
}


}
