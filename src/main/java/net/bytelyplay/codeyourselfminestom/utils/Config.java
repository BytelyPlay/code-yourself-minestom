package net.bytelyplay.codeyourselfminestom.utils;

import net.minestom.server.coordinate.Pos;

public class Config {
    private static final Config INSTANCE =
            new Config();

    private Pos spawnPositionInHub = new Pos(0, 5, 0);

    private String listenIp = "127.0.0.1";
    private char listenPort = 25565;

    /**
     * Path to the world for the hub.
     * It has to contain a dimensions folder then a folder called
     * minecraft then overworld then region (everything is lowercase)
     * which contains all the region files (.mcas)
     */
    private String pathToHubWorld = "./config/hub_world";

    public Pos getSpawnPositionInHub() {
        return spawnPositionInHub;
    }

    public String getListenIp() {
        return listenIp;
    }
    public char getListenPort() {
        return listenPort;
    }

    public String getPathToHubWorld() {
        return pathToHubWorld;
    }

    public static Config getInstance() {
        return INSTANCE;
    }
    private Config() {}
}
