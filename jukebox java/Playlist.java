package org.example;
import java.util.*;
import org.example.Connectivity;
public class Playlist {
    private int playlistid;
    private int userid;
    private String playlistname;

    public Playlist() {
    }

    public Playlist(int playlistid, int userid, String playlistname) {
        this.playlistid = playlistid;
        this.userid = userid;
        this.playlistname = playlistname;
    }

    public int getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(int playlistid) {
        this.playlistid = playlistid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public void setPlaylistname(String playlistname) {
        this.playlistname = playlistname;
    }
}
