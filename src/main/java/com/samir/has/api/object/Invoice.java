package com.samir.has.api.object;

import com.samir.has.api.object.livraison.Delivery;

import javax.xml.bind.annotation.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {

    @XmlAttribute private LocalUniqueId invoiceNumber;
    private String  issueDate;
     private LocalUniqueId customerId;
    @XmlElement(name = "items") private Map<LocalUniqueId, Integer> productsList;
    @XmlAnyElement private Delivery deliveryMode;
    private float price;
    private boolean paid = false;

    public Invoice(){}

    public Invoice(LocalUniqueId customerId, Delivery deliveryMode) {
        this.invoiceNumber = LocalUniqueId.randomInvoiceUniqueId();
        this.issueDate = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("fr", "FR")).format(new Date());
        //this.issueDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.customerId = customerId;
        this.productsList = new HashMap<LocalUniqueId, Integer>();
        this.deliveryMode = deliveryMode;
        this.price = 0;
        this.paid = false;
    }

    public LocalUniqueId getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(LocalUniqueId invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public LocalUniqueId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LocalUniqueId customer) {
        this.customerId = customer;
    }

    public Map<LocalUniqueId, Integer> getProductsList() {
        return productsList;
    }

    public void setProductsList(Map<LocalUniqueId, Integer> productsList) {
        this.productsList = productsList;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Delivery getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Delivery deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

}
