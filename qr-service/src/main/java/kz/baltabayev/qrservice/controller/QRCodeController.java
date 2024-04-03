package kz.baltabayev.qrservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import kz.baltabayev.qrservice.service.QRService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * QRCodeController is a REST controller that provides an API for generating QR codes.
 * It uses the QRService to generate the QR codes asynchronously.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qr")
public class QRCodeController {

    private final QRService qrService;

    /**
     * Generates a QR code for the provided link.
     * The QR code is written directly to the HTTP response.
     *
     * @param response the HTTP response to write the QR code to
     * @param link the link to encode in the QR code
     * @return a CompletableFuture that completes when the QR code has been written to the response
     */
    @GetMapping("generate")
    CompletableFuture<Void> generateQR(
            HttpServletResponse response,
            @RequestParam String link
    ) {
        return qrService.generateQRAsync(response, link);
    }
}