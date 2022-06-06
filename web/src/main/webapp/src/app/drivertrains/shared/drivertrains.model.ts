import {Driver} from "../../drivers/shared/drivers.model";
import {Train} from "../../trains/shared/trains.model";

export class DriverTrain {
  id: number = 0
  driver: Driver;
  train: Train;
  howManyDays: string = "";

  constructor(driver: Driver, train: Train, howManyDays: string) {
    this.driver = driver;
    this.train = train;
    this.howManyDays = howManyDays;
  }
}

export class DriverTrainsDTO {
  driverTrains: Array<DriverTrain> = [];
}
