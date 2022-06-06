import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DriversComponent} from "./drivers/drivers.component";
import {TrainsComponent} from "./trains/trains.component";
import {RoutesComponent} from "./routes/routes.component";
import {DrivertrainsComponent} from "./drivertrains/drivertrains.component";
import {DriversAddComponent} from "./drivers/drivers-add/drivers-add.component";

const routes: Routes = [
  {path: 'drivers', component: DriversComponent},
  {path: 'driversadd', component: DriversAddComponent},
  {path: 'trains', component: TrainsComponent},
  {path: 'routes', component: RoutesComponent},
  {path: 'drivertrains', component: DrivertrainsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
