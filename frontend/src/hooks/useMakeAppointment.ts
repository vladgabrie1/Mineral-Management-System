import {useMutation, useQueryClient} from "@tanstack/react-query";
import { NewAppointment} from "../model/Appointment.ts";
import {makeAppointment} from "../services/dataService.ts";

export function useMakeAppointment(){
    const queryClient = useQueryClient();
    const { mutate, isPending, isError } = useMutation({
        mutationFn: (appointment: NewAppointment) => makeAppointment(appointment),
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['appointments']});
        }
    });

    return {
        isPending,
        isError,
        makeAppointment: mutate
    };
}
