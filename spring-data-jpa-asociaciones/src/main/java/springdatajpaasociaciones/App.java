package springdatajpaasociaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationContext;
import springdatajpaasociaciones.model.Address;
import springdatajpaasociaciones.model.Employee;
import springdatajpaasociaciones.model.EmployeeType;
import springdatajpaasociaciones.repository.AddressRepository;
import springdatajpaasociaciones.repository.CompanyRepository;
import springdatajpaasociaciones.repository.EmployeeRepository;
import springdatajpaasociaciones.repository.ProjectRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
       ApplicationContext context =  SpringApplication.run(App.class, args);

       var addressRepository = context.getBean(AddressRepository.class);
       var employeeRepository = context.getBean(EmployeeRepository.class);
       var projectRepository = context.getBean(ProjectRepository.class);
       var companyRepository = context.getBean(CompanyRepository.class);

       // va a probar a asociar algunos de ellos
        Address address = new Address();
        Employee employee = new Employee(null, "emp1", 20, true, 2000.0,
                Instant.now(), LocalDate.now(), LocalDateTime.now(), List.of("email1","email2"),
                EmployeeType.JUNIOR, address, null, null);

        // hay que hacer save en el lado owner
      //  employeeRepository.save(employee);

        // pendiente: se comprueban los cambios en pg-admin o k?
    }

}
