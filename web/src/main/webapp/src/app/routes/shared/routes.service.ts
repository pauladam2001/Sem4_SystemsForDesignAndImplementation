import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import { Route, RoutesDTO} from "./routes.model";

@Injectable({
  providedIn: 'root'
})
export class RoutesService {

  private url = "http://localhost:8080/api/routes";

  constructor(private httpClient: HttpClient) { }

  getRoutes(): Observable<RoutesDTO> {
    return this.httpClient.get<RoutesDTO>(this.url);
  }

  getRoute(id: number): Observable<any> {
    return this.getRoutes().pipe(map(routes => routes.routes.find(route => route.id === id)));
  }

  addRoute(source: string, destination: string) {
    return this.httpClient.post(this.url, {
      source: source,
      destination: destination
    });
  }

  updateRoute(route: Route) {
    const url = `${this.url}/${route.id}`;
    return this.httpClient.put(url, {
      source: route.source,
      destination: route.destination
    });
  }

  deleteRoute(id: number): Observable<any> {
    let url = `${this.url}/${id}`;
    return this.httpClient.delete(url);
  }
}
