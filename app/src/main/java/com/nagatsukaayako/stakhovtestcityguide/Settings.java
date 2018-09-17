package com.nagatsukaayako.stakhovtestcityguide;

class Settings {
    private static Settings instance;

    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public boolean isBackground = true;
    public long backgroundTime = 60000;
    public boolean isNotificate = true;
}
