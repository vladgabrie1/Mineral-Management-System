import {SellerOption, Warehouse} from "../model/Warehouse.ts";

export const getFillColor = (fillPercentage: number): string => {
    if (fillPercentage > 80) return 'bg-red-500';
    if (fillPercentage > 50) return 'bg-yellow-500';
    return 'bg-green-500';
};

export const getUniqueSellerOptions = (warehouses: Warehouse[]): SellerOption[] => {
    const sellers = [...new Set(warehouses.map(w => w.sellerId))];
    return sellers.map(id => ({ value: id, label: `Seller ${id}` }));
};