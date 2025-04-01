package ee.qrent.transaction;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories

@EntityScan("ee.qrental.transaction.entity.jakarta")
public class TransactionSpringDataConfig {}
