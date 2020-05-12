package lk.smartken.liftsimulation;

import javafx.application.Application;
import lk.smartken.liftsimulation.model.Lift;
import lk.smartken.liftsimulation.model.LiftStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static lk.smartken.liftsimulation.service.impl.LiftServiceImpl.LIFTS;

@SpringBootApplication
public class LiftsimulatorApiApplication implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(LiftsimulatorApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LiftsimulatorApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {


        //insert two lifts as key-value pair to hashmap
        LIFTS.put("LIFT1", new Lift(1, 0,"NAN",LiftStatus.IDLE));
        LIFTS.put("LIFT2",  new Lift(1, 0,"NAN",LiftStatus.IDLE));


    }

}
