import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app15';

  addBtnStyle(){
    return {
      'background':'lightgreen',
      'padding':'25px'
    }
  }

  data: any[] = [
    {"name":"bob","age":"23","dept":"IT"},
    {"name":"Alex","age":"31","dept":"Dev"},
    {"name":"Donald","age":"25","dept":"DevOps"}
  ];

  getColor(dept:string){
    switch(dept){
      case 'IT': return 'green';
      case 'Dev': return "blue";
    }return "";
  }

}
