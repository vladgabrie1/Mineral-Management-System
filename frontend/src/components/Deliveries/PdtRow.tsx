import React from 'react';
import { PDT } from '../../model/PDT';

interface PdtRowProps {
    pdt: PDT;
    onClick: (pdt: PDT) => void;
}

const PdtRow: React.FC<PdtRowProps> = ({ pdt, onClick }) => {
    return (
        <tr
            onClick={() => onClick(pdt)}
            className="hover:bg-gray-700 cursor-pointer transition-colors duration-150"
        >
            <td className="py-2 px-4">{pdt.licensePlate}</td>
            <td className="py-2 px-4">{new Date(pdt.truckArrivalTime).toLocaleString()}</td>
            <td className="py-2 px-4">
                <span className={`px-2 py-1 rounded ${pdt.onTime ? 'bg-green-500' : 'bg-red-500'}`}>
                    {pdt.onTime ? 'Yes' : 'No'}
                </span>
            </td>
            <td className="py-2 px-4">
                {pdt.weight > 0 ? `${pdt.weight.toLocaleString()} kg` : 'Pending'}
            </td>
        </tr>
    );
};

export default PdtRow;
