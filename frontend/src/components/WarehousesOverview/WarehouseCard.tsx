import { Card, CardBody } from "@nextui-org/react";
import {Warehouse} from "../../model/Warehouse.ts";
import {getFillColor} from "../../util/warehouseUtils.ts";





interface WarehouseCardProps {
    warehouse: Warehouse;
    onClick: () => void;
}

export const WarehouseCard: React.FC<WarehouseCardProps> = ({ warehouse, onClick }) => {
    const fillPercentage = warehouse.fillPercentage;
    const fillColor = getFillColor(fillPercentage);

    return (
        <Card isPressable shadow={"lg"} onPress={onClick} className="w-full">
            <CardBody className="p-3">
                <div className="text-sm font-semibold">{warehouse.name}</div>
                <div className="text-xs text-gray-600 mt-2 mb-2">{warehouse.materialType}</div>
                <div className="w-full h-3 bg-gray-200 rounded">
                    <div
                        className={`h-full rounded ${fillColor}`}
                        style={{ width: `${fillPercentage}%` }}
                    />
                </div>
                <div className="text-xs mt-1 text-right">{fillPercentage.toFixed(1)}%</div>
            </CardBody>
        </Card>
    );
};