import { Component, OnInit } from '@angular/core';
import {Driver} from "../shared/drivers.model";
import {DriversService} from "../shared/drivers.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-drivers-list',
  templateUrl: './drivers-list.component.html',
  styleUrls: ['./drivers-list.component.css']
})
export class DriversListComponent implements OnInit {

  errorMessage: string = "";
  drivers: Array<Driver> = [];

  constructor(private driverService: DriversService, private router: Router) { }

  ngOnInit(): void {
    this.getDrivers();
  }

  getDrivers() {
    this.driverService.getDrivers().subscribe(
      drivers => {
        this.drivers = drivers.drivers;
        console.log(drivers);
      },
      err => this.errorMessage = err
    );
  }

  onDelete(id: number) {
    this.driverService.deleteDriver(id).subscribe(
      res => {
        console.log(res);
        this.ngOnInit();
      }
    );
  }

  redirectToAdd() {
    this.router.navigate(["/driversadd"]);
  }
}
