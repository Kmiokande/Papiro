package com.kmiokande.papiro.utility;

import android.provider.BaseColumns;

public class Constants {
    private Constants() {}

    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
}
