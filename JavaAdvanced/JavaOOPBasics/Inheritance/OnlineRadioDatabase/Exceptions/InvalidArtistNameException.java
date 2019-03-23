package JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions;

public class InvalidArtistNameException extends InvalidSongException {
    public InvalidArtistNameException(String message) {
        super(message);
    }
}
