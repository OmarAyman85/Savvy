import { Component, inject } from '@angular/core';
import { WidgetComponent } from '../../components/widget/widget.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { IncomeService } from '../../services/income.service';

@Component({
  selector: 'app-income',
  imports: [WidgetComponent, MatButtonModule, MatIcon, MatMenuModule],
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
