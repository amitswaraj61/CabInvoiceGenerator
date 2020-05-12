package cabInvoice;
public class CabInvoiceException extends Exception {
    public ExceptionType type;
    public String message;
    public enum ExceptionType {
        USER_CANT_BE_NULL;
    }
public CabInvoiceException(String message, ExceptionType type){
        super(message);
        this.type=type;
        this.message=message;
    }
}
