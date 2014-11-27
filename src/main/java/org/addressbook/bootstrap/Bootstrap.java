package org.addressbook.bootstrap;

import liquibase.util.csv.opencsv.CSVReader;
import org.addressbook.persistence.dao.CountryRepository;
import org.addressbook.persistence.domain.Country;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;

/**
 * @author alisiikh.
 */
@Component
public class Bootstrap implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private CountryRepository countryRepository;

    @PostConstruct
    public void run() throws Exception {
        Resource countriesCsv = applicationContext.getResource("classpath:countries.csv");
        CSVReader csvReader = new CSVReader(new FileReader(countriesCsv.getFile()));
        String[] heading = csvReader.readNext();

        String[] line;
        while ((line = csvReader.readNext()) != null) {
            countryRepository.save(new Country(Integer.valueOf(line[2]), line[1], line[0]));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.countryRepository = applicationContext.getBean("countryRepository", CountryRepository.class);
    }
}
