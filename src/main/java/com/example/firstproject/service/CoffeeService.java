package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();
        return coffeeRepository.save(coffee);
    }

    public ResponseEntity<Coffee> update(Long id, CoffeeDto dto) {
        Coffee updated = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target != null && id == target.getId()) {
            coffeeRepository.save(updated);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<Coffee> delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target != null){
            coffeeRepository.delete(target);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
