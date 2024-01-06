package com.enoca.challenge.controller;

import com.enoca.challenge.dto.request.NewEmployeeRequestDTO;
import com.enoca.challenge.dto.request.UpdateEmployeeRequestDTO;
import com.enoca.challenge.dto.response.AllEmployeesResponseDTO;
import com.enoca.challenge.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/create")
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid NewEmployeeRequestDTO newEmployee){
        employeeService.save(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateEmployee(@RequestBody @Valid UpdateEmployeeRequestDTO updateDto){
        employeeService.update(updateDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/findall")
    public ResponseEntity<List<AllEmployeesResponseDTO>> findAll(){
        return ResponseEntity.ok(employeeService.findall());
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
