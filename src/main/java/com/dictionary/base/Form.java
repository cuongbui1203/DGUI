package com.dictionary.base;

import com.dictionary.database.DatabaseController;

public interface Form {
    DatabaseController db = null;
    default void reset() {
        System.out.println("reset");
    }

}