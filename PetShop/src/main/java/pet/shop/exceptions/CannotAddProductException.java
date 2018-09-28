package pet.shop.exceptions;

public class CannotAddProductException extends Exception {

    public CannotAddProductException(String message){
        super(message);
    }
}
