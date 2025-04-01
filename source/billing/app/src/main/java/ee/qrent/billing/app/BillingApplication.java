package ee.qrent.billing.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ee.qrent", "ee.qrental"})
public class BillingApplication {

  public static void main(String[] args) {
    SpringApplication.run(BillingApplication.class, args);
  }
}
