import { useTrucks } from "../hooks/useTrucks";
import { Card, CardBody, Spinner } from "@nextui-org/react";

export function TrucksPosition(): JSX.Element {
    const { isPending, isError, trucks } = useTrucks();

    const locations = [
        { name: "DOCK", color: "bg-blue-600" },
        { name: "BRIDGE", color: "bg-purple-600" },
        { name: "ENTERED", color: "bg-pink-600" },
        { name: "EXITED", color: "bg-green-600" },
    ];

    const truckCounts: Record<string, number> = locations.reduce((acc, loc) => {
        acc[loc.name] = trucks
            ? trucks.filter((truck) => truck.location === loc.name).length
            : 0;
        return acc;
    }, {} as Record<string, number>);

    if (isPending) {
        return (
            <div className="flex h-screen items-center justify-center">
                <Card className="p-5 text-center w-72">
                    <Spinner size="lg" />
                </Card>
            </div>
        );
    }

    if (isError) {
        return (
            <div className="flex h-screen items-center justify-center">
                <Card className="p-5 text-center w-72">
                    <h2 className="text-red-500 font-bold">There was an error fetching data</h2>
                </Card>
            </div>
        );
    }

    return (
        <div className="p-5">
            <h1 className="text-2xl mb-6 text-white">Trucks Status</h1>
            <div className="flex gap-5">
                {locations.map((loc) => (
                    <Card
                        key={loc.name}
                        className={`flex flex-col items-center justify-center text-center w-32 h-30 rounded-lg shadow-lg ${loc.color} text-white transition transform hover:scale-105 hover:shadow-2xl`}
                    >
                        <CardBody className="flex flex-col items-center">
                            <h2 className="text-2xl font-semibold">{truckCounts[loc.name] || 0}</h2>
                            <p className="text-medium font-semibold capitalize opacity-90">{loc.name.toLowerCase()}</p>
                        </CardBody>
                    </Card>
                ))}
            </div>
        </div>
    );
}
