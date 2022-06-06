import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Train, TrainsDTO} from "./trains.model";

@Injectable({
  providedIn: 'root'
})
export class TrainsService {

  private url = "http://localhost:8080/api/trains";

  constructor(private httpClient: HttpClient) { }

  getTrains(): Observable<TrainsDTO> {
    return this.httpClient.get<TrainsDTO>(this.url);
  }

  getTrain(id: number): Observable<any> {
    return this.getTrains().pipe(map(trains => trains.trains.find(train => train.id === id)));
  }

  addTrain(arrival: string, departure: string, routeId: number) {
    return this.httpClient.post(this.url, {
      arrivalTime: arrival,
      departureTime: departure,
      routeID: routeId
    });
  }

  updateTrain(id: number, arrival: string, departure: string) {
    const url = `${this.url}/${id}`;
    return this.httpClient.put(url, {
      arrivalTime: arrival,
      departureTime: departure
    });
  }

  deleteTrain(id: number): Observable<any> {
    let url = `${this.url}/${id}`;
    return this.httpClient.delete(url);
  }

  filterTrainsBySource(source: string): Observable<TrainsDTO> {
    let url = this.url + `/filterTrainsBySource/${source}`;
    return this.httpClient.get<TrainsDTO>(url);
  }

  sortTrainsByDeparture() {
    let url = this.url + "/sortTrainsByDeparture";
    return this.httpClient.get<TrainsDTO>(url);
  }
}
