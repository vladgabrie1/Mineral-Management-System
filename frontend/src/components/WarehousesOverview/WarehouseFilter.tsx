import {Select, SelectItem} from "@nextui-org/react";
import {SellerOption} from "../../model/Warehouse.ts";


interface WarehouseFilterProps {
    sellerOptions: SellerOption[];
    onSellerChange: (sellerId: string) => void;
    defaultValue?: string;
}

export const WarehouseFilter: React.FC<WarehouseFilterProps> = ({
     sellerOptions,
     onSellerChange,
     defaultValue = "all"
     }) => {
    return (
        <Select
            label="Filter by Seller"
            className="max-w-xs"
            size="sm"
            onChange={(e) => onSellerChange(e.target.value || "all")}
            defaultSelectedKeys={[defaultValue]}
        >
            <SelectItem key="all" value="all">All Sellers</SelectItem>
            {sellerOptions.map((seller) => (
                <SelectItem key={seller.value} value={seller.value}>
                    {seller.label}
                </SelectItem>
            ))}
        </Select>
    );
};