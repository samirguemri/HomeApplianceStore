package com.samir.has.api.doa.inmemoriedb;

import com.samir.has.api.doa.IBillDao;
import com.samir.has.api.doa.IDatabaseConnection;
import com.samir.has.api.object.Bill;
import com.samir.has.api.object.LocalUniqueId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeBillDao")
public class FakeBillDaoImpl implements IBillDao, IDatabaseConnection {

    private final List<Bill> billsTable;

    public FakeBillDaoImpl() {
        billsTable = new ArrayList<>();
        connect();
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void insertBill(Bill bill) {
        this.billsTable.add(bill);
    }

    @Override
    public Bill selectBillByNumber(LocalUniqueId billNumber) {
        for (Bill bill : this.billsTable) {
            if (bill.getBillNumber().equals(billNumber))
                return bill;
        }
        return null;
    }

    @Override
    public List<Bill> selectBillByCustomer(LocalUniqueId customerId) {
        List<Bill> billList = new ArrayList<>();
        for (Bill bill : this.billsTable) {
            if (bill.getCustomerId().equals(customerId))
                billList.add(bill);
        }
        return billList;
    }

    @Override
    public List<Bill> selectBillByDate(String date) {
        List<Bill> billList = new ArrayList<>();
        for (Bill bill : this.billsTable) {
            if (bill.getIssueDate().toString().equals(date))
                billList.add(bill);
        }
        return billList;
    }

    @Override
    public boolean updateBill(LocalUniqueId billRef, Bill newBill) {
        if (removeBill(billRef)){
            this.billsTable.add(newBill);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeBill(LocalUniqueId billRef){
        for (Bill bill : this.billsTable) {
            if (bill.getBillNumber().equals(billRef))
                return this.billsTable.remove(bill);
        }
        return false;
    }

    @Override
    public void addProductIntoBill(LocalUniqueId billNumber, LocalUniqueId productRef, int quantity) {
        updateProductQuantityOnBill(billNumber,productRef,quantity);
    }

    @Override
    public boolean updateProductQuantityOnBill(LocalUniqueId billNumber, LocalUniqueId productRef, int newQuantity) {
        Bill bill = this.selectBillByNumber(billNumber);
        bill.getProductsList().put(productRef,newQuantity);
        return this.updateBill(billNumber,bill);
    }

    @Override
    public boolean removeProductFromBill(LocalUniqueId billNumber, LocalUniqueId productRef) {
        Bill bill = this.selectBillByNumber(billNumber);
        bill.getProductsList().remove(productRef);
        return this.updateBill(billNumber,bill);
    }
}
