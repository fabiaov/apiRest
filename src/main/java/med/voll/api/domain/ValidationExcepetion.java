package med.voll.api.domain;

public class ValidationExcepetion extends RuntimeException {
    public ValidationExcepetion(String msg) {
        super(msg);
    }
}
