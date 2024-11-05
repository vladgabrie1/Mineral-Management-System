import {useQuery} from "@tanstack/react-query";
import {getTrucksLocation} from "../services/dataService.ts";

export function useTrucks(){
    const {data: trucks, isPending, isError} = useQuery({
        queryKey: ['trucks'],
        queryFn:() => getTrucksLocation(),
        refetchInterval: 300000,
    });

    return {
        trucks,
        isPending,
        isError,
    }
}