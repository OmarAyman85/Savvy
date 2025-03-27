import { Component, computed, Input, signal } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

export type MenuItem = {
  icon: String;
  label: String;
  route?: string;
};

@Component({
  selector: 'app-custom-sidenav',
  imports: [CommonModule, MatListModule, MatIconModule, RouterModule],
  templateUrl: './custom-sidenav.component.html',
  styles: [
    `
      .side-nav-header {
        padding-top: 24px;
        text-align: center;

        > img {
          border-radius: 100%;
          object-fit: cover;
          margin-botom: 10px;
        }
      }

      .header-text {
        > h2 {
          margin: 0;
          font-size: 1rem;
          line-height: 1.5rem;
        }

        > p {
          margin: 0;
          font-size: 0.8rem;
        }
      }

      .hide-header-text {
        opacity: 0;
        height: 0;
      }
    `,
  ],
})
export class CustomSidenavComponent {
  sideNavCollapsed = signal<boolean>(false);
  @Input() set collapsed(value: boolean) {
    this.sideNavCollapsed.set(value);
  }
  profilePicSize = computed(() => (this.sideNavCollapsed() ? '50' : '200'));

  menuItems = signal<MenuItem[]>([
    { icon: 'home', label: 'Home', route: '/home' },
    { icon: 'dashboard', label: 'Dashboard', route: '/dashboard' },
    { icon: 'person', label: 'Profile', route: '/profile' },
    { icon: 'settings', label: 'Settings', route: '/settings' },
  ]);
}
