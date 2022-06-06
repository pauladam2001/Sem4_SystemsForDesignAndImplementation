import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule} from "@angular/router";

import { AppComponent } from './app.component';
import { DriversComponent } from './drivers/drivers.component';
import { TrainsComponent } from './trains/trains.component';
import { RoutesComponent } from './routes/routes.component';
import { DrivertrainsComponent } from './drivertrains/drivertrains.component';
import { DriversListComponent } from './drivers/drivers-list/drivers-list.component';
import { DrivertrainsListComponent } from './drivertrains/drivertrains-list/drivertrains-list.component';
import { RoutesListComponent } from './routes/routes-list/routes-list.component';
import { TrainsListComponent } from './trains/trains-list/trains-list.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {DriversService} from "./drivers/shared/drivers.service";
import { DriversAddComponent } from './drivers/drivers-add/drivers-add.component';
import { DriversUpdateComponent } from './drivers/drivers-update/drivers-update.component';
import { DrivertrainsAddComponent } from './drivertrains/drivertrains-add/drivertrains-add.component';
import { DrivertrainsUpdateComponent } from './drivertrains/drivertrains-update/drivertrains-update.component';
import { RoutesAddComponent } from './routes/routes-add/routes-add.component';
import { RoutesUpdateComponent } from './routes/routes-update/routes-update.component';
import { TrainsAddComponent } from './trains/trains-add/trains-add.component';
import { TrainsUpdateComponent } from './trains/trains-update/trains-update.component';

@NgModule({
  declarations: [
    AppComponent,
    DriversComponent,
    TrainsComponent,
    RoutesComponent,
    DrivertrainsComponent,
    DriversListComponent,
    DrivertrainsListComponent,
    RoutesListComponent,
    TrainsListComponent,
    DriversAddComponent,
    DriversUpdateComponent,
    DrivertrainsAddComponent,
    DrivertrainsUpdateComponent,
    RoutesAddComponent,
    RoutesUpdateComponent,
    TrainsAddComponent,
    TrainsUpdateComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [DriversService],
  bootstrap: [AppComponent]
})
export class AppModule { }
