package org.addressbook.bootstrap;

import liquibase.util.csv.opencsv.CSVReader;
import org.addressbook.persistence.dao.CountryRepository;
import org.addressbook.persistence.domain.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.FileReader;

/**
 * @author alisiikh.
 */
@Component
public class Bootstrap {

    @Inject
    private ApplicationContext applicationContext;

    @Inject
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
}
