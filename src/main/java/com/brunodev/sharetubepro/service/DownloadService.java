package com.brunodev.sharetubepro.service;

import java.io.File;
import java.nio.file.Files;

import org.springframework.stereotype.Service;

@Service
public class DownloadService {

	private static final String YTDLP_PATH = "yt-dlp"; 

	 public byte[] downloadVideo(String videoUrl, String type, String quality) throws Exception {
	        // Define o formato com base nas escolhas do usuário
	        String format;
	        if ("audio".equalsIgnoreCase(type)) {
	            format = "bestaudio";
	        } else {
	            format = "bestvideo+bestaudio";
	        }

	        // Adiciona a qualidade, se especificada
	        if (quality != null && !quality.isEmpty()) {
	            format += "[height=" + quality + "]"; //resoluçao
	        }

	        // Configura o comando do yt-dlp
	        ProcessBuilder processBuilder = new ProcessBuilder(
	                YTDLP_PATH,
	                "--format", format,
	                "--output", "downloads/%(title)s.%(ext)s",
	                videoUrl
	        );

	        processBuilder.redirectErrorStream(true);
	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();

	        if (exitCode != 0) {
	            throw new Exception("Erro ao baixar o vídeo. Código de erro: " + exitCode);
	        }

	        // Procura o arquivo baixado
	        File downloadDir = new File("downloads");
	        File[] files = downloadDir.listFiles();
	        if (files == null || files.length == 0) {
	            throw new Exception("Nenhum arquivo encontrado após o download.");
	        }

	        File downloadedFile = files[0]; 
	        byte[] fileBytes = Files.readAllBytes(downloadedFile.toPath());

	        // Limpa o arquivo baixado
	        Files.delete(downloadedFile.toPath());

	        return fileBytes;
	    }
}
