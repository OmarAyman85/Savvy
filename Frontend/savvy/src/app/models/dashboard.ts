export interface Widget {
  id: number;
  title: string;
  description: string;
  category: string;
  amount: number;
  date: Date;

  rows?: number;
  columns?: number;

  backgroundColor?: string;
  color?: string;
}
