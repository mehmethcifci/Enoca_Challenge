package com.enoca.challenge.services;

import com.enoca.challenge.dto.request.NewEmployeeRequestDTO;
import com.enoca.challenge.dto.request.UpdateEmployeeRequestDTO;
import com.enoca.challenge.dto.response.AllEmployeesResponseDTO;
import com.enoca.challenge.entity.Company;
import com.enoca.challenge.entity.Employee;
import com.enoca.challenge.exception.custom.CompanyNotFoundException;
import com.enoca.challenge.exception.custom.EmployeeNotFoundException;
import com.enoca.challenge.repository.CompanyRepository;
import com.enoca.challenge.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public void save(NewEmployeeRequestDTO newEmployee) {
        Optional<Company> optionalCompany = companyRepository.findById(newEmployee.getCompanyOid());
        if(optionalCompany.isEmpty()){
            throw new CompanyNotFoundException("Company is not found");
        }
        employeeRepository.save(Employee.builder()
                        .name(newEmployee.getName())
                        .surname(newEmployee.getSurname())
                        .identificationNumber(newEmployee.getIdentificationNumber())
                        .salary(newEmployee.getSalary())
                        .position(newEmployee.getPosition())
                        .company(optionalCompany.get())
                .build());
    }

    public void update(UpdateEmployeeRequestDTO updateDto) {
        if (employeeRepository.findById(updateDto.getOid()).isEmpty()){
            throw new EmployeeNotFoundException("Employee is not found"); //TODO exception
        }
        employeeRepository.save(Employee.builder()
                        .oid(updateDto.getOid())
                        .name(updateDto.getName())
                        .surname(updateDto.getSurname())
                        .identificationNumber(updateDto.getIdentificationNumber())
                        .position(updateDto.getPosition())
                        .salary(updateDto.getSalary())
                        .company(companyRepository.findById(updateDto.getCompanyOid()).orElseThrow(
                                () -> new CompanyNotFoundException("Company is not found")))
                .build());
    }

    public List<AllEmployeesResponseDTO> findall() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()){
            throw new EmployeeNotFoundException("Employee is not found");
        }
        return employeeList.stream().map(employee -> AllEmployeesResponseDTO.builder()
                .name(employee.getName())
                .surname(employee.getSurname())
                .identificationNumber(employee.getIdentificationNumber())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .companyOid(employee.getCompany().getOid())
                .build()).collect(Collectors.toList());
    }

    public Boolean deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()){
            throw new EmployeeNotFoundException("Employee is not found");
        }
        employeeRepository.delete(optionalEmployee.get());
        return true;
    }
}
