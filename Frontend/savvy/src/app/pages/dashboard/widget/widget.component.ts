import { Component, input, signal } from '@angular/core';
import { Widget } from '../../models/dashboard';
import { MatButtonModule } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { WidgetOptionsComponent } from '../widget-options/widget-options.component';

@Component({
  selector: 'app-widget',
  imports: [MatButtonModule, MatIcon, WidgetOptionsComponent],
  templateUrl: './widget.component.html',
  styles: `
  :host{
    display: block;
    border-radius: 16px;
    color: #000000;
  }

  .container{
    position: relative;
    box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.4);
    height: 100%;
    width: 100%;
    padding: 32px;
    box-sizing: border-box;
    overflow: hidden;
    border-color: #000000;
    border-width: 1px;
    border-style: solid;
    border-radius: inherit;
  }

  .settings-button{
    position: absolute;
    top: 20px;
    right: 20px;
    color: rgb(85, 65, 81);
    transition: background-color 0.3s;
}
  `,
  host: {
    '[style.grid-row]': '"span " + (data().rows ?? 1)',
    '[style.grid-column]': '"span " + (data().columns ?? 1)',
  },
})
export class WidgetComponent {
  data = input.required<Widget>();
  showOptions = signal(false);
}
