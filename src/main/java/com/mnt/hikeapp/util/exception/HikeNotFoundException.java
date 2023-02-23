package com.mnt.hikeapp.util.exception;

public class HikeNotFoundException extends Exception {
    public HikeNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
