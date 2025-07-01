package net.kamiland.liteproxybalancer_bungee.exception;

public class SectionAlreadyRegisteredException extends RuntimeException {

    public SectionAlreadyRegisteredException(String section) {
        super("Section already registered: " + section);
    }

}
