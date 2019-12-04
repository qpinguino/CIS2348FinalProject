import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class item {
    protected SimpleStringProperty i_code,i_name;
    protected SimpleDoubleProperty i_quantity,i_price,i_discount,i_discount_amount,i_discounted_price,i_tax,i_tax_amount,i_final_price;
    static double cart_subtotal,cart_savings,cart_rewardPts,cart_tax,cart_grandTotal;
    // subtotal, savings, reward point earned so far, tax amount and grand total

    public item(String code, String name, double qt, double price, double discount, boolean taxExempt){
        i_code = new SimpleStringProperty(code);
        i_name = new SimpleStringProperty(name);
        i_quantity = new SimpleDoubleProperty(qt);
        i_price = new SimpleDoubleProperty(price);
        i_discount=new SimpleDoubleProperty(discount);
        i_discount_amount = new SimpleDoubleProperty(-1);
        i_discounted_price= new SimpleDoubleProperty(-1);
        i_tax = new SimpleDoubleProperty(-1);
        i_tax_amount = new SimpleDoubleProperty(-1);
        i_final_price= new SimpleDoubleProperty(-1);


        if(taxExempt)
            i_tax.set(0);
        else
            i_tax.set(0.0825);

        calculations();


    }

    public String getI_code(){
        return i_code.get();
    }
    public String getI_name(){
        return i_name.get();
    }
    public double getI_quantity() {
        return i_quantity.get();
    }
    public double getI_price() {
        return i_price.get();
    }
    public double getI_discount() {
        return i_discount.get();
    }

    public double getI_discount_amount(){
        return i_discount_amount.get();
    }
    public double getI_discounted_price(){
        return i_discounted_price.get();
    }
    public double getI_tax_amount(){
        return i_tax_amount.get();
    }
    public double getI_final_price(){
        return i_final_price.get();
    }

    public double getCart_subtotal(){
        return cart_subtotal;
    }

    public void setI_code(String i_code) {
        this.i_code.set(i_code);
    }
    public void setI_name(String i_name) {
        this.i_name.set(i_name);

    }
    public void setI_quantity(double i_quantity) {
        this.i_quantity.set(i_quantity);
        calculations();
    }
    public void setI_price(double i_price) {
        this.i_price.set(i_price);
        calculations();
    }
    public void setI_discount(double i_discount) {
        this.i_discount.set(i_discount);
        calculations();
    }

    public void calculations(){
        double d_price = i_price.get();
        double d_qt = i_quantity.get();
        double d_dis = i_discount.get();
        double d_tax = i_tax.get();
            i_discount_amount.set(d_price*d_qt*d_dis/100);
            i_discounted_price.set((d_price*d_qt)-i_discount_amount.get());
            i_tax_amount.set(i_discounted_price.get()*d_tax);
            i_final_price.set(i_discounted_price.get()+i_tax_amount.get());
            cart_subtotal += i_discounted_price.get();
            cart_savings += i_discount_amount.get();
            cart_rewardPts += i_discounted_price.get() / 100;
            cart_tax += i_tax_amount.get();
            cart_grandTotal += i_final_price.get();




    }

    public static void clear_cart(){
        cart_subtotal=0;
        cart_savings=0;
        cart_tax=0;
        cart_grandTotal=0;
    }

    public void remove_item(){
        cart_subtotal -= i_discounted_price.get();
        cart_savings -= i_discount_amount.get();
        cart_rewardPts -= i_discounted_price.get() / 100;
        cart_tax -= i_tax_amount.get();
        cart_grandTotal -= i_final_price.get();
    }

    public static boolean reward_discount(){
        if(cart_rewardPts>=100)
        {
            cart_rewardPts = 0;
            cart_savings *= 1.1;
            cart_grandTotal *= .9;
            return true;
        }
        return false;
    }


}
