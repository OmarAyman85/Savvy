import { Injectable, signal } from '@angular/core';
import { Widget } from '../models/dashboard';

@Injectable()
export class DashboardService {
  constructor() {}

  widgets = signal<Widget[]>([
    {
      id: 1,
      title: 'Dashboard',
      description: 'This is the dashboard',
      category: 'Dashboard',
      amount: 100,
      date: new Date(),
    },
    {
      id: 2,
      title: 'Income',
      description: 'This is the income',
      category: 'Income',
      amount: 100,
      date: new Date(),
    },
    {
      id: 3,
      title: 'Expense',
      description: 'This is the expense',
      category: 'Expense',
      amount: 100,
      date: new Date(),
    },
  ]);
}
