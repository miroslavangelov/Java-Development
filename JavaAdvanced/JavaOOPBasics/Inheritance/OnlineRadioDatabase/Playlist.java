package JavaOOPBasics.Inheritance.OnlineRadioDatabase;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<Song> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getPlaylistLength() {
        int totalSeconds = 0;

        for (Song song: songs) {
            totalSeconds += song.getSongMinutes()*60;
            totalSeconds += song.getSongSeconds();
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%dh %dm %ds", hours, minutes, seconds);

    }
}
