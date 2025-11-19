package org.example.bank.common;

public class Exceptions {
    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String msg) { super(msg); }
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String msg) { super(msg); }
    }
}

