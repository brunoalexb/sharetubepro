package com.brunodev.sharetubepro.util;

public class FileUtils {

	
	public static String getDownloadDirectory() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Para Windows
            return System.getenv("USERPROFILE") + "\\Downloads\\";
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
            // Para macOS ou Linux
            return System.getProperty("user.home") + "/Downloads/";
        }

        return "downloads/"; // Padrão caso não seja detectado
    }

    public static void main(String[] args) {
        System.out.println("Pasta de downloads: " + getDownloadDirectory());
    }
}
