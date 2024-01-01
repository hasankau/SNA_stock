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
public class StockItem {
    private String exp_date;
    private String qty;
    private String cash_price;
    private String credit_price;
    private String bar_code;
    private String discount_margin;

    public boolean equals(StockItem stockItem1, StockItem stockItem2){
        String string1 = stockItem1.bar_code+stockItem1.cash_price+stockItem1.credit_price+stockItem1.exp_date;
        String string2 = stockItem2.bar_code+stockItem2.cash_price+stockItem2.credit_price+stockItem2.exp_date;
        return string1.equals(string2);
    }
    
    /**
     * @return the exp_date
     */
    public String getExp_date() {
        return exp_date;
    }

    /**
     * @param exp_date the exp_date to set
     */
    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
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

    /**
     * @return the cash_price
     */
    public String getCash_price() {
        return cash_price;
    }

    /**
     * @param cash_price the cash_price to set
     */
    public void setCash_price(String cash_price) {
        this.cash_price = cash_price;
    }

    /**
     * @return the credit_price
     */
    public String getCredit_price() {
        return credit_price;
    }

    /**
     * @param credit_price the credit_price to set
     */
    public void setCredit_price(String credit_price) {
        this.credit_price = credit_price;
    }

    /**
     * @return the Item_item_code
     */
    public String getItem_item_code() {
        return bar_code;
    }

    /**
     * @param Item_item_code the Item_item_code to set
     */
    public void setItem_item_code(String Item_item_code) {
        this.bar_code = Item_item_code;
    }

    /**
     * @return the discount_margin
     */
    public String getDiscount_margin() {
        return discount_margin;
    }

    /**
     * @param discount_margin the discount_margin to set
     */
    public void setDiscount_margin(String discount_margin) {
        this.discount_margin = discount_margin;
    }
}
