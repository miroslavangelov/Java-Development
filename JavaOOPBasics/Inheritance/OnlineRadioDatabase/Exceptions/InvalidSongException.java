package JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions;

public class InvalidSongException extends IllegalArgumentException {
    public InvalidSongException(String message) {
        super(message);
    }
}
