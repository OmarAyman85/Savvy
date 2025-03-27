import { Component, inject } from '@angular/core';
import { WidgetComponent } from '../../components/widget/widget.component';
import { DashboardService } from '../../services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [WidgetComponent],
  providers: [DashboardService],
  templateUrl: './dashboard.component.html',
  styles: `.dashboard-widgets{
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 16px;
  }`,
})
export class DashboardComponent {
  store = inject(DashboardService).widgets();
}
