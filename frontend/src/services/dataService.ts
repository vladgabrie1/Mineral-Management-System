import {Appointment, NewAppointment} from "../model/Appointment.ts";
import axios from "axios";
import {Truck} from "../model/Truck.ts";
import {PDT} from "../model/PDT.ts";
import {Warehouse} from "../model/Warehouse.ts";

export async function makeAppointment(appointment: NewAppointment){
    const {data: newAppointment} = await axios.post<Appointment>('/api/appointments', appointment);
    return newAppointment;
}

export async function getTrucksLocation(){
    const {data: trucks} = await axios.get<Truck[]>("/api/trucks/live");
    return Array.isArray(trucks) ? trucks : [];
}

export async function getPDTsByDate(date: string): Promise<PDT[]> {
    try {
        const { data } = await axios.get<PDT[]>("/api/pdt/by-date", {
            params: { date },
            validateStatus: (status) => status < 500,
        });

        if (!data) {
            return [];
        }

        return data;
    } catch (error) {
        console.error("Error in getPDTsByDate:", error);
        throw error;
    }
}

export async function getWarehouses(){
    const {data: warehouses} = await axios.get<Warehouse[]>("/api/warehouses");
    return Array.isArray(warehouses) ? warehouses : [];
}