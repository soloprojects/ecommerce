package com.ecommerce.bookinventory.controller;

import com.ecommerce.bookinventory.dto.BookDto;
import com.ecommerce.bookinventory.exception.BusinessException;
import com.ecommerce.bookinventory.service.BookService;
import com.ecommerce.bookinventory.utility.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/book-inventory")
public class BookController {

    private final BookService service;

    @GetMapping("/item/{id}")
    public ResponseEntity<Object> getItem(@PathVariable("id") String id){

        try{
            return ResponseEntity.ok(service.findById(id));
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(){
        try{
            return ResponseHandler.generateResponse("Successfully obtained items", HttpStatus.OK, service.findAll());
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping("/search")
    public ResponseEntity<Object> getAll(@RequestParam("search_query") String searchQuery){
        try{
            return ResponseHandler.generateResponse("Search result", HttpStatus.OK, service.search(searchQuery));
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody BookDto request){
        try{
            service.save(request);
            return ResponseHandler.generateResponse("Successfully created", HttpStatus.CREATED, null);
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

}
