package lk.smartken.liftsimulation.controller;

import lk.smartken.liftsimulation.model.Response;
import lk.smartken.liftsimulation.service.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smartkent/liftsimulation")
public class LiftController {

    @Autowired
    LiftService liftService;

    @GetMapping
    public Response test(@RequestParam("fromFloor") int fromFloor, @RequestParam("toFloor") int toFloor){


       return liftService.pickAndDrop(fromFloor,toFloor);


    }

}
