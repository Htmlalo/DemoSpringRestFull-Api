package demo_learned.demo_learned.service;

import demo_learned.demo_learned.dto.request.ApiResponse;
import demo_learned.demo_learned.dto.request.IpLocationInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class IpLocationInfoService {
    IPService ipService;

    public ApiResponse<IpLocationInfo> getIpLocationInfo(String ip) {
        return ApiResponse.ok(ipService.getIpLocationInfo(ip));
    }

    public String getPublicIP() {
        return ipService.getPublicIP();
    }
}
