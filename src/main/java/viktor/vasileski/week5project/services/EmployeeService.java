package viktor.vasileski.week5project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viktor.vasileski.week5project.entities.Employee;
import viktor.vasileski.week5project.exceptions.NotFoundException;
import viktor.vasileski.week5project.payloads.EmployeeDTO;
import viktor.vasileski.week5project.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> findAll(int pageN){
        Pageable pageable = PageRequest.of(pageN, 25);
        return employeeRepository.findAll(pageable);
    }

    public Employee findById(long id){
        return employeeRepository.findById(id).orElseThrow(()->new NotFoundException("L'id inserito è errato o non esiste in db."));
    }

    public Employee saveEmployee(EmployeeDTO payload){
        Employee newEmployee = new Employee(payload.username(), payload.name(), payload.surname(), payload.email());
        return employeeRepository.save(newEmployee);
    }

    public Employee findByIdAndUpdate(long id, EmployeeDTO payload){
        Employee found = findById(id);
        found.setUsername(payload.username());
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        return employeeRepository.save(found);
    }

    public void findAndDelete(long id){
        Employee found = findById(id);
        employeeRepository.delete(found);
    }

    //TODO:patch add avatar
}
