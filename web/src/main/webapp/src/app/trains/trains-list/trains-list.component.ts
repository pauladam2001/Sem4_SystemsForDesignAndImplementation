import { Component, OnInit } from '@angular/core';
import {Train} from "../shared/trains.model";
import {TrainsService} from "../shared/trains.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-trains-list',
  templateUrl: './trains-list.component.html',
  styleUrls: ['../../drivers/drivers-list/drivers-list.component.css']
})
export class TrainsListComponent implements OnInit {

  errorMessage: string = "";
  trains: Array<Train> = [];

  constructor(private trainService: TrainsService, private router: Router) { }

  ngOnInit(): void {
    this.getTrains();
  }

  getTrains() {
    this.trainService.getTrains().subscribe(
      trains => {
        console.log(trains);
        this.trains = trains.trains;
      },
      err => this.errorMessage = err
    );
  }

  onDelete(id: number) {
    this.trainService.deleteTrain(id).subscribe(
      res => {
        console.log(res);
        this.ngOnInit();
      }
    );
  }

  filterTrains(filterSource: HTMLInputElement) {
    this.trainService.filterTrainsBySource(filterSource.value).subscribe(
      trains => {
          this.trains = trains.trains;
          console.log(trains);
        },
        err => this.errorMessage = err
      )

    // (<HTMLInputElement>document.getElementById("source")).value = "";
  }

  sortTrains() {
    this.trains = this.trains.sort((obj1, obj2) => obj1.departureTime < obj2.departureTime ? -1 : 1);
    // this.trainService.sortTrainsByDeparture().subscribe(
    //   trains => {
    //     this.trains = trains.trains;
    //     console.log(trains);
    //   },
    //   err => this.errorMessage = err
    // )
  }
}
