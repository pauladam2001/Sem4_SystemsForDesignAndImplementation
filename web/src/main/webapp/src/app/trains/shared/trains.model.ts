import {Route} from "../../routes/shared/routes.model";

export class Train {
  id: number = 0;
  arrivalTime: string = "";
  departureTime: string = "";
  route: Route;

  constructor(arrival: string="", departure: string="", route: Route) {
    this.arrivalTime = arrival;
    this.departureTime = departure;
    this.route = route;
  }
}

export class TrainsDTO {
  trains: Array<Train> = [];
}
