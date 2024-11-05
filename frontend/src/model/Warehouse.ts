export interface Warehouse {
  name: string;
  id: string;
  sellerId: string;
  materialType: string;
  fillPercentage: number;
  stock: number;
}

export interface SellerOption {
  value: string;
  label: string;
}