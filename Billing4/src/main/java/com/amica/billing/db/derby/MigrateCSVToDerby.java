package com.amica.billing.db.derby;

import com.amica.billing.Customer;
import com.amica.billing.db.CustomerRepository;
import com.amica.billing.db.InvoiceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses= CustomerRepository.class)
@EntityScan(basePackageClasses= Customer.class)
@EnableTransactionManagement
@PropertySource("classpath:migration.properties")

public class MigrateCSVToDerby {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext =
                     SpringApplication.run(MigrateCSVToDerby.class)) {
            CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
            //InvoiceRepository invoiceRepository = applicationContext.getBean(InvoiceRepository.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
