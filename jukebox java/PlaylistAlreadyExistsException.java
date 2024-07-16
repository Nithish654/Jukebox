package org.example;

public class PlaylistAlreadyExistsException extends Exception{
    public PlaylistAlreadyExistsException(String message) {
        super(message);
    }
}
