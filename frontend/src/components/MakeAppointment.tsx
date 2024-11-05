import {AppointmentFormData, NewAppointment} from "../model/Appointment.ts";
import { AppointmentForm } from "./AppointmentForm.tsx";
import { useMakeAppointment } from "../hooks/useMakeAppointment.ts";
import {Spinner} from "@nextui-org/react";
import {useState} from "react";


export function MakeAppointment() {
    const [successMessage, setSuccessMessage] = useState<string | null>(null);
    const { isPending, isError, makeAppointment } = useMakeAppointment();

    const handleMakeAppointment = (appointmentFormData: AppointmentFormData) => {
        const newAppointment: NewAppointment = {
            ...appointmentFormData,
        };
        makeAppointment(newAppointment, {
            onSuccess: () => {
                setSuccessMessage("Appointment successfully created!");
            }
        });
    };

    return (
        <div style={{ height: '100vh', display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center' }}>
            <AppointmentForm onSubmit={handleMakeAppointment} successMessage={successMessage} />
            {isPending && (
                <div className="text-white bg-gray-800 p-4 rounded mb-4 flex justify-center">
                    <Spinner size="lg"/>
                </div>
            )}
            {isError && (
                <div className="text-red-500 bg-red-100 p-4 rounded mb-4">
                    Error creating appointment. Please try again later.
                </div>
            )}
        </div>
    );
}