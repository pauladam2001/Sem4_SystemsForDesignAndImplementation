package core.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train extends BaseEntity<Long> {
    @Column(name = "departure")
    private String departureTime;

    @Column(name = "arrival")
    private String arrivalTime;

    @OneToMany(mappedBy = "train")
    Set<DriverTrain> driverTrainsList;

    @ManyToOne
    @JoinColumn(name = "routeID")
    Route route;

    public Train() {

    }

    public Train(Long id, Route route, String departureTime, String endTime) {
        this.setId(id);
        this.route = route;
        this.departureTime = departureTime;
        this.arrivalTime = endTime;
    }

    public Long getRouteId() {
        return route.getId();
    }

    public Route getRoute() {
        return this.route;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setRouteId(Long routeId) {
        this.route.setId(routeId);
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    @Override
    public String toString() {

        return "Train{" +
                "id=" + getId().toString() +
                ", route=" + route +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}