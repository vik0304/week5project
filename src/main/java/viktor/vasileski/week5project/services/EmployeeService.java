package viktor.vasileski.week5project.services;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import viktor.vasileski.week5project.entities.Employee;
import viktor.vasileski.week5project.exceptions.BadRequestException;
import viktor.vasileski.week5project.exceptions.NotFoundException;
import viktor.vasileski.week5project.payloads.EmployeeDTO;
import viktor.vasileski.week5project.repositories.EmployeeRepository;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Cloudinary imageUploader;

    private static final long MAX_SIZE = 2 * 1024 * 1024;
    private static final List<String> ALLOWED_TYPES = List.of("image/png","image/jpeg");

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
    public Employee uploadAvatar(long id, MultipartFile file){
        Employee found = findById(id);
        if(file.isEmpty()) throw new BadRequestException("Non si può caricare un file vuoto");
        if (file.getSize() > MAX_SIZE) throw new BadRequestException("File troppo grande, il massimo consentito è 2mb.");
        if (!ALLOWED_TYPES.contains(file.getContentType())) throw new BadRequestException("Inserire file di formato png/jpeg.");
        try {
            Map result = imageUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageURL= (String) result.get("url");
            found.setAvatar(imageURL);
            return employeeRepository.save(found);
        } catch(IOException e){
            throw new BadRequestException("Errore durante il caricamento dell'avatar");
        }
    }
}
