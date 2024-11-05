import {TrucksPosition} from "./TruckPosition.tsx";
import PdtTable from "./Deliveries/PdtTable.tsx";
import {WarehouseOverview} from "./WarehousesOverview/WarehouseOverview.tsx";

export function Dashboard() {
    return (
        <div style={{height: '100%'}}>
            <TrucksPosition/>
            <PdtTable/>
            <div className="p-4">
                <div className="grid gap-6">
                    <WarehouseOverview
                        className="col-span-full"
                    />
                </div>
            </div>
        </div>
    );
}