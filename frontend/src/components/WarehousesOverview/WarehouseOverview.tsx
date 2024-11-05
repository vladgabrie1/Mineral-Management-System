import React, { useState, useMemo } from 'react';
import { Card, CardBody, Spinner } from "@nextui-org/react";
import { useWarehouses } from "../../hooks/useWarehouses";
import { Warehouse } from "../../model/Warehouse";
import { getUniqueSellerOptions } from "../../util/warehouseUtils";
import { WarehouseCard } from "./WarehouseCard";
import { WarehouseFilter } from "./WarehouseFilter";
import { WarehouseDetailsModal } from "./WarehouseDetails";

interface WarehouseOverviewProps {
    className?: string;
}

export const WarehouseOverview: React.FC<WarehouseOverviewProps> = ({ className = "" }) => {
    const { warehouses, isLoading, error } = useWarehouses();
    const [selectedSeller, setSelectedSeller] = useState<string>("all");
    const [selectedWarehouse, setSelectedWarehouse] = useState<Warehouse | null>(null);
    const [isModalOpen, setIsModalOpen] = useState(false);

    const sellerOptions = useMemo(() =>
        getUniqueSellerOptions(warehouses), [warehouses]
    );

    const filteredWarehouses = useMemo(() => {
        if (selectedSeller === "all") return warehouses || [];
        return (warehouses || []).filter(w => w.sellerId === selectedSeller);
    }, [selectedSeller, warehouses]);

    const handleWarehouseClick = (warehouse: Warehouse) => {
        setSelectedWarehouse(warehouse);
        setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
        setSelectedWarehouse(null);
    };

    const renderContent = () => {
        if (isLoading) {
            return (
                <div className="flex justify-center items-center">
                    <div className="p-5">
                        <Spinner size="lg"/>
                    </div>
                </div>
            );
        }

        if (error) {
            console.log(error);
            return (
                <div className="flex justify-center items-center">
                    <div className="p-5">
                        <p className="text-red-500">Failed to load warehouses</p>
                    </div>
                </div>
            );
        }

        return (
            <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-3">
                {filteredWarehouses.map((warehouse) => (
                    <WarehouseCard
                        key={warehouse.id}
                        warehouse={warehouse}
                        onClick={() => handleWarehouseClick(warehouse)}
                    />
                ))}
            </div>
        );
    };

    return (
        <Card className={className}>
            <CardBody>
                <div className="flex items-center justify-between mb-4">
                    <h2 className="text-lg font-semibold">Warehouses Overview</h2>
                    {!isLoading && !error && (
                        <WarehouseFilter
                            sellerOptions={sellerOptions}
                            onSellerChange={setSelectedSeller}
                        />
                    )}
                </div>

                {renderContent()}

                <WarehouseDetailsModal
                    warehouse={selectedWarehouse}
                    isOpen={isModalOpen}
                    onClose={handleModalClose}
                />
            </CardBody>
        </Card>
    );
};
