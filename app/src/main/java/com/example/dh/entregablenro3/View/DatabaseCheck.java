package com.example.dh.entregablenro3.View;

import com.google.firebase.database.FirebaseDatabase;

public class DatabaseCheck {

    private static FirebaseDatabase mDatabase;

        public static FirebaseDatabase getDatabase() {
            if (mDatabase == null) {
                mDatabase = FirebaseDatabase.getInstance();
                mDatabase.setPersistenceEnabled(true);
            }
            return mDatabase;
        }

}
