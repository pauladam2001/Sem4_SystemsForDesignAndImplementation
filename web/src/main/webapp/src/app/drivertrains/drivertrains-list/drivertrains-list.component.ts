import { Component, OnInit } from '@angular/core';
import {DriverTrain} from "../shared/drivertrains.model";
import {DrivertrainsService} from "../shared/drivertrains.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-drivertrains-list',
  templateUrl: './drivertrains-list.component.html',
  styleUrls: ['../../drivers/drivers-list/drivers-list.component.css']
})
export class DrivertrainsListComponent implements OnInit {

  errorMessage: string = "";
  driverTrains: Array<DriverTrain> = [];

  constructor(private driverTrainsService: DrivertrainsService, private router: Router) { }

  ngOnInit(): void {
    this.getDriverTrains();
  }

  getDriverTrains() {
    this.driverTrainsService.getDriverTrains().subscribe(
      driverTrains => {
        this.driverTrains = driverTrains.driverTrains;
        console.log(driverTrains);
      },
      err => this.errorMessage = err
    );
  }

  onDelete(id: number) {
    this.driverTrainsService.deleteDriverTrain(id).subscribe(
      res => {
        console.log(res);
        this.ngOnInit();
      }
    );
  }
}
