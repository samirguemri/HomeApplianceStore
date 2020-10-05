package com.samir.has.api.service;

import com.samir.has.api.doa.IBillDao;
import com.samir.has.api.doa.IProductDao;
import com.samir.has.api.object.Bill;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BillService {

    private final IBillDao billDao;
    private final IProductDao productDao;

    @Autowired
    public BillService(@Qualifier("fakeProductDao") IProductDao productDao, @Qualifier("fakeBillDao") IBillDao billDao) {
        this.productDao = productDao;
        this.billDao = billDao;
    }

    void addBill(Bill bill){
        this.billDao.insertBill(bill);
    }

    Bill getBillByRef(LocalUniqueId billRef){
        return this.billDao.selectBillByNumber(billRef);
    }

    List<Bill> getBillByCustomer(LocalUniqueId customerId){
        return this.billDao.selectBillByCustomer(customerId);
    }

    List<Bill> getBillByDate(String date){
        return this.billDao.selectBillByDate(date);
    }

    boolean updateBill(LocalUniqueId billRef, Bill newBill){
        return this.billDao.updateBill(billRef,newBill);
    }

    void addProductIntoBill(LocalUniqueId billRef, LocalUniqueId productRef, int quantity){
        this.billDao.addProductIntoBill(billRef,productRef,quantity);
    }

    boolean updateProductQuantityOnBill(LocalUniqueId billNumber, LocalUniqueId productRef, int newQuantity){
        return this.billDao.updateProductQuantityOnBill(billNumber,productRef,newQuantity);
    }

    boolean removeProductFromBill(LocalUniqueId billNumber, LocalUniqueId productRef){
        return this.billDao.removeProductFromBill(billNumber,productRef);
    }

    void calculateTotal(LocalUniqueId billNumber){
        float price = 0;
        Map<LocalUniqueId,Integer> productList = this.billDao.selectBillByNumber(billNumber).getProductsList();
        Iterator<LocalUniqueId> productIterator = productList.keySet().iterator();
        while (productIterator.hasNext()){
            LocalUniqueId productRef = productIterator.next();
            Product product = this.productDao.selectProduct(productRef);
            price += product.getPrice() * productList.get(productRef);
        }
        this.billDao.selectBillByNumber(billNumber).setPrice(price);
    }

    void generatePdf(LocalUniqueId billNumber){

    }
}
