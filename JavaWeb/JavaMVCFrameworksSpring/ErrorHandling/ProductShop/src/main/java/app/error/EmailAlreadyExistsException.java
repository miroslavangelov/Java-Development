package app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Email already exists!")
public class EmailAlreadyExistsException extends RuntimeException {
    private int statusCode;

    public EmailAlreadyExistsException() {
        this.statusCode = 404;
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
