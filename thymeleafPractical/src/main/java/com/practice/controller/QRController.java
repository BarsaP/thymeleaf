package com.practice.controller;

import java.io.File;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.util.QRCodeGenerator;
import java.io.FileInputStream;
import java.io.InputStream;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class QRController {

    @GetMapping("/qr")
    @ResponseBody
    public void getQRCode(HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        String url = "http://localhost:8080/join";
        QRCodeGenerator.generateQRCode(url, "qr_code.png");

        File file = new File("qr_code.png");
        InputStream is = new FileInputStream(file);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}

