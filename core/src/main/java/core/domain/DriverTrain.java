package core.domain;

import javax.persistence.*;

@Entity
@Table(name = "drivertrain")
public class DriverTrain extends BaseEntity<Long> {
    // m:n between driver and train

    @ManyToOne
    @JoinColumn(name = "driverID")
    Driver driver;

    @ManyToOne
    @JoinColumn(name = "trainID")
    Train train;

    @Column(name = "howManyDays")
    private String howManyDays;

    public DriverTrain() {

    }

    public DriverTrain(Long id, Driver driver, Train train, String howManyDays) {
        this.setId(id);
        this.driver = driver;
        this.train = train;
        this.howManyDays = howManyDays;
    }

    public Long getDriverID() {
        return this.driver.getId();
    }

    public Long getTrainID() {
        return this.train.getId();
    }

    public Driver getDriver() {
        return this.driver;
    }

    public Train getTrain() {
        return this.train;
    }

    public void setDriverId(Long driver) {
        this.driver.setId(driver);
    }

    public void setTrainId(Long train) {
        this.train.setId(train);
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getHowManyDays() {
        return howManyDays;
    }

    public void setHowManyDays(String howManyDays) {
        this.howManyDays = howManyDays;
    }

    @Override
    public String toString() {
        return "DriverTrain{" +
                "driver=" + driver +
                ", train=" + train +
                '}';
    }
}
