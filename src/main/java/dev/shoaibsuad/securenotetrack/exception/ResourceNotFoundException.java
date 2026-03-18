package dev.shoaibsuad.securenotetrack.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Integer id) {
        super(resourceName + " with id " + id + " not found");
    }
}
