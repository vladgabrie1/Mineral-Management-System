import React from 'react';
import { PDT } from '../../model/PDT';
import {
    Modal,
    ModalContent,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Button,
} from "@nextui-org/react";

interface PdtModalProps {
    pdt: PDT | null;
    onClose: () => void;
}

const PdtModal: React.FC<PdtModalProps> = ({ pdt, onClose }) => {
    if (!pdt) return null;

    return (
        <Modal
            isOpen={!!pdt}
            onClose={onClose}
            size="lg"
        >
            <ModalContent>
                {(onClose) => (
                    <>
                        <ModalHeader className="flex flex-col gap-1">
                            Delivery Details - {pdt.licensePlate}
                        </ModalHeader>
                        <ModalBody>
                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <h3 className="font-bold">License Plate</h3>
                                    <p>{pdt.licensePlate}</p>
                                </div>
                                <div>
                                    <h3 className="font-bold">Weight</h3>
                                    <p>{pdt.weight} Tonnes</p>
                                </div>
                                <div>
                                    <h3 className="font-bold">Delivery Time</h3>
                                    <p>{new Date(pdt.deliveryTime).toLocaleString()}</p>
                                </div>
                                <div>
                                    <h3 className="font-bold">Truck Arrival Time</h3>
                                    <p>{new Date(pdt.truckArrivalTime).toLocaleString()}</p>
                                </div>
                                <div>
                                    <h3 className="font-bold">Material Type</h3>
                                    <p>{pdt.materialType}</p>
                                </div>
                                <div>
                                    <h3 className="font-bold">Warehouse ID</h3>
                                    <p>{pdt.warehouseId}</p>
                                </div>
                                <div>
                                    <h3 className="font-bold">On Time</h3>
                                    <p>{pdt.onTime ? 'Yes' : 'No'}</p>
                                </div>
                            </div>
                        </ModalBody>
                        <ModalFooter>
                            <Button color="primary" onPress={onClose}>
                                Close
                            </Button>
                        </ModalFooter>
                    </>
                )}
            </ModalContent>
        </Modal>
    );
};

export default PdtModal;