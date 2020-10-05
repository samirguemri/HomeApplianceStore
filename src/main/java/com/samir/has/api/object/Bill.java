package com.samir.has.api.object;

import com.samir.has.api.object.livraison.Delivery;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * La facture produite après l'achat de un ou plusieurs Produit
 */
public class Bill {

    private LocalUniqueId billNumber;
    private SimpleDateFormat issueDate;
    private LocalUniqueId customerId;
    private Map<LocalUniqueId, Integer> productsList;
    private Delivery deliveryMode;
    private float price;
    private boolean paid = false;

    public Bill(LocalUniqueId customerId, Delivery deliveryMode) {
        this.billNumber = LocalUniqueId.randomUniqueId();
        this.customerId = customerId;
        this.issueDate = new SimpleDateFormat("dd/MM/yyyy");
        this.productsList = new HashMap<LocalUniqueId, Integer>();
        this.deliveryMode = deliveryMode;
        this.price = 0;
        this.paid = false;
    }

    public com.samir.has.api.object.LocalUniqueId getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(LocalUniqueId billNumber) {
        this.billNumber = billNumber;
    }

    public SimpleDateFormat getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(SimpleDateFormat issueDate) {
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

    /*
    public float calculerTotal() {

    }

    public void ajouterProduit(Product produitaAjouter, int quantite){
            if (productsList.containsKey(produitaAjouter))
                productsList.put(produitaAjouter, productsList.get(produitaAjouter)+quantite);
            else productsList.put(produitaAjouter,quantite);
        produitaAjouter.addStock(-quantite);
    }

    public void modifierQuantiteProduit(Product produitAModifier, int nouvelleQuantite){
        productsList.put(produitAModifier,nouvelleQuantite);
    }

    public void supprimerProduit(Product unProduit){
        productsList.remove(unProduit);
    }


    public void imprimer(Writer writer) throws NoProductException {
        if(lesProduits.size() == 0)
            throw new NoProductException("Pas de Produits ajoutes .. Impossible de generer la Facture !!");
        calculerTotal();
        writer.start();
        writer.writeLine("HomeShop compagnie\n");
        writer.writeLine("1 Place Charles de Gaulle, 75008 Paris\n");
        writer.writeLine("\n");
        writer.writeLine("Facture à l'attention de :\n");
        writer.writeLine(acheteur.getName()+"\n");
        writer.writeLine(acheteur.getAdresse()+"\n");
        writer.writeLine("\n");
        writer.writeLine("Mode de livraison : "+livraison.getMode()+"\n");
        writer.writeLine("\n");
        writer.writeLine("Produits : \n");
        writer.writeLine("-----------------------------------------------------\n");
        for (Product unProduit: lesProduits.keySet()) {
            writer.writeLine(unProduit.getNom() + " - " + unProduit.getPrix() + " - " +
                    lesProduits.get(unProduit) + " unité(s)\n");
            writer.writeLine(unProduit.getDescriptif() + "\n");
            writer.writeLine("\n");
        }
        writer.writeLine("Livraison : "+livraison.prix()+"\n");
        writer.writeLine("-----------------------------------------------------\n");
        writer.writeLine("Total : "+prix);
        writer.stop();
    }
    */
}
