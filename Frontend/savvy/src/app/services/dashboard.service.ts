import { computed, Injectable, signal } from '@angular/core';
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
    {
      id: 4,
      title: 'Test',
      description: 'This is the test',
      category: 'Test',
      amount: 500,
      date: new Date(),
    },
  ]);

  addedWidgets = signal<Widget[]>([
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

  widgetsToAdd = computed(() => {
    const addedIds = this.addedWidgets().map((widget) => widget.id);
    return this.widgets().filter((widget) => !addedIds.includes(widget.id));
  });

  addWidget(w: Widget) {
    this.addedWidgets.set([...this.addedWidgets(), { ...w }]);
  }

  updateWidget(id: number, widget: Partial<Widget>) {
    const index = this.addedWidgets().findIndex((w) => w.id === id);
    if (index !== -1) {
      const newWidgets = [...this.addedWidgets()];
      newWidgets[index] = { ...newWidgets[index], ...widget };
      this.addedWidgets.set(newWidgets);
    }
  }
  moveWidgetToRight(id: number) {
    const index = this.addedWidgets().findIndex((w) => w.id === id);
    if (index == this.addedWidgets().length - 1) {
      return;
    }

    const newWidgets = [...this.addedWidgets()];
    [newWidgets[index], newWidgets[index + 1]] = [
      { ...newWidgets[index + 1] },
      { ...newWidgets[index] },
    ];
    this.addedWidgets.set(newWidgets);
  }

  moveWidgetToLeft(id: number) {
    const index = this.addedWidgets().findIndex((w) => w.id === id);
    if (index == 0) {
      return;
    }

    const newWidgets = [...this.addedWidgets()];
    [newWidgets[index], newWidgets[index - 1]] = [
      { ...newWidgets[index - 1] },
      { ...newWidgets[index] },
    ];
    this.addedWidgets.set(newWidgets);
  }
}
