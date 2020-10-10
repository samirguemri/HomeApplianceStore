package com.samir.has.api.service;

import com.samir.has.api.doa.IInvoiceDao;
import com.samir.has.api.doa.IProductDao;
import com.samir.has.api.object.Invoice;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    private final IInvoiceDao invoiceDao;
    private final IProductDao productDao;

    @Autowired
    public InvoiceService(@Qualifier("fakeProductDao") IProductDao productDao, @Qualifier("fakeBillDao") IInvoiceDao invoiceDao) {
        this.productDao = productDao;
        this.invoiceDao = invoiceDao;
    }

    public void addInvoice(Invoice invoice){
        this.invoiceDao.insertInvoice(invoice);
    }

    public Invoice getInvoiceByRef(LocalUniqueId billRef){
        return this.invoiceDao.selectInvoiceByNumber(billRef);
    }

    public List<Invoice> getInvoiceByCustomer(LocalUniqueId customerId){
        return this.invoiceDao.selectInvoiceByCustomer(customerId);
    }

    public List<Invoice> getInvoiceByDate(String date){
        return this.invoiceDao.selectInvoiceByDate(date);
    }

    public boolean updateInvoice(LocalUniqueId billRef, Invoice newInvoice){
        return this.invoiceDao.updateInvoice(billRef, newInvoice);
    }

    public void addProductIntoInvoice(LocalUniqueId billRef, LocalUniqueId productRef, int quantity){
        this.invoiceDao.addProductIntoInvoice(billRef,productRef,quantity);
    }

    public boolean updateProductQuantityOnInvoice(LocalUniqueId invoiceNumber, LocalUniqueId productRef, int newQuantity){
        return this.invoiceDao.updateProductQuantityOnInvoice(invoiceNumber,productRef,newQuantity);
    }

    public boolean removeProductFromInvoice(LocalUniqueId invoiceNumber, LocalUniqueId productRef){
        return this.invoiceDao.removeProductFromInvoice(invoiceNumber,productRef);
    }

    public void totalCost(LocalUniqueId invoiceNumber){
        float price = 0;
        Map<LocalUniqueId,Integer> productList = this.invoiceDao.selectInvoiceByNumber(invoiceNumber).getProductsList();
        Iterator<LocalUniqueId> productIterator = productList.keySet().iterator();
        while (productIterator.hasNext()){
            LocalUniqueId productRef = productIterator.next();
            Product product = this.productDao.selectProduct(productRef);
            price += product.getPrice() * productList.get(productRef);
        }
        this.invoiceDao.selectInvoiceByNumber(invoiceNumber).setPrice(price);
    }

    public void generateXml(LocalUniqueId invoiceNumber){
        try {
            File file = new File("firstXmlFileGeneration.xml");
            file.createNewFile();
            Class deliveryModeClass = getInvoiceByRef(invoiceNumber).getDeliveryMode().getImplementedObject().getClass();
            JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {Invoice.class, deliveryModeClass});
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Invoice invoice = getInvoiceByRef(invoiceNumber);
            jaxbMarshaller.marshal(invoice, file);
            jaxbMarshaller.marshal(invoice, System.out);

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePdf(LocalUniqueId invoiceNumber){

    }
}
