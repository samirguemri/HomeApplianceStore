package com.samir.has.api.doa;

import com.samir.has.api.object.Bill;
import com.samir.has.api.object.LocalUniqueId;

import java.util.List;

public interface IBillDao {
    void insertBill(Bill bill);
    Bill selectBillByNumber(LocalUniqueId billNumber);
    List<Bill> selectBillByCustomer(LocalUniqueId customerId);
    List<Bill> selectBillByDate(String date);
    boolean updateBill(LocalUniqueId billRef, Bill newBill);
    boolean removeBill(LocalUniqueId billRef);
    void addProductIntoBill(LocalUniqueId billRef, LocalUniqueId productRef, int quantity);
    boolean updateProductQuantityOnBill(LocalUniqueId billNumber, LocalUniqueId productRef, int newQuantity);
    boolean removeProductFromBill(LocalUniqueId billNumber, LocalUniqueId productRef);
}
