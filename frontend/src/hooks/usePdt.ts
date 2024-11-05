import { useQuery } from "@tanstack/react-query";
import { getPDTsByDate } from "../services/dataService";
import { PDT } from "../model/PDT";

export function usePdt(date: string | null) {
    const query = useQuery<PDT[], Error>({
        queryKey: ["pdts", date],
        queryFn: async () => {
            if (!date) return [];
            try {
                return await getPDTsByDate(date);
            } catch (error) {
                console.error("Error fetching PDTs:", error);
                throw error instanceof Error ? error : new Error('Unknown error occurred');
            }
        },
        enabled: !!date,
    });

    return {
        data: query.data ?? [],
        isLoading: query.isLoading,
        isError: query.isError,
    };
}
