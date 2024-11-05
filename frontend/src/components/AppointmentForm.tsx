import * as React from "react";
import { useState } from "react";
import {
    Button,
    Input,
    Dropdown,
    DropdownTrigger,
    DropdownMenu,
    DropdownItem,
    Card,
    CardBody,
    DatePicker
} from "@nextui-org/react";
import { MaterialType } from "../model/MaterialType.ts";
import { AppointmentFormData } from "../model/Appointment.ts";
import { now, getLocalTimeZone, ZonedDateTime } from "@internationalized/date";

interface AppointmentFormProps {
    onSubmit: (appointmentFormData: AppointmentFormData) => void;
    successMessage?: string | null;

}

export const AppointmentForm = ({ onSubmit, successMessage }: AppointmentFormProps) => {
    const [appointmentFormData, setAppointmentFormData] = useState<AppointmentFormData>({
        date: now(getLocalTimeZone()).toString(),
        materialType: MaterialType[MaterialType.CEMENT],
        licensePlate: ""
    });

    const [isLoading, setIsLoading] = useState(false);

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setAppointmentFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleMaterialTypeChange = (key: MaterialType) => {
        setAppointmentFormData(prevState => ({
            ...prevState,
            materialType: MaterialType[key]
        }));
    };

    const handleDateChange = (value: ZonedDateTime) => {
        const formattedDate = value.toDate().toISOString().slice(0, 19);
        setAppointmentFormData(prevState => ({
            ...prevState,
            date: formattedDate
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setIsLoading(true);
        try {
            onSubmit(appointmentFormData);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="flex justify-center items-center min-h-screen bg-background">
            <Card className="w-full max-w-2xl">
                <CardBody>
                    <form onSubmit={handleSubmit} className="flex flex-col gap-4">
                        {successMessage && (
                            <div className="text-green-600 bg-green-100 p-4 rounded mb-4">
                                {successMessage}
                            </div>
                        )}
                        <div className="flex flex-col sm:flex-row gap-4">
                            <Input
                                name="licensePlate"
                                value={appointmentFormData.licensePlate}
                                onChange={handleChange}
                                label="License Plate Number"
                                variant="bordered"
                                isRequired
                                className="flex-1"
                            />
                            <Dropdown>
                                <DropdownTrigger>
                                    <Button
                                        variant="bordered"
                                        className="w-full sm:w-48"
                                    >
                                        {appointmentFormData.materialType}
                                    </Button>
                                </DropdownTrigger>
                                <DropdownMenu
                                    aria-label="Material Type Selection"
                                    selectionMode="single"
                                    selectedKeys={[appointmentFormData.materialType]}
                                    onSelectionChange={(keys) => handleMaterialTypeChange(Number(Array.from(keys)[0]) as MaterialType)}
                                >
                                    {Object.entries(MaterialType)
                                        .filter(([key]) => isNaN(Number(key)))
                                        .map(([name, value]) => (
                                            <DropdownItem key={value}>
                                                {name}
                                            </DropdownItem>
                                        ))}
                                </DropdownMenu>
                            </Dropdown>
                        </div>
                        <div className="w-full">
                            <DatePicker
                                label="Event Date"
                                variant="bordered"
                                defaultValue={now(getLocalTimeZone())}
                                hideTimeZone
                                onChange={handleDateChange}
                                className="w-full"
                                showMonthAndYearPickers
                            />
                        </div>
                        <Button
                            type="submit"
                            color="primary"
                            isLoading={isLoading}
                            className="w-full"
                        >
                            Submit
                        </Button>
                    </form>
                </CardBody>
            </Card>
        </div>
    );
};