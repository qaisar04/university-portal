package kz.baltabayev.qrservice.service;

import jakarta.servlet.http.HttpServletResponse;

import java.util.concurrent.CompletableFuture;

/**
 * QRService is an interface that defines the contract for a service that generates QR codes.
 * It has a single method, generateQRAsync, which generates a QR code asynchronously for a given link.
 * The generated QR code is written directly to the provided HttpServletResponse.
 */
public interface QRService {

    /**
     * Generates a QR code for the provided link asynchronously.
     * The QR code is written directly to the provided HttpServletResponse.
     *
     * @param response the HttpServletResponse to write the QR code to
     * @param link the link to encode in the QR code
     * @return a CompletableFuture that completes when the QR code has been written to the response
     */
    CompletableFuture<Void> generateQRAsync(HttpServletResponse response, String link);
}