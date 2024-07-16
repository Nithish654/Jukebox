package org.example;

public class songs {
    private int Song_id;
    private String Album;
    private String artist_name;
    private String genre;
    private int duration;
private int releasedate;
    public songs() {
    }

    public songs(int Song_id,String album, String artist_name,String genre,int releasedate,int duration) {
       this.Song_id=Song_id;
        this.Album = album;
        this.artist_name = artist_name;
         this.genre=genre;

        this.releasedate=releasedate;
        this.duration=duration;
    }

    public int getReleasedate() {
       return releasedate;
    }

    public int getDuration() {
       return duration;
    }

    public void setDuration(int duration) {
       // this.duration = duration;
   }

    public void setReleasedate(int releasedate) {
        this.releasedate = releasedate;
    }

    public String getAlbum() {
        return Album;
    }

    public int getSong_id() {
        return Song_id;
    }

    public void setSong_id(int song_id) {
        Song_id = song_id;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getArtist() {
        return artist_name;
    }

    public void setArtist(String artist) {
        this.artist_name = artist;
    }

    public String getGenre() {
        return genre;
   }

   public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "songs{" +
                "Song_id=" + Song_id +
                ", Album='" + Album + '\'' +
                ", artist_name='" + artist_name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
               ", releasedate=" + releasedate +
                '}';
    }
}
