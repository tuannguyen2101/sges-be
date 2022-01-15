package com.fpt.controller.staff;

import com.fpt.dto.OrderDTO;
import com.fpt.entity.Order;
import com.fpt.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/staff/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDTO>> getAll() {
        return new ResponseEntity<List<OrderDTO>>(this.orderService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/confirm")
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO dto) {
        OrderDTO orderDTO = this.orderService.update(dto);
        if (orderDTO == null) {
            return new ResponseEntity<OrderDTO>(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }

    @GetMapping("/tuannn/find")
    public ResponseEntity<Page<Order>> findAll(
            @RequestParam("status") Optional<Integer> status,
            @RequestParam("payment") Optional<Integer> payment,
            @RequestParam("name") String name,

            @RequestParam("from") String from,
            @RequestParam("to") String to,

            @RequestParam("number") Optional<Integer> number,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("direction") Optional<Integer> direction
    ) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Integer statusCheck = status.orElse(null);
        Integer paymentCheck = payment.orElse(null);

        Date fromCheck = !from.equals("") ? Date.valueOf(from) : null;
        Date toCheck = !to.equals("") ? Date.valueOf(to) : null;

        int numberCheck = number.orElse(0);
        int sizeCheck = size.orElse(10);
        int directionCheck = direction.orElse(1);

        Pageable pageable;
        Page<Order> orders;

        log.info("\nstatusCheck: " + statusCheck + " " +
                "\npaymentCheck: " + paymentCheck + " " +
                "\nname: " + name + " " +
                "\nfromCheck: " + fromCheck + " " +
                "\ntoCheck: " + toCheck + " " +
                "\nnumberCheck: " + numberCheck + " " +
                "\nsizeCheck: " + sizeCheck + " " +
                "\ndirectionCheck: " + directionCheck
        );

        if (directionCheck == 0) {
            pageable = PageRequest.of(numberCheck, sizeCheck, Sort.by(Sort.Direction.ASC, "create_date"));
        } else {
            pageable = PageRequest.of(numberCheck, sizeCheck, Sort.by(Sort.Direction.DESC, "create_date"));
        }
        try {
            if (fromCheck == null || toCheck == null) {
                if (name.equals("")) {
                    if (paymentCheck == null) {
                        if (statusCheck == null) {
                            pageable = directionCheck == 0 ?
                                    PageRequest.of(numberCheck, sizeCheck, Sort.by(Sort.Direction.ASC, "createDate"))
                                    : PageRequest.of(numberCheck, sizeCheck, Sort.by(Sort.Direction.DESC, "createDate"));
                            orders = orderService.sGetAll(pageable);
                        } else {
                            orders = orderService.sGetAllByStatus(statusCheck, pageable);
                        }
                    } else {
                        if (statusCheck == null) {
//                            pageable = directionCheck == 0 ?
//                                    PageRequest.of(numberCheck, sizeCheck, Sort.by(Sort.Direction.ASC, "createDate"))
//                                    : PageRequest.of(numberCheck, sizeCheck, Sort.by(Sort.Direction.DESC, "createDate"));
                            orders = orderService.sGetAllByPayment(paymentCheck, pageable);
                        } else {
                            orders = orderService.sGetAllByStatusAndPayment(statusCheck, paymentCheck, pageable);
                        }
                    }
                    return new ResponseEntity<>(orders, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("error: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
