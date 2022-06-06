export class Driver {
  id: number = 0;
  cnp: string = "";
  name: string = "";

  constructor(cnp: string="", name: string="") {
    this.cnp = cnp;
    this.name = name;
  }
}

export class DriversDTO {
  drivers: Array<Driver> = [];
}
