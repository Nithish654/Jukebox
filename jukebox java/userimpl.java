package org.example;

import java.util.Scanner;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
public class userimpl {


    user l = new user();
    managePlaylist m = new managePlaylist();
    playlistSongs p = new playlistSongs();
    songsimpl songi = new songsimpl();
    Audioplayer au = new Audioplayer();
    Scanner s = new Scanner(System.in);

    public void catalog() {
        System.out.println("Welcome");
        System.out.println("1. Existing User\n2. New User");
        System.out.println("Enter your choice");
        try {

            int choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter your username:");
                    String username = s.nextLine();
                    if (!l.userExists(username)) {
                        System.out.println("User not registered. Do you want to register? (yes/no)");
                        String registerOption = s.nextLine();
                        if (registerOption.equalsIgnoreCase("yes")) {
                            System.out.println("Enter a new username:");
                            String newUsername = s.nextLine();
                            System.out.println("Enter a password:");
                            String newPassword = s.nextLine();
                            l.registerNewUser(newUsername, newPassword);
                        } else
                            break;
                    }
                    pl(username);
                    break;

                case 2:
                    System.out.println("Enter a new username:");
                    String newUsername = s.nextLine();
                    if (l.userExists(newUsername)) {
                        System.out.println("User already exists");
                        System.out.println("Sign in with a different name. Do you want to continue? (yes/no)");
                        String option = s.next();
                        if (option.equalsIgnoreCase("yes")) {
                            System.out.println("Enter a new username:");
                            String newusername = s.nextLine();
                            if (l.userExists(newUsername)) {
                                System.out.println("This username already exists. Aborting registration.");
                            } else {
                                System.out.println("Enter a password:");
                                String newPassword = s.nextLine();
                                l.registerNewUser(newusername, newPassword);
                            }
                        }
                    } else {
                        System.out.println("Enter a password:");
                        String newPassword = s.nextLine();
                        l.registerNewUser(newUsername, newPassword);
                              pl(newUsername);
                    }
                    break;
            }

        } catch (Exception e) {
            System.out.println("Enter an integer value" + e);
        }
    }

    public void pl(String username) {

         while (true) {
        System.out.println("1. play songs\t\t2. Search song by name\t\t3. Search by artist name\t\t4. Manage Playlist\t\t5. Exit");
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                songi.showsongs();
                System.out.println("Enter the song id to play");
                int songId = s.nextInt();
                au.Audiofun(songId);
                au.play();
                boolean mainmenu = false;
                while (!mainmenu) {
                    System.out.println("\t\t1. Stop\n\t\t2. Pause\n\t\t3. Resume\n\t\t4. Restart\n\t\t5. Back to main menu");

                    int playbackChoice = s.nextInt();
                    switch (playbackChoice) {
                        case 1:
                            try {
                                au.stop();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                au.pause();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                au.resumeAudio();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            try {
                                au.restart();
                            }
                            catch(Exception re){
                                re.printStackTrace();
                            }
                            break;
                        case 5:
                            // Back to the main menu
                            try {
                                au.stop();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            mainmenu = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");

                    }

                }
                   break;

            case 2:
                System.out.println("Enter the name of the song");
                String albumname = s.next();
                if(!p.isalblumExists(albumname)){
                    System.out.println("album not found");
                }
                else{
                    songi.searchbyAlbum(albumname);
                    System.out.println("enter id");
                    int song=s.nextInt();
                    au.Audiofun(song);
                    au.play();
                    boolean serachname=false;

                    while (!serachname){
                        System.out.println("\t\t1. Stop\n\t\t2. Pause\n\t\t3. Resume\n\t\t4.Exit");
                        int achoice=s.nextInt();
                        switch (achoice){
                            case 1:
                                try {
                                    au.stop();
                                }
                                catch (Exception e){
                                    System.out.println(e);
                                }
                                break;
                            case 2:
                                try {
                                    au.pause();
                                }
                                catch (Exception e){
                                    System.out.println(e);
                                }
                                break;
                            case 3:
                                try {
                                    au.resumeAudio();
                                }
                                catch (Exception e){
                                    System.out.println(e);
                                }
                                break;
                            case 4:
                                serachname=true;
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }
                }

                break;

            case 3:
                System.out.println("Enter the name of the artist");
                String name = s.next();
                songi.searchByartistname(name);
                break;

            case 4:
                managePlaylistSubMenu(username);
                break;

            case 5:
                System.exit(0); // This will terminate the program

                break;

            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

}


        public void managePlaylistSubMenu(String username) {
            boolean exitSubMenu = false;
            while (!exitSubMenu) {
                System.out.println("Playlist Management\n\t\t\t1.create playlist\n\t\t\t2. Add song to playlist\n\t\t\t3. View playlist songs\n\t\t\t4. List all playlists\n\t\t\t5. Delete playlist\n\t\t\t6. play songs in playlist\n\t\t\t 7.Back to main menu");
                int subChoice = s.nextInt();
                switch (subChoice) {
                    case 1:
                        System.out.println("Create palylist");
                        System.out.println("enter the name");
                        String playname=s.next();
                        try {
                            if (!m.playlistEXists(playname)) {
                                m.createplaylist(playname, username);
                            } else {
                                System.out.println("playlist already exists by this name");
                            }
                        }catch (PlaylistAlreadyExistsException e){
                            System.out.println(e.getMessage());
                        }
                         break;
                    case 2:
                        // Add songs to playlist
                      m.viewPlaylist(username);
                        songi.showsongs();
                        System.out.println("Enter the playlist name");
                        String playlistname = s.next();

                        p.addSongToPlaylist(playlistname);
                        break;

                    case 3:
                        // View playlist songs
                        System.out.println("Enter the playlist name");
                        m.viewPlaylist(username);
                        String viewPlaylistName = s.next();
                        if(m.playlistEXists(viewPlaylistName)){
                            m.showSongsInPlaylist(viewPlaylistName);
                        }
                        //m.viewPlaylist(viewPlaylistName);
                        break;

                    case 4:
                        // List all playlists
                        m.viewPlaylist(username);
                        break;

                    case 5:
                        // Delete playlist
                        m.viewPlaylist(username);
                        System.out.println("Enter the playlist name to delete");
                        String nameToBeDeleted = s.next();
                        if (m.playlistEXists(nameToBeDeleted)) {
                            m.deleteplaylist(nameToBeDeleted);
                        } else {
                            System.out.println("Playlist not found");
                        }
                        break;
                    case 6:
                        m.viewPlaylist(username);
                        System.out.println("Enter the playlist name to play songs:");
                        String playlistName = s.next();
                        if (m.playlistEXists(playlistName)) {
                            m.showSongsInPlaylist(playlistName);
                            System.out.println("Enter the song id to play");
                            int songid = s.nextInt();
                            au.Audiofun(songid);
                            au.play();
                            boolean playmenu = false;
                            while (!playmenu) {
                                System.out.println("1. Stop\n2. Pause\n3. Resume\n4. play\n5. Back to main menu");
                                int playbackChoice = s.nextInt();
                                switch (playbackChoice) {
                                    case 1:
                                        try {
                                            au.stop();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 2:
                                        try {
                                            au.pause();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 3:
                                        try {
                                            au.resumeAudio();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 4:
                                        try {
                                            au.restart();
                                        } catch(Exception e){
                                            e.printStackTrace();
                                        }
                                        // You may need to implement a method in your Audioplayer class to handle this
                                        break;
                                    case 5:
                                        playmenu = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Try again.");
                                }
                            }
                        }
                        break;
                    case 7 :
                        exitSubMenu = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
            }



