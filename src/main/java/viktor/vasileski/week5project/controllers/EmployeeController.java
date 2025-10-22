package viktor.vasileski.week5project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viktor.vasileski.week5project.entities.Employee;
import viktor.vasileski.week5project.exceptions.ValidationException;
import viktor.vasileski.week5project.payloads.EmployeeDTO;
import viktor.vasileski.week5project.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody @Validated EmployeeDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }return employeeService.saveEmployee(body);
    }

    @GetMapping
    public Page<Employee> getAll(@RequestParam(defaultValue = "0") int pageN){
        return employeeService.findAll(pageN);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable long id){
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employee updateTrip(@PathVariable long id,@RequestBody @Validated EmployeeDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }return employeeService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        employeeService.findAndDelete(id);
    }

    //TODO: patch avatar con cloudinary
}
