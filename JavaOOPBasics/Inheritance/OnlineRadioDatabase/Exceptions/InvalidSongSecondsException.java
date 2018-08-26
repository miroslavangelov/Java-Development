package JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions;

public class InvalidSongSecondsException extends InvalidSongLengthException {
    public InvalidSongSecondsException(String message) {
        super(message);
    }
}
