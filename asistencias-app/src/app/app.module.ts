import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { RegistrarAsistenciaComponent } from './components/registrar-asistencia/registrar-asistencia.component';
import { FormsModule } from '@angular/forms';
import { RegistrarSalidaComponent } from './components/registrar-salida/registrar-salida.component';
import { ReporteAuxiliarComponent } from './components/reporte-auxiliar/reporte-auxiliar.component';
import { LoginComponent } from './components/login/login.component';
import { ReporteAdminComponent } from './components/reporte-admin/reporte-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrarAsistenciaComponent,
    RegistrarSalidaComponent,
    ReporteAuxiliarComponent,
    LoginComponent,
    ReporteAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
