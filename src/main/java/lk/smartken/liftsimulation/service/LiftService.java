package lk.smartken.liftsimulation.service;

import lk.smartken.liftsimulation.model.Response;

public interface LiftService {

    Response pickAndDrop(int fromFloor, int toFloor);
}
