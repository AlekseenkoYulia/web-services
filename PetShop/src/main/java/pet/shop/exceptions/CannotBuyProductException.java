package pet.shop.exceptions;

public class CannotBuyProductException extends Exception {

    public CannotBuyProductException(String message){
        super(message);
    }
}
