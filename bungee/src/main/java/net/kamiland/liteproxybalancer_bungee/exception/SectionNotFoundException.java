package net.kamiland.liteproxybalancer_bungee.exception;

public class SectionNotFoundException extends RuntimeException {

    public SectionNotFoundException(String section) {
        super("Section not found: " + section);
    }

}
