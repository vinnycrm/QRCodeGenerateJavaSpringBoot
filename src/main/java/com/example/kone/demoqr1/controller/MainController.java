package com.example.kone.demoqr1.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.kone.demoqr1.QRCodeGenerator;
import com.google.zxing.WriterException;

@Controller
public class MainController {
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

	@GetMapping("/")
	public String getQRCode(Model model) {
		String medium = "https://www.google.com/";
		String github = "https://github.com/vinnycrm";

		byte[] image = new byte[0];
		try {

			// Generate and Return Qr Code in Byte Array
			image = QRCodeGenerator.getQRCodeImage(medium, 250, 250);

			// Generate and Save Qr Code Image in static/image folder
			QRCodeGenerator.generateQRCodeImage(github, 250, 250, QR_CODE_IMAGE_PATH);

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);

		model.addAttribute("medium", medium);
		model.addAttribute("github", github);
		model.addAttribute("qrcode", qrcode);

		return "qrcode";
	}
}
