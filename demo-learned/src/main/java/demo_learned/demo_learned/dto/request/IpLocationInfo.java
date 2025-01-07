package demo_learned.demo_learned.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
@Data
public class IpLocationInfo {
    String query;
    String status;
    String country;
    String countryCode;
    String regionName;
    String city;
    String zip;
    double lat;
    double lon;
    String timezone;
    String isp;
    String org;
    String as;
}
