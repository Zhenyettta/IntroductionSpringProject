import {MDBDataTable} from 'mdbreact';
import "../styles/table.css";
import {Button} from "react-bootstrap";
import Edit from "../assets/edit.png";
import Delete from "../assets/delete.png";
import {Product} from "../utils/types.ts";
import {ROLE_KEY, USER_ROLE_MANAGER} from "../utils/constants.ts";
import {useState} from "react";

interface Props {
    products: Product[]
    onEditButtonClick: (id: number) => void
    onDeleteButtonClick: (id: number) => void

}


export default function ProdTable({ products, onEditButtonClick, onDeleteButtonClick }: Props) {
    const [confirmationMap, setConfirmationMap] = useState<{ [key: number]: boolean }>({});

    function renderButtons(id: number) {
        const showConfirmation = confirmationMap[id] || false;

        return (
            <div>
                <Button
                    onClick={() => onEditButtonClick(id)}
                    className="p-1 rounded-full bg-transparent border-0"
                    style={{ marginRight: '5px' }}
                >
                    <img alt="Image" src={Edit} style={{ height: '2em' }} />
                </Button>
                {localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER && (
                    <Button
                        onClick={() => {
                            setConfirmationMap({ ...confirmationMap, [id]: true });
                        }}
                        className="p-1 rounded-full bg-transparent border-0"
                    >
                        <img alt="Image" src={Delete} style={{ height: '2em' }} />
                    </Button>
                )}
                {showConfirmation && (
                    <div>
                        <p>Will delete. Proceed?</p>
                        <button
                            onClick={() => {
                                onDeleteButtonClick(id);
                                setConfirmationMap({ ...confirmationMap, [id]: false });
                            }}
                        >
                            Yes
                        </button>
                        <button
                            onClick={() => {
                                setConfirmationMap({ ...confirmationMap, [id]: false });
                            }}
                        >
                            No
                        </button>
                    </div>
                )}
            </div>
        );
    }
        return (
            <MDBDataTable
                noBottomColumns
                striped
                bordered
                hover
                data={{
                    columns: [
                        {
                            label: 'Name',
                            field: 'name',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Description',
                            field: 'description',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Category',
                            field: 'category',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Manufacturer',
                            field: 'manufacturer',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Amount In Stock',
                            field: 'inStockAmount',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Price',
                            field: 'price',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Total',
                            field: 'total',
                            sort: 'asc',
                            width: 150
                        },
                        {
                            label: 'Actions',
                            field: 'actions',
                            width: 150
                        }
                    ],
                    rows: products.map((product) => ({
                        name: product.name,
                        description: product.description,
                        category: product.category.name,
                        manufacturer: product.producer,
                        inStockAmount: product.inStockAmount,
                        price: `${product.price.toFixed(2)} ₴`,
                        total: `${(product.price * product.inStockAmount).toFixed(2)} ₴`,
                        actions: renderButtons(product.id)
                    }))
                }}
            />
        );
}
