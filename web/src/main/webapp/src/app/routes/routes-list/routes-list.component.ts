import { Component, OnInit } from '@angular/core';
import {Route} from "../shared/routes.model";
import {RoutesService} from "../shared/routes.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-routes-list',
  templateUrl: './routes-list.component.html',
  styleUrls: ['../../drivers/drivers-list/drivers-list.component.css']
})
export class RoutesListComponent implements OnInit {

  errorMessage: string = "";
  routes: Array<Route> = [];

  constructor(private routesService: RoutesService, private router: Router) { }

  ngOnInit(): void {
    this.getRoutes();
  }

  getRoutes() {
    this.routesService.getRoutes().subscribe(
      routes => {
        this.routes = routes.routes;
        console.log(routes);
      },
      err => this.errorMessage = err
    );
  }

  onDelete(id: number) {
    this.routesService.deleteRoute(id).subscribe(
      res => {
        console.log(res);
        this.ngOnInit();
      }
    );
  }
}
