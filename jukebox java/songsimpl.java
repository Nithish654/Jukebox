package org.example;
import java.sql.*;
import org.example.Connectivity;
public class songsimpl {
    public songsimpl() {
        Connectivity.connectivity();
    }

    public void showsongs() {
        try {
            Statement st = Connectivity.connect.createStatement();
            ResultSet rs = st.executeQuery("select * from songs");
            // Print column headers
            System.out.printf("%-8s %-20s %-20s %-10s %-8s %-8s%n", "Song ID", "Album name", "Artist name", "Genre", "ReleaseDate", "Duration");
            // Print a separator line
            System.out.println("----------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("Songid");
                String albumName = rs.getString("Album");
                String artistName = rs.getString("artist_name");
                String genre = rs.getString("genre");
                int releaseDate = rs.getInt("releasedate");
                int duration = rs.getInt("duration");

                // Print song details
                System.out.printf("%-8d %-20s %-20s %-10s %-8d %-8d%n", id, albumName, artistName, genre, releaseDate, duration);

            }} catch(
                    Exception ex){
                System.out.println("Unable to read" + ex);
            }
        }



    public void searchbyAlbum(String album) {
        try {

            PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT * FROM songs WHERE Album = ?");

            ps.setString(1, album);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Albumname = rs.getString("Album");
                String artistname = rs.getString("artist_name");
                int id = rs.getInt("Songid");
                String genre=rs.getString("genre");
                 int releasedate=rs.getInt("releasedate");
                 int duration=rs.getInt("duration");

                System.out.println("Song ID: " + id + " | Album: " + Albumname + " | Artist: " + artistname + " | Genre: " + genre + " | Release Date: " + releasedate + " | Duration: " + duration);
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public void searchByartistname(String artist) {
        try {
            PreparedStatement ps = Connectivity.connect.prepareStatement("select songid from songs where artist_name=?");
            ps.setString(1, artist);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Albumname = rs.getString("Album");
                String artistname = rs.getString("artist_name");
                int id = rs.getInt("Song_id");
                String genre=rs.getString("genre");
                int releasedate=rs.getInt("releasedate");
                int duration=rs.getInt("duration");

                System.out.println("Song ID: " + id + " | Album: " + Albumname + " | Artist: " + artistname + " | Genre: " + genre + " | Release Date: " + releasedate + " | Duration: " + duration);

            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }
    public void searchByFirstLetter(String letter) {
        try {
            PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT * FROM songs WHERE Album LIKE ?");
            ps.setString(1, letter + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String Albumname = rs.getString("Album");
                String artistname = rs.getString("artist_name");
                int id = rs.getInt("Song_id");
                String genre=rs.getString("genre");
                int releasedate=rs.getInt("releasedate");
                int duration=rs.getInt("duration");
                songs s1 = new songs(id, Albumname, artistname,genre,releasedate,duration);

                System.out.println(s1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

