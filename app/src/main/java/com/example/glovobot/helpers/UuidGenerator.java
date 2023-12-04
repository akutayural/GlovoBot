package com.example.glovobot.helpers;

import androidx.annotation.NonNull;

import java.util.UUID;

public class UuidGenerator {
    @NonNull
    public static String generateUuidV4() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


}
