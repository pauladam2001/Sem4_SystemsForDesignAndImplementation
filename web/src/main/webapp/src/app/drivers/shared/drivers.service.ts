import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Driver, DriversDTO} from "./drivers.model";

@Injectable({
  providedIn: 'root'
})
export class DriversService {

  private url = "http://localhost:8080/api/drivers";

  constructor(private httpClient: HttpClient) { }

  getDrivers(): Observable<DriversDTO> {
    return this.httpClient.get<DriversDTO>(this.url);
  }

  getDriver(id: number): Observable<any> {
    return this.getDrivers().pipe(map(drivers => drivers.drivers.find(driver => driver.id === id)));
  }

  addDriver(cnp: string, name: string) {
    return this.httpClient.post(this.url, {
      cnp: cnp,
      name: name
    });
  }

  updateDriver(driver: Driver) {
    const url = `${this.url}/${driver.id}`;
    return this.httpClient.put(url, {
      cnp: driver.cnp,
      name: driver.name
    });
  }

  deleteDriver(id: number): Observable<any> {
    let url = `${this.url}/${id}`;
    return this.httpClient.delete(url);
  }
}
