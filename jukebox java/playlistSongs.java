package org.example;
import java.sql.*;
import java.util.*;
import java.util.*;
public class playlistSongs {
    Scanner s = new Scanner(System.in);

    public playlistSongs() {
        Connectivity.connectivity();
    }

    public int getPlaylistId(String playlistname) {
        try {
            PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT playlist_id FROM playlist WHERE playlist_name = ?");
            ps.setString(1, playlistname);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("playlist_id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    // Method to check if the song is already in the playlist
    public boolean isSongInPlaylist(int songId, int playlistId) {
        try {
            PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT * FROM song_playlist WHERE song_id = ? AND playlist_id = ?");
            ps.setInt(1, songId);
            ps.setInt(2, playlistId);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // Returns true if the song is found in the playlist
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void addSongToPlaylist(String playlistname) {
        try {
            int playlistId = getPlaylistId(playlistname);


            if (playlistId != -1) {
                System.out.println("Enter the song ID to add to the playlist:");
                int songid = s.nextInt();
                if (!isSongInPlaylist(songid, playlistId)) {
                    PreparedStatement ps = Connectivity.connect.prepareStatement("INSERT INTO song_playlist(song_id, playlist_id) VALUES (?, ?)");
                    ps.setInt(1, songid);
                    ps.setInt(2, playlistId);
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Song added to the playlist");
                    }
                } else {
                    System.out.println("Song already exists in the playlist");
                }
            } else {
                System.out.println("Song not found in the database");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public boolean isalblumExists(String albumname) {
        try (PreparedStatement ps = Connectivity.connect.prepareStatement("Select Album FROM songs where album=?")) {
            ps.setString(1, albumname);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            //System.out.println("album not found"+e.getMessage());
        }
        return false;
    }
}








