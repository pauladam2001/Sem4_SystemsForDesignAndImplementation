package core.service;

import core.domain.Route;

import java.util.List;

public interface RouteServiceInterface {
    List<Route> findAll();

    void save(String source, String destination);

    void update(Long routeID, String source, String destination);

    void delete(Long routeID);
}
