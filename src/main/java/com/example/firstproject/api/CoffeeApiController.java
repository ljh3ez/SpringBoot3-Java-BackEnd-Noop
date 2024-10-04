package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeService.index();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeService.show(id);
    }

    @PostMapping("/api/coffees")
    public Coffee create (@RequestBody CoffeeDto coffeeDto){ // dto에 매개변수를 받아오려면 @RequestBody 가 필요하다
        return coffeeService.create(coffeeDto);
    }

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update (@PathVariable Long id, @RequestBody CoffeeDto dto){
        return coffeeService.update(id, dto);
    }

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        return coffeeService.delete(id);
    }
}
