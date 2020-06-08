package com.cralos.mydaggerapp.ui.auth;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.annotations.Nullable;

public class AuthResource<T> {

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public AuthResource(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> AuthResource<T> authenticated(@Nullable T data) {
        return new AuthResource<>(AuthStatus.AUTHENTICATE, data, null);
    }

    public static <T> AuthResource<T> error(@Nullable String message, @Nullable T data) {
        return new AuthResource<>(AuthStatus.ERROR, data, message);
    }

    public static <T> AuthResource<T> loading(@Nullable T data) {
        return new AuthResource<>(AuthStatus.LOADING, data, null);
    }

    public static <T> AuthResource<T> logout(@Nullable T data) {
        return new AuthResource<>(AuthStatus.NOT_AUTHENTICATE, null, null);
    }

    public enum AuthStatus {AUTHENTICATE, ERROR, LOADING, NOT_AUTHENTICATE}

}
