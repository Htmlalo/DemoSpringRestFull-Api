package demo_learned.demo_learned.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo_learned.demo_learned.dto.request.IpLocationInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class IPService {
    ObjectMapper objectMapper;
    String API_IPPublic = "https://api.ipify.org";
    String API_IPLocation = "http://ip-api.com/json/";

    public String getPublicIP() {
        try {
            URI uri = URI.create(API_IPPublic);
            URL url = uri.toURL();
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));) {
                return reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public IpLocationInfo getIpLocationInfo(String ip) {
        try {
            String customFields = "status,message,country,countryCode,regionName,city,zip,lat,lon,timezone,isp,org,as,query";
            URI uri = URI.create(API_IPLocation + ip + "?fields=" + customFields);
            URL url = uri.toURL();
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return objectMapper.readValue(response.toString(), IpLocationInfo.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
