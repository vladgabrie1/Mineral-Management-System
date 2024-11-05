import {useQuery} from "@tanstack/react-query";
import {Warehouse} from "../model/Warehouse.ts";
import {getWarehouses} from "../services/dataService.ts";


export const useWarehouses = () => {
    const {
        data: warehouses = [],
        isLoading,
        error,
    } = useQuery<Warehouse[], Error>({
        queryKey: ['warehouses'],
        queryFn: getWarehouses,
        refetchInterval: 300000,
    });

    return {
        warehouses,
        isLoading,
        error,
    };
};