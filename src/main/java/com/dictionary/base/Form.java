package com.dictionary.base;

import com.dictionary.database.DatabaseController;

public interface Form {
    default void reset() {
        System.out.println("reset");
    }

}