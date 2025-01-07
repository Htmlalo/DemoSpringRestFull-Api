package demo_learned.demo_learned.controller;


import demo_learned.demo_learned.dto.request.IpLocationInfo;
import demo_learned.demo_learned.service.IPService;
import demo_learned.demo_learned.util.XClientInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class IpAddressController {
    IPService ipService;
    XClientInfo xClientInfo;

    @GetMapping("/getPublicIP")
    @ResponseBody
    public String getPublicIP() {
        return ipService.getPublicIP();
    }


    @GetMapping("/getIpLocationInfo")
    public IpLocationInfo getIpLocationInfo(@RequestParam String ip) {
        return ipService.getIpLocationInfo(ip);
    }

    @GetMapping("/save-fingerprint")
    public String newOrder(HttpServletRequest request) throws NoSuchAlgorithmException {
        String clientIpBrowser = xClientInfo.getUserAgent(request);
        return hashSHA256(clientIpBrowser);
    }

    private static String hashSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
