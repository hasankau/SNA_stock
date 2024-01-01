/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

/**
 *
 * @author HASANKA
 */
public class Item {
    private int id; 
    private String item_code;
    private String description;
    private String manufacturer;
    private String selling_price;
    private String buying_price;
    private String qty;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the item_code
     */
    public String getItem_code() {
        return item_code;
    }

    /**
     * @param item_code the item_code to set
     */
    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the selling_price
     */
    public String getSelling_price() {
        return selling_price;
    }

    /**
     * @param selling_price the selling_price to set
     */
    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    /**
     * @return the buying_price
     */
    public String getBuying_price() {
        return buying_price;
    }

    /**
     * @param buying_price the buying_price to set
     */
    public void setBuying_price(String buying_price) {
        this.buying_price = buying_price;
    }

    /**
     * @return the qty
     */
    public String getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(String qty) {
        this.qty = qty;
    }
}
