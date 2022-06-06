import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {DriverTrainsDTO} from "./drivertrains.model";

@Injectable({
  providedIn: 'root'
})
export class DrivertrainsService {

  private url = "http://localhost:8080/api/drivertrains";

  constructor(private httpClient: HttpClient) { }

  getDriverTrains(): Observable<DriverTrainsDTO> {
    return this.httpClient.get<DriverTrainsDTO>(this.url);
  }

  getDriverTrain(driverId: number, trainId: number): Observable<any> {
    return this.getDriverTrains().pipe(map(driverTrains => driverTrains.driverTrains.find(driverTrain => driverTrain.driver.id === driverId && driverTrain.train.id === trainId)));
  }

  addDriverTrain(driverId: number, trainId: number, howManyDays: string) {
    return this.httpClient.post(this.url, {
      driverID: driverId,
      trainID: trainId,
      howManyDays: howManyDays
    });
  }

  updateDriverTrain(id: number, howManyDays: string) {
    const url = `${this.url}/${id}`;
    return this.httpClient.put(url, {
      howManyDays: howManyDays
    });
  }

  deleteDriverTrain(id: number): Observable<any> {
    let url = `${this.url}/${id}`;
    return this.httpClient.delete(url);
  }
}
