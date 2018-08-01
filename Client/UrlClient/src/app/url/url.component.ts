import { Component, OnInit } from '@angular/core';

import { FormControl, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import { Observable } from 'rxjs';
import { TouchSequence } from '../../../node_modules/@types/selenium-webdriver';
import { Url } from './url';

@Component({
  selector: 'app-url',
  templateUrl: './url.component.html',
  styleUrls: ['./url.component.css']
})
export class UrlComponent implements OnInit {

  anArray : Array<Url> = [];

  constructor(private api: ApiService) { }

  ngOnInit() {
    this.api.getUrls().subscribe(
      data => {this.anArray = data}
    );
  }
  

  url = new FormControl('', Validators.required);

  shortenUrl() {
   this.api.shortenUrl(this.url.value).subscribe(
    data => {if(data.id != null) {this.anArray.push(data)}else {alert("Url already exists")}}, error => console.error(error));
    this.url.setValue('');
  }

   originalUrl(url) {
    this.api.originalUrl(url).subscribe(data => {window.open("http://"+data.originalUrl,'_blank' // <- This is what makes it open in a new window.
    )});
   }


   
  

}
