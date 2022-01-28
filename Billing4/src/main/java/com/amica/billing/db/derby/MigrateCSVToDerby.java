package com.amica.billing.db.derby;

import com.amica.billing.Customer;
import com.amica.billing.Terms;
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

import javax.persistence.criteria.CriteriaBuilder;

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
            InvoiceRepository invoiceRepository = applicationContext.getBean(InvoiceRepository.class);

            customerRepository.deleteAll();
            System.out.println("Starting count for Customer Repository: " + customerRepository.count());
            Customer customer = new Customer("Sherry", "Custodio", Terms.CREDIT_30);
            Customer customer1 = new Customer("McKayla", "West", Terms.CREDIT_90);
            customerRepository.save(customer);
            customerRepository.save(customer1);
            System.out.println("Ending count for Customer Repository: " + customerRepository.count());
            System.out.println("For " + customerRepository.findByFirstNameAndLastName("McKayla","West"));
            customerRepository.deleteAll();

            invoiceRepository.deleteAll();
            System.out.println(invoiceRepository.count());


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
