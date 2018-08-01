import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UrlComponent } from './url/url.component';

const routes: Routes = [
  {
    path: '',
    component: UrlComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }