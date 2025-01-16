package com.brunodev.sharetubepro.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class DownloadController {


    @PostMapping("/download")
    public ResponseEntity<?> downloadVideo(
            @RequestParam String videoUrl,
            @RequestParam(required = false, defaultValue = "video") String type,
            @RequestParam(required = false) String quality
    ) {
        String format;
        if ("audio".equalsIgnoreCase(type)) {
            format = "bestaudio";
        } else if ("video".equalsIgnoreCase(type)) {
        	//format = "best";
            format = quality != null && !quality.isEmpty() ? "bestvideo[height=" + quality + "]+bestaudio" : "bestvideo+bestaudio";
        } else {
            return ResponseEntity.badRequest().body("Tipo inválido. Use 'video' ou 'audio'.");
        }
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);
        
        String outputPath = getDownloadDirectory() + formattedDateTime + ".%(ext)s";
        // Define o caminho de saída
        //String outputPath = "downloads/%(title)s.%(ext)s";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("yt-dlp", "-f", format, "-o", outputPath, videoUrl);
            processBuilder.redirectErrorStream(true); 
            Process process = processBuilder.start();

         
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
               
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao processar o vídeo: " + output.toString());
            }

            
            return ResponseEntity.ok("Download concluído com sucesso!");

        } catch (Exception e) {
        	
        	 e.printStackTrace();
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado: " + e.getMessage());
        }
    }

	private String getDownloadDirectory() {
		 String os = System.getProperty("os.name").toLowerCase();

	        if (os.contains("win")) {
	            // Para Windows
	            return System.getenv("USERPROFILE") + "\\Downloads\\";
	        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
	            // Para macOS ou Linux
	            return System.getProperty("user.home") + "/Downloads/";
	        }

	        return "downloads/"; 
	    }
	}

