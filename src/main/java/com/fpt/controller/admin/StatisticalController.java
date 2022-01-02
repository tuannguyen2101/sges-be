package com.fpt.controller.admin;

import com.fpt.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thongKe")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
public class StatisticalController {
    @Autowired
    StatisticalService statisticalService;

    @GetMapping("/currentProduct")
    public ResponseEntity<List<Object>> getAllNameAndQuantity(){
        List<Object> listNameAndQuantity = statisticalService.getAllNameAndQuantity();

        return new ResponseEntity<>(listNameAndQuantity, HttpStatus.OK);
    }

    @GetMapping("/getTotal")
    public ResponseEntity<List<Object>> getTotalSalaryByYear(){
        List<Object> list = statisticalService.getTotalSalaryByYear();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getTotalByMonth/{year}")
    public ResponseEntity<List<Object>> getTotalSalaryByMonthInYear(@PathVariable("year") Integer year){
        List<Object> list = statisticalService.getTotalSalaryByMonthInYear(year);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getAllYears")
    public ResponseEntity<List<Integer>> getAllYears(){
        List<Integer> list = statisticalService.getAllYears();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getSalesByDay")
    public ResponseEntity<List<Object>> getSalesDay(){
        List<Object> list =statisticalService.getSalesByDay();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getSalesByWeek")
    public ResponseEntity<List<Object>> getSalesWeek(){
        List<Object> list = statisticalService.getSalesByWeek();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getTopTenProductSold")
    public ResponseEntity<List<Object>> getTopTen(){
        List<Object> list = statisticalService.getTopTenProductSold();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/getSalesByCurrentMonth")
    public ResponseEntity<List<Object>> getSalesByCurrentMonth(){
        List<Object> list = statisticalService.getSalesByCurrentMonth();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
