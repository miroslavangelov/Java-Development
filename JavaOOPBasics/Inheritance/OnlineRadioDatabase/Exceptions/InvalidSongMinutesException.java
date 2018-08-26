package JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions;

public class InvalidSongMinutesException extends InvalidSongLengthException {
    public InvalidSongMinutesException(String message) {
        super(message);
    }
}
