import ProdTable from "../components/ProdTable.tsx";
import Title from "../components/Title.tsx";
import {Button, Col, Container, Row} from "react-bootstrap";
import {useEffect, useState} from "react";
import {Product} from "../utils/types.ts";
import axios, {AxiosError} from "axios";
import {PRODUCTS_ENDPOINT} from "../utils/apiEndpoints.ts";
import {
    AUTHORIZATION_KEY,
    OPERATION_CREATE,
    OPERATION_EDIT,
    ROLE_KEY,
    USER_ROLE_EMPLOYEE,
    USER_ROLE_MANAGER
} from "../utils/constants.ts";
import {handleLogout} from "../utils/sharedFunctions.ts";
import ProductsFormWindow from "../components/ProductsFormWindow.tsx";
import {useLocation} from "react-router-dom";


export default function Products() {
    const [products, setProducts] = useState<Product[]>([]);
    const [isDataCorrect, setIsDataCorrect] = useState(true);
    const [isFormShown, setIsFormShown] = useState(false);
    const [formOperation, setFormOperation] = useState("");
    const [editingProduct, setEditingProduct] = useState<Product>();
    const [productSum, setProductSum] = useState<number>(0);

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const categoryId = searchParams.get("category");

    useEffect(getProducts, []);

    function getProducts() {
        let categoryProductsUrl = `${PRODUCTS_ENDPOINT}`;

        if (categoryId) {
            categoryProductsUrl += `?category_id=${categoryId}`;
        }

        axios
            .get(categoryProductsUrl, {
                headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)},
            })
            .then((response) => {
                setIsDataCorrect(true);
                const products = response.data as Product[];
                setProducts(products);

                const productSum = products.reduce((accumulator, product) => accumulator + (product.inStockAmount * product.price), 0)
                setProductSum(productSum);
            })
            .catch((error) => {
                if (error.response.status === 404 || error.response.status === 409) {
                    setIsDataCorrect(false);
                    window.location.href = "/categories";
                    return;
                } else {
                    setIsDataCorrect(true);
                    handleLogout();
                }
            });
    }


    function handleCreateButtonClick() {
        setFormOperation(OPERATION_CREATE);
        setIsFormShown(true);
    }

    function handleEditButtonClick(id: number) {
        const product = products.find(p => p.id === id) as Product;
        setEditingProduct(product);

        setFormOperation(OPERATION_EDIT);
        setIsFormShown(true);
    }

    function handleChangeButtonClick(id: number) {
        const product = products.find(p => p.id === id) as Product;
        setEditingProduct(product);

        setFormOperation(OPERATION_EDIT);
        setIsFormShown(true);
    }

    function closeForm() {
        setIsFormShown(false);
        setEditingProduct(undefined);
        setIsDataCorrect(true);
    }

    function handleSubmitForm(product: Product) {
        if (formOperation === OPERATION_CREATE) {
            handleCreate(product);
        } else if (formOperation === OPERATION_EDIT) {
            if (localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER) {
                handleEdit(product);
            }
            if (localStorage.getItem(ROLE_KEY) === USER_ROLE_EMPLOYEE) {
                handleChangeQuantity(product);
            }
        }
    }

    function handleCreate(product: Product) {
        axios.post(PRODUCTS_ENDPOINT,
            {
                name: product.name,
                description: product.description,
                producer: product.producer,
                inStockAmount: product.inStockAmount,
                price: product.price,
                categoryId: product.category.id,
            },
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then(response => {
                product.id = response.data as number;

                setProducts([...products, product]);

                closeForm();
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }
                const responseCode = error.response.status;
                if (responseCode == 409) {
                    setIsDataCorrect(false);
                }
            });
    }

    function handleEdit(product: Product) {
        axios.put(`${PRODUCTS_ENDPOINT}/${product.id}`,
            {
                name: product.name,
                description: product.description,
                producer: product.producer,
                inStockAmount: product.inStockAmount,
                price: product.price,
                categoryId: product.category.id,
            },
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then(() => {
                const updatedProducts = [...products];
                const editedProductIndex = products.findIndex(p => p.id === product.id);
                updatedProducts[editedProductIndex] = product;
                setProducts(updatedProducts);

                closeForm();
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }

                const responseCode = error.response.status;

                if (responseCode === 409) {

                    setIsDataCorrect(false);
                }
            });
    }

    function handleChangeQuantity(product: Product) {
        const patchData = {
            op: "add",
            path: "/inStockAmount",
            value: product.inStockAmount
        };

        axios.patch(`${PRODUCTS_ENDPOINT}/${product.id}`, patchData,
            {
                headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}
            })
            .then(() => {
                const updatedProducts = [...products];
                const editedProductIndex = products.findIndex(p => p.id === product.id);
                updatedProducts[editedProductIndex] = product;
                setProducts(updatedProducts);
                window.location.href = "/goods";

                closeForm();
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }

                const responseCode = error.response.status;

                if (responseCode === 409) {
                    setIsDataCorrect(false);
                }
            });
    }


    function handleDelete(id: number) {
        axios.delete(`${PRODUCTS_ENDPOINT}/${id}`,
            {headers: {Authorization: localStorage.getItem(AUTHORIZATION_KEY)}})
            .then(() => {
                const updatedProducts = [...products];
                const deletedProductIndex = products.findIndex(p => p.id === id);
                updatedProducts.splice(deletedProductIndex, 1);

                setProducts(updatedProducts);
            })
            .catch((error: AxiosError) => {
                if (!error.response) {
                    handleLogout();
                    return;
                }
            });
    }

    return (
        <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8" id="goods-container">
            <Title className="mt-7">Goods</Title>
            <Container className="my-5">
                <Row className="align-items-center">
                    <Col>
                        {localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER &&
                            <Button variant="primary"
                                    className="px-5 py-2 bg-our-green font-semibold text-white"
                                    onClick={handleCreateButtonClick}>
                                Add product
                            </Button>
                        }
                    </Col>
                </Row>
            </Container>
            <div className="mt-8 mb-8">
                {localStorage.getItem(ROLE_KEY) === USER_ROLE_MANAGER && <ProdTable
                    products={products}
                    onEditButtonClick={handleEditButtonClick}
                    onDeleteButtonClick={handleDelete}
                />}
                {localStorage.getItem(ROLE_KEY) === USER_ROLE_EMPLOYEE && <ProdTable
                    products={products}
                    onEditButtonClick={handleChangeButtonClick}
                    onDeleteButtonClick={handleDelete}
                />}

            </div>

            <div
                style={{
                    color: "#333",
                    padding: "10px",
                    textAlign: "right"
                }}>
                Total amount: {productSum.toFixed(2)}
            </div>
            {isFormShown &&
                <ProductsFormWindow
                    title={`${formOperation} product`}
                    isDataCorrect={isDataCorrect}
                    onSubmit={handleSubmitForm}
                    handleCloseButtonClick={closeForm}
                    startingProduct={editingProduct}
                />
            }
        </div>
    );
}