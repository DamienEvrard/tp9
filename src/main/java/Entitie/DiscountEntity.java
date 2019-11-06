/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitie;

/**
 *
 * @author damie
 */
public class DiscountEntity {
    private String discount_code;
    private float rate;

    public DiscountEntity(String DC, float pRate){
        this.discount_code=DC;
        this.rate=pRate;            
    }

    public String getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(String discount_code) {
        this.discount_code = discount_code;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
        
       
        
        
}
