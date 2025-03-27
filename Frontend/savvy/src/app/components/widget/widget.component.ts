import { Component, input } from '@angular/core';
import { Widget } from '../../models/dashboard';

@Component({
  selector: 'app-widget',
  imports: [],
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
  `,
})
export class WidgetComponent {
  data = input.required<Widget>();
}
