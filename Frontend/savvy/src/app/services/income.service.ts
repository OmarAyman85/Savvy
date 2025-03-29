import { computed, Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Income } from '../models/income';

@Injectable()
export class IncomeService {
  private http = inject(HttpClient); // Use inject() instead of constructor

  widgets = signal<Income[]>([]);
  addedWidgets = signal<Income[]>([]);

  constructor() {
    this.fetchWidgets();
  }

  fetchWidgets() {
    this.http.get<Income[]>('http://localhost:8080/api/income/all').subscribe({
      next: (data) => this.addedWidgets.set(data),
      error: (err) => console.error('Error fetching widgets', err),
    });
  }

  widgetsToAdd = computed(() => {
    const addedIds = this.addedWidgets().map((widget) => widget.id);
    return this.widgets().filter((widget) => !addedIds.includes(widget.id));
  });

  addWidget(w: Income) {
    this.addedWidgets.set([...this.addedWidgets(), { ...w }]);
  }
}
