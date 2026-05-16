package com.usuarios.strategy;

public interface PasswordStrategy {
    String encode(String password);
}