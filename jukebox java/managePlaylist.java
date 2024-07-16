package org.example;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class managePlaylist {
    public managePlaylist() {
        Connectivity.connectivity();
    }

    public boolean playlistEXists(String playlistname) {
        try (PreparedStatement ps = Connectivity.connect.prepareStatement("Select playlist_name from playlist where playlist_name=?")) {
            ps.setString(1, playlistname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean createplaylist(String playlistname, String username) throws PlaylistAlreadyExistsException {

        if (!playlistEXists(playlistname)) {
            try (PreparedStatement psinsert = Connectivity.connect.prepareStatement("INSERT INTO playlist (playlist_name, user_id)" +
                    "SELECT ?, user_id FROM users WHERE username = ?")) {
                psinsert.setString(1, playlistname);
                psinsert.setString(2, username);
                int rowsAffected = psinsert.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Playlist created");
                    return true;
                } else {
                    System.out.println("Playlist creation failed");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Playlist already exists for this user.");
            throw new PlaylistAlreadyExistsException("Playlist already exists for user: " + username);
        }
        return false;
    }


    public void viewPlaylist(String username) {
        try (PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT playlist_name FROM playlist WHERE user_id = (SELECT user_id FROM users WHERE username = ?)")) {
            //    "SELECT user_id FROM users WHERE username = ?"
            ps.setString(1, username);
            ResultSet r = ps.executeQuery();
            System.out.printf("Your Playlist");
            if (r.next()) {
                System.out.println(r.getString("playlist_name"));
            } else {
                System.out.println("playlist not available");
            }

        } catch (SQLException e) {
            System.out.println(e);

        }

    }


    public void showSongsInPlaylist(String playlistName) {
        try (PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT sp.song_id, s.album, s.Artist_name, s.genre, s.releasedate, s.duration " +
                "                    FROM song_playlist sp" +
                "                    JOIN songs s ON sp.song_id = s.songid " +
                "WHERE sp.playlist_id = (SELECT playlist_id FROM playlist WHERE playlist_name = ?)")) {


            ps.setString(1, playlistName);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                System.out.println("Songs in " + playlistName + " playlist:");
                while (rs.next()) {
                    int songId = rs.getInt("song_id");

                    String album = rs.getString("album");
                    String artist = rs.getString("artist_name");
                    String genre = rs.getString("genre");
                    int releaseDate = rs.getInt("releasedate");
                    int duration = rs.getInt("duration");
                    // Fetch and display song details using songId
                    System.out.println("Song ID: " + songId + "\t" + "album " + album + "\t" + "artist" + artist + "\t" + "genre" + genre + "\t" + "releaseDate" + releaseDate + "\t");

                }
            } else {
                System.out.println("No songs found in the playlist ");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void deleteplaylist(String playlistName) {
        try (PreparedStatement ps = Connectivity.connect.prepareStatement("Delete FROM playlist WHERE playlist_name =  ?")) {
            ps.setString(1, playlistName);


            int rowseffected = ps.executeUpdate();
            if (rowseffected > 0) {
                System.out.println("Playlist deleted successfully!");
            } else {
                System.out.println("Failed to delete the playlist.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }


}