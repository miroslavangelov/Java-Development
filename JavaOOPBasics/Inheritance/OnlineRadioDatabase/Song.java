package JavaOOPBasics.Inheritance.OnlineRadioDatabase;

import JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions.InvalidArtistNameException;
import JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions.InvalidSongMinutesException;
import JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions.InvalidSongNameException;
import JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions.InvalidSongSecondsException;

public class Song {
    private String artistName;
    private String songName;
    private int songMinutes;
    private int songSeconds;

    public Song(String artistName, String songName, int songMinutes, int songSeconds) {
        this.setArtistName(artistName);
        this.setSongName(songName);
        this.setSongMinutes(songMinutes);
        this.setSongSeconds(songSeconds);
    }

    public int getSongMinutes() {
        return songMinutes;
    }

    public int getSongSeconds() {
        return songSeconds;
    }

    private void setArtistName(String artistName) {
        if (artistName.length() < 3 || artistName.length() > 20) {
            throw new InvalidArtistNameException("Artist name should be between 3 and 20 symbols.");
        }
        this.artistName = artistName;
    }

    private void setSongName(String songName) {
        if (songName.length() < 3 || songName.length() > 30) {
            throw new InvalidSongNameException("Song name should be between 3 and 30 symbols.");
        }
        this.songName = songName;
    }

    private void setSongMinutes(int minutes) {
        if (minutes < 0 || minutes > 14) {
            throw new InvalidSongMinutesException("Song minutes should be between 0 and 14.");
        }
        this.songMinutes = minutes;
    }

    private void setSongSeconds(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new InvalidSongSecondsException("Song seconds should be between 0 and 59.");
        }
        this.songSeconds = seconds;
    }
}
