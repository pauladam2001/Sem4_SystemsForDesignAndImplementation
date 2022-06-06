package core.service;

import core.domain.Driver;

import java.util.List;

public interface DriverServiceInterface {
    List<Driver> findAll();

    void save(String name, String CNP);

    void update(Long driverID, String name, String CNP);

    void delete(Long driverID);
}
