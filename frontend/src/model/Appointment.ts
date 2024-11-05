import {MaterialType} from "./MaterialType.ts";

export type Appointment = {
    sellerId: string;
    date: string;
    materialType: MaterialType;
    licensePlate: string;
}

export type AppointmentFormData = Omit<Appointment, 'sellerId'>
export type NewAppointment = Omit<Appointment, 'sellerId'>;