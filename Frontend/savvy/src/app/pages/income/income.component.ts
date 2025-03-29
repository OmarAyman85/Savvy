import { Component, inject } from '@angular/core';
import { IncomeWidgetComponent } from '../income/income-widget/income-widget.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { IncomeService } from '../../services/income.service';

@Component({
  selector: 'app-income',
  standalone: true,
  imports: [IncomeWidgetComponent, MatButtonModule, MatIcon, MatMenuModule],
  providers: [IncomeService],
  templateUrl: './income.component.html',
  styles: `
  .dashboard-widgets{
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    grid-auto-rows:300px;
    gap: 16px;
  }
  .header{
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 16px;
  }`,
})
export class IncomeComponent {
  store = inject(IncomeService);
}
