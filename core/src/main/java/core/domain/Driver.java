package core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "driver")
public class Driver extends BaseEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "CNP")
    private String CNP;

    @OneToMany(mappedBy = "driver")
    Set<DriverTrain> driverTrains;

    public Driver() {

    }

    public Driver(Long id, String name, String CNP) {
        setId(id);
        this.name = name;
        this.CNP = CNP;
    }

    public String getName() {
        return name;
    }

    public String getCNP() {
        return CNP;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", CNP=" + CNP +
                "} " + super.toString();
    }
}
