import {
    Modal,
    ModalContent,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Button,
} from "@nextui-org/react";
import {Warehouse} from "../../model/Warehouse.ts";


interface WarehouseDetailsModalProps {
    warehouse: Warehouse | null;
    isOpen: boolean;
    onClose: () => void;
}

export const WarehouseDetailsModal: React.FC<WarehouseDetailsModalProps> = ({warehouse, isOpen, onClose,}) => {
    if (!warehouse) return null;

    return (
        <Modal isOpen={isOpen} onClose={onClose} size="sm">
            <ModalContent>
                <ModalHeader>Warehouse Details</ModalHeader>
                <ModalBody>
                    <div className="space-y-2">
                        <p><strong>Warehouse ID:</strong> {warehouse.id}</p>
                        <p><strong>Seller ID:</strong> {warehouse.sellerId}</p>
                        <p><strong>Material Type:</strong> {warehouse.materialType}</p>
                        <p><strong>Fill Percentage:</strong> {warehouse.fillPercentage}%</p>
                        <p><strong>Current Stock:</strong> {warehouse.stock} tons</p>
                    </div>
                </ModalBody>
                <ModalFooter>
                <Button color="primary" onPress={onClose}>
                        Close
                    </Button>
                </ModalFooter>
            </ModalContent>
        </Modal>
    );
};
