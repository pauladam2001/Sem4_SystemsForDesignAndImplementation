export class Route {
  id: number = 0;
  destination: string = "";
  source: string = "";

  constructor(destination: string="", source: string="") {
    this.destination = destination;
    this.source = source;
  }
}

export class RoutesDTO {
  routes: Array<Route> = [];
}
