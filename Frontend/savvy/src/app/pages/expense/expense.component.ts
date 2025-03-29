import { Component, inject } from '@angular/core';
import { ExpenseService } from '../../services/expense.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { ExpenseWidgetComponent } from './expense-widget/expense-widget.component';

@Component({
  selector: 'app-expense',
  standalone: true,
  imports: [ExpenseWidgetComponent, MatButtonModule, MatIcon, MatMenuModule],
  providers: [ExpenseService],
  templateUrl: './expense.component.html',
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
export class ExpenseComponent {
  store = inject(ExpenseService);
}
