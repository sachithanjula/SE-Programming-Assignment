package lk.smartken.liftsimulation.service.impl;

import lk.smartken.liftsimulation.model.Lift;
import lk.smartken.liftsimulation.model.LiftStatus;
import lk.smartken.liftsimulation.model.Response;
import lk.smartken.liftsimulation.service.LiftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class LiftServiceImpl implements LiftService {

    //initialized a hashmap with liftname and lift object
    public final static HashMap<String, Lift> LIFTS = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(LiftServiceImpl.class);


    @Override
    public Response pickAndDrop(int fromFloor, int toFloor) {

        //get values from hashmap
        Lift lift1 = LIFTS.get("LIFT1");
        Lift lift2 = LIFTS.get("LIFT2");

        int gTime = 0;

        if (lift1.getLiftStatus().equals(LiftStatus.IDLE)) {

            lift1.setDirection(checkDirection(fromFloor, toFloor));
            lift1.setLiftStatus(LiftStatus.TO_PICKUP);

            //replace lift1 values
            LIFTS.replace("LIFT1", lift1);

            //generate time
            gTime = generateTime(lift1.getCurrentFloor(), fromFloor);
            lift1.setLiftStatus(LiftStatus.PICKUP);

            lift1.setCurrentFloor(fromFloor);
            setLiftStatusByTime(gTime, "LIFT1", lift1, toFloor);

        } else if (lift2.getLiftStatus().equals(LiftStatus.IDLE)) {

            lift2.setDirection(checkDirection(fromFloor, toFloor));
            lift2.setLiftStatus(LiftStatus.TO_PICKUP);

            //replace lift2 values
            LIFTS.replace("LIFT2", lift2);

            //generate time
            gTime = generateTime(fromFloor, toFloor);
            lift2.setLiftStatus(LiftStatus.PICKUP);

            lift2.setCurrentFloor(fromFloor);
            setLiftStatusByTime(gTime, "LIFT2", lift2, toFloor);
        }

        return new Response(gTime);


    }

    //calculate time to go between floors
    public int generateTime(int cur, int to) {
        int number = (to - cur) * 3;

        //get positive value
        return  number < 0 ? -number : number;


    }

    public void setLiftStatusByTime(int time, String liftName, Lift lift, int des) {

        //set time to run the function
        time = time * 1000;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        //System.out.println(liftName + "----" + LIFTS.get(liftName).toString());
                        logger.info(liftName + " ---- " + LIFTS.get(liftName).toString());

                        //get lift current status
                        LiftStatus liftStatus = lift.getLiftStatus();

                        switch (liftStatus) {
                            case PICKUP:
                                LIFTS.replace(liftName, lift);

                                //set new status to TO_DROPOFF
                                lift.setLiftStatus(LiftStatus.TO_DROPOFF);
                                lift.setPerson(1);

                                setLiftStatusByTime(4, liftName, lift, des);
                                break;

                            case TO_DROPOFF:
                                //replace with new lift values
                                LIFTS.replace(liftName, lift);

                                int i = generateTime(lift.getCurrentFloor(), des);

                                //set new status to DROPOFF
                                lift.setLiftStatus(LiftStatus.DROPOFF);

                                lift.setCurrentFloor(des);

                                setLiftStatusByTime(i, liftName, lift, des);
                                break;

                            case DROPOFF:
                                //replace with new lift values
                                LIFTS.replace(liftName, lift);

                                //set new status to IDLE
                                lift.setLiftStatus(LiftStatus.IDLE);
                                lift.setPerson(0);

                                setLiftStatusByTime(4, liftName, lift, des);
                                break;

                            case IDLE:

                                //replace with new lift values
                                LIFTS.replace(liftName, lift);
                                break;
                        }

                    }
                },
                time  // delayed time
        );
    }

    //check lift direction
    public String checkDirection(int from, int to) {

        if (from > to) {
            return "DOWN";
        } else {
            return "UP";
        }

    }
}
