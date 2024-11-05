import React, { useState, useMemo } from "react";
import { usePdt } from "../../hooks/usePdt";
import PdtModal from "./PdtModal";
import {
    DatePicker,
    Table,
    Chip,
    Spinner,
    Input,
    TableRow,
    TableCell,
    TableBody,
    TableHeader,
    TableColumn,
    Pagination,
    DateValue
} from "@nextui-org/react";
import { PDT } from "../../model/PDT";
import { useQueryClient } from "@tanstack/react-query";
import { format } from "date-fns";

export default function PdtTable() {
    const [selectedDate, setSelectedDate] = useState<string>("2024-10-30");
    const [filter, setFilter] = useState<string>("");
    const [selectedPdt, setSelectedPdt] = useState<PDT | null>(null);
    const [currentPage, setCurrentPage] = useState<number>(1);
    const queryClient = useQueryClient();

    const itemsPerPage = 6;
    const { data: pdts = [], isLoading, isError } = usePdt(selectedDate);

    const handleDateChange = (value: DateValue) => {
        const date = new Date(value.toString());
        if (!isNaN(date.getTime())) {
            const formattedDate = date.toISOString().split("T")[0];
            setSelectedDate(formattedDate);
            setCurrentPage(1);
            queryClient.invalidateQueries({ queryKey: ["pdts", formattedDate] });
        }
    };

    const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFilter(e.target.value);
        setCurrentPage(1);
    };

    const handleRowClick = (pdt: PDT) => setSelectedPdt(pdt);
    const handleModalClose = () => setSelectedPdt(null);

    const filteredPdts = useMemo(
        () => pdts.filter((pdt: PDT) => pdt.licensePlate.toLowerCase().includes(filter.toLowerCase())),
        [pdts, filter]
    );

    const totalPages = Math.max(1, Math.ceil(filteredPdts.length / itemsPerPage));
    const startIndex = (currentPage - 1) * itemsPerPage;
    const currentPdts = filteredPdts.slice(startIndex, startIndex + itemsPerPage);

    return (
        <div className="p-4">
            <h1 className="text-2xl  mb-6 text-white">Truck Arrivals</h1>
            <div className="flex mb-4">
                <Input
                    type="text"
                    placeholder="Search by License Plate"
                    value={filter}
                    onChange={handleFilterChange}
                    className="w-1/4 mr-2 text-white"
                />
                <DatePicker
                    onChange={handleDateChange}
                    className="w-1/4 text-white"
                />
            </div>

            {isLoading && (
                <div className="text-white bg-gray-800 p-4 rounded mb-4 flex justify-center">
                    <Spinner size="lg" />
                </div>
            )}

            {isError && (
                <div className="text-red-500 bg-red-100 p-4 rounded mb-4">
                    Error fetching PDTs. Please try again later.
                </div>
            )}

            {!isLoading && !isError && (
                <Table
                    aria-label="PDT table with pagination"
                    bottomContent={
                        <div className="flex w-full justify-center">
                            <Pagination
                                isCompact
                                showControls
                                showShadow
                                color={"secondary"}
                                page={currentPage}
                                total={totalPages}
                                onChange={(page) => setCurrentPage(page)}
                            />
                        </div>
                    }
                >
                    <TableHeader>
                        <TableColumn>License Plate</TableColumn>
                        <TableColumn>Arrival Time</TableColumn>
                        <TableColumn>On Time</TableColumn>
                        <TableColumn>Status</TableColumn>
                    </TableHeader>
                    <TableBody>
                        {currentPdts.length > 0 ? (
                            currentPdts.map((pdt: PDT) => (
                                <TableRow
                                    key={`${pdt.licensePlate}-${pdt.truckArrivalTime}`}
                                    onClick={() => handleRowClick(pdt)}
                                >
                                    <TableCell>{pdt.licensePlate}</TableCell>
                                    <TableCell>{format(new Date(pdt.truckArrivalTime), "dd/MM/yyyy HH:mm")}</TableCell>
                                    <TableCell>
                                        <Chip
                                            color={pdt.onTime ? "success" : "danger"}
                                            variant="flat"
                                            size="sm"
                                        >
                                            {pdt.onTime ? "Yes" : "No"}
                                        </Chip>
                                    </TableCell>
                                    <TableCell>
                                        <Chip
                                            color={pdt.weight === 0 ? "warning" : "success"}
                                            variant="flat"
                                            size="sm"
                                        >
                                            {pdt.weight === 0 ? "In Process" : "Finished"}
                                        </Chip>
                                    </TableCell>
                                </TableRow>
                            ))
                        ) : (
                            <TableRow>
                                <TableCell colSpan={4} className="text-center py-4">
                                    No PDTs found for the selected criteria
                                </TableCell>
                                <TableCell children={null} />
                                <TableCell children={null} />
                                <TableCell children={null} />
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            )}

            <PdtModal pdt={selectedPdt} onClose={handleModalClose} />
        </div>
    );
}
