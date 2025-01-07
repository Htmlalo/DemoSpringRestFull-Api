package demo_learned.demo_learned;

import jakarta.servlet.annotation.WebServlet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootTest
@Slf4j
class DemoLearnedApplicationTests {

    @Test
    void contextLoads() {
        File database = new File("src/main/resources/GeoLite2-City.mmdb");

    }

}
