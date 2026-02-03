package org.example.examenjpamongodbexample.domain.error;

public class DatabaseError extends RuntimeException{
    public DatabaseError(String message){super(message);}
}
