package JavaOOPBasics.Inheritance.OnlineRadioDatabase;

import JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions.InvalidSongException;
import JavaOOPBasics.Inheritance.OnlineRadioDatabase.Exceptions.InvalidSongLengthException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int songsCount = Integer.parseInt(reader.readLine());
        Playlist playlist = new Playlist();

        for (int i = 0; i < songsCount; i++) {
            try {
                String[] songData = reader.readLine().split(";");
                String[] songLength = songData[2].split(":");
                try {
                    String artistName = songData[0].trim();
                    String songName = songData[1].trim();
                    int songMinutes = Integer.parseInt(songLength[0]);
                    int songSeconds = Integer.parseInt(songLength[1]);
                    Song song = new Song(artistName, songName, songMinutes, songSeconds);
                    playlist.addSong(song);
                    System.out.println("Song added.");
                } catch (NumberFormatException e) {
                    throw new InvalidSongLengthException("Invalid song length.");
                }
            } catch (InvalidSongException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Songs added: " + playlist.getSongs().size());
        System.out.println("Playlist length: " + playlist.getPlaylistLength());
    }
}
