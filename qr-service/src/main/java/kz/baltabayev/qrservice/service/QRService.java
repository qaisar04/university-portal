package kz.baltabayev.qrservice.service;

import jakarta.servlet.http.HttpServletResponse;

import java.util.concurrent.CompletableFuture;

public interface QRService {

    CompletableFuture<Void> generateQRAsync(HttpServletResponse response, String link);
}
