import { Component, computed, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { CustomSidenavComponent } from './components/custom-sidenav/custom-sidenav.component';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    CustomSidenavComponent,
  ],
  templateUrl: './app.component.html',
  styles: [
    `
      mat-toolbar {
        position: relative;
        z-index: 5;
      }

      mat-sidenav-container {
        height: calc(100vh - 64px);
      }

      .content {
        padding: 24px;
        background-color: white;
      }
    `,
  ],
})
export class AppComponent {
  collapsed = signal<boolean>(false);
  sideNavWidth = computed(() => (this.collapsed() ? '65px' : '250px'));
}
